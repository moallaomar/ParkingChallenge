package com.example.parkingchallenge.services.impl;

import com.example.parkingchallenge.clients.ParkingClient;
import com.example.parkingchallenge.dto.Parking;
import com.example.parkingchallenge.exceptions.RestClientException;
import com.example.parkingchallenge.model.ClientParamsEntity;
import com.example.parkingchallenge.repository.ClientParamsRepository;
import com.example.parkingchallenge.services.ParkingService;
import com.example.parkingchallenge.services.ReverseGeoCodingService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

    private static final double EARTH_RADIUS = 6371000; // Rayon moyen de la Terre en mètres
    @Autowired
    private ParkingClient parkingClient;
    @Autowired
    private ReverseGeoCodingService reverseGeoCodingService;
    @Autowired
    private ClientParamsRepository clientParamsRepository;

    /**
     * ce service récupère les coordonnées et fait appel dans un 1er temps au service
     * de reverse Geo Coding, une fois la ville ou commune
     * identifiée, j'appelle la table de paramétrage pour récupérer l'uri et les json node à récupérer au moment du parsing.
     *
     * @param longitude
     * @param latitude
     * @return List<Parking>
     */
    @Override
    public List<Parking> getAllNearestParking(Double longitude, Double latitude) {
        if (longitude == null || latitude == null) {
            throw new IllegalArgumentException("longitude and latitude cannot be null");
        }
        String cityOrTown = reverseGeoCodingService.getTownOrCityFromOpenStreetMap(latitude.toString(), longitude.toString());
        // a ce niveau on a la commune ou la ville et les coordonnées
        List<ClientParamsEntity> clientParams = clientParamsRepository.findByCity(cityOrTown.toLowerCase());
        List<Parking> parkings;

        parkings = getAllInformationsFromDataSource(clientParams);

        // on ajoute la distance et sort par distance (ASC)
        return parkings.stream().map(parking -> mapDistance(parking, longitude, latitude)).sorted(Comparator.comparingInt(Parking::getDistance)).collect(Collectors.toList());

    }

    /**
     * methode qui récupère les coordonnées d'un parking , fait le calcul de distance et le set dans le DTO
     *
     * @param parking
     * @param longitude
     * @param latitude
     * @return
     */
    private Parking mapDistance(Parking parking, Double longitude, Double latitude) {
        String[] geoPoint = parking.getGeoPoint().split(",");
        parking.setDistance(calculateDistance(longitude, latitude, Double.valueOf(geoPoint[0]), Double.valueOf(geoPoint[1])));
        return parking;
    }

    /**
     * la formule de la "distance orthodromique" ou "grande cercle" pour calculer la distance entre deux points sur la surface de la Terre. La formule utilise le rayon moyen de la Terre pour effectuer le calcul.
     * Les latitudes et longitudes sont fournies en degrés, et la distance calculée est en mètre.
     * Pour ne pas vous mentir, j'ai récupéré le code de chatGPT  car j'ai jamais été confronté à cette situation :)
     *
     * @param longitude
     * @param latitude
     * @param longitudeParking
     * @param latitudeParking
     * @return
     */
    private int calculateDistance(Double longitude, Double latitude, Double longitudeParking, Double latitudeParking) {
        double lat1Rad = Math.toRadians(latitude);
        double lat2Rad = Math.toRadians(latitudeParking);
        double deltaLat = Math.toRadians(latitudeParking - latitude);
        double deltaLon = Math.toRadians(longitudeParking - longitude);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS * c;

        return (int) distance;
    }


    /**
     * dans l'exemple  que vous avez envoyé (poitiers) on a 2 sources de données à exploiter. le but est de
     * récupérer les informations de chaque source et de les fusionner dans une liste de parking.
     * La clé de la Map est le nom du parking.
     * Cette méthode est capable de répurérer la liste la liste des parkings provenant d'une seule dataSource
     *
     * @param clientParams
     * @return
     */
    private List<Parking> getAllInformationsFromDataSource(List<ClientParamsEntity> clientParams) {
        Map<String, Parking> parkingMap = new HashMap<>();
        Set<Parking> parkings = new HashSet<>();
        clientParams.forEach(clientParam -> {
            JSONArray parkingsJson = getJsonArrayFromStringResponse(invokeClient(clientParam.getUriWithQueryParams()));

            for (int i = 0; i < parkingsJson.size(); i++) {
                Parking parking = new Parking();
                JSONObject field = (JSONObject) ((JSONObject) parkingsJson.get(i)).get(clientParam.getParamsItemParserList().stream().filter(itemParser -> itemParser.getArray()).findFirst().get().getJsonNodeToGet());

                propertySetter(parking, clientParam, field);

                if (parkingMap.containsKey(parking.getParkingName())) {
                    parkingMap.get(parking.getParkingName()).setFreeSpace(parking.getFreeSpace());
                } else {
                    parkingMap.put(parking.getParkingName(), parking);
                }
            }
        });

        parkingMap.keySet().stream().forEach(key -> {
            Parking parking = parkingMap.get(key);
            if (parking.getGeoPoint() != null) {
                parking.setGeoPoint(parking.getGeoPoint().replaceAll("[^\\d.,]", ""));
                parkings.add(parking);
            }
        });
        return parkings.stream().filter(parking -> parking.getSpace() != null && parking.getFreeSpace() != null && parking.getGeoPoint() != null).collect(Collectors.toList());
    }

    /**
     * cette méthode permet de définir les propriétés de l'objet parking à partir de la liste des fields.
     * Le but est de faire appel à la reflection , l'idée est de setter un attribut  au runtime avec son nom au format String
     *
     * @param parking
     * @param clientParam
     * @param field
     */
    private void propertySetter(Parking parking, ClientParamsEntity clientParam, JSONObject field) {
        clientParam.getParamsItemParserList().stream().filter(itemParser -> !itemParser.getArray()).forEach(itemParser -> {
            try {
                parking.setProperty(itemParser.getPropertyToMap(), field.get(itemParser.getJsonNodeToGet()).toString());
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });
    }

    /**
     * methode qui transforme la reponse de l'API en un JSONArray comportant des fields
     *
     * @param jsonResponse
     * @return
     */
    private JSONArray getJsonArrayFromStringResponse(String jsonResponse) {
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonResponse);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return (JSONArray) jsonObject.get("records");
    }


    /**
     * cette méthode invoque  l'API client pour récupérer les informations
     *
     * @param uri
     * @return json au format String
     */
    private String invokeClient(String uri) {
        try {
            return parkingClient.getAllParkingsWithoutQueryParams(URI.create(uri));
        } catch (RuntimeException exception) {
            throw new RestClientException("Une erreur est survenue au moment de l'appel HTTP uri = " + uri, exception);
        }
    }
}
