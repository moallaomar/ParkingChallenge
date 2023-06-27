package com.example.parkingchallenge;

import com.example.parkingchallenge.model.ClientParamsEntity;
import com.example.parkingchallenge.model.ParamItemParserEntity;
import com.example.parkingchallenge.repository.ClientParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableFeignClients
public class ParkingChallengeApplication implements CommandLineRunner {

    @Autowired
    private ClientParamsRepository clientParamsRepository;

    public static void main(String[] args) {
        SpringApplication.run(ParkingChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        ClientParamsEntity clientParamsEntity = new ClientParamsEntity();
        clientParamsEntity.setCity("poitiers");
        clientParamsEntity.setUriWithQueryParams("https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilite-parkings-grand-poitiers-donnees-metiers&rows=1000&facet=nom_du_parking&facet=zone_tarifaire&facet=statut2&facet=statut3");


        ParamItemParserEntity paramItemParserEntity = new ParamItemParserEntity();
        paramItemParserEntity.setArray(true);
        paramItemParserEntity.setJsonNodeToGet("fields");
        paramItemParserEntity.setClientParam(clientParamsEntity);
        ParamItemParserEntity paramItemParserEntity1 = new ParamItemParserEntity();
        paramItemParserEntity1.setArray(false);
        paramItemParserEntity1.setJsonNodeToGet("nom_du_par");
        paramItemParserEntity1.setPropertyToMap("parkingName");
        paramItemParserEntity1.setClientParam(clientParamsEntity);

        ParamItemParserEntity paramItemParserEntity6 = new ParamItemParserEntity();
        paramItemParserEntity6.setArray(false);
        paramItemParserEntity6.setJsonNodeToGet("geo_shape");
        paramItemParserEntity6.setPropertyToMap("geoPoint");
        paramItemParserEntity6.setClientParam(clientParamsEntity);

        ParamItemParserEntity paramItemParserEntity2 = new ParamItemParserEntity();
        paramItemParserEntity2.setArray(false);
        paramItemParserEntity2.setJsonNodeToGet("nb_places");
        paramItemParserEntity2.setPropertyToMap("space");
        paramItemParserEntity2.setClientParam(clientParamsEntity);

        clientParamsEntity.setParamsItemParserList(Stream.of(paramItemParserEntity, paramItemParserEntity1, paramItemParserEntity2, paramItemParserEntity6).collect(Collectors.toSet()));
        clientParamsRepository.save(clientParamsEntity);
        ClientParamsEntity clientParamsEntity1 = new ClientParamsEntity();
        clientParamsEntity1.setCity("poitiers");
        clientParamsEntity1.setUriWithQueryParams("https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilites-stationnement-des-parkings-en-temps-reel&facet=nom");
        ParamItemParserEntity paramItemParserEntity4 = new ParamItemParserEntity();
        paramItemParserEntity4.setArray(true);
        paramItemParserEntity4.setJsonNodeToGet("fields");
        paramItemParserEntity4.setClientParam(clientParamsEntity1);
        ParamItemParserEntity paramItemParserEntity3 = new ParamItemParserEntity();
        paramItemParserEntity3.setArray(false);
        paramItemParserEntity3.setJsonNodeToGet("places");
        paramItemParserEntity3.setPropertyToMap("freeSpace");
        paramItemParserEntity3.setClientParam(clientParamsEntity1);


        ParamItemParserEntity paramItemParserEntity5 = new ParamItemParserEntity();
        paramItemParserEntity5.setArray(false);
        paramItemParserEntity5.setJsonNodeToGet("nom");
        paramItemParserEntity5.setPropertyToMap("parkingName");
        paramItemParserEntity5.setClientParam(clientParamsEntity1);


        clientParamsEntity1.setParamsItemParserList(Stream.of(paramItemParserEntity3, paramItemParserEntity4, paramItemParserEntity5).collect(Collectors.toSet()));
        clientParamsRepository.save(clientParamsEntity1);


        ClientParamsEntity clientParamsEntity2 = new ClientParamsEntity();
        clientParamsEntity2.setCity("lille");
        clientParamsEntity2.setUriWithQueryParams("https://opendata.lillemetropole.fr/api/records/1.0/search/?dataset=disponibilite-parkings&q=&facet=libelle&facet=ville&facet=etat");
        ParamItemParserEntity paramItemParserEntity7 = new ParamItemParserEntity();
        paramItemParserEntity7.setArray(true);
        paramItemParserEntity7.setJsonNodeToGet("fields");
        paramItemParserEntity7.setClientParam(clientParamsEntity2);
        ParamItemParserEntity paramItemParserEntity8 = new ParamItemParserEntity();
        paramItemParserEntity8.setArray(false);
        paramItemParserEntity8.setJsonNodeToGet("libelle");
        paramItemParserEntity8.setPropertyToMap("parkingName");
        paramItemParserEntity8.setClientParam(clientParamsEntity2);

        ParamItemParserEntity paramItemParserEntity9 = new ParamItemParserEntity();
        paramItemParserEntity9.setArray(false);
        paramItemParserEntity9.setJsonNodeToGet("geometry");
        paramItemParserEntity9.setPropertyToMap("geoPoint");
        paramItemParserEntity9.setClientParam(clientParamsEntity2);

        ParamItemParserEntity paramItemParserEntity10 = new ParamItemParserEntity();
        paramItemParserEntity10.setArray(false);
        paramItemParserEntity10.setJsonNodeToGet("max");
        paramItemParserEntity10.setPropertyToMap("space");
        paramItemParserEntity10.setClientParam(clientParamsEntity2);

        ParamItemParserEntity paramItemParserEntity11 = new ParamItemParserEntity();
        paramItemParserEntity11.setArray(false);
        paramItemParserEntity11.setJsonNodeToGet("dispo");
        paramItemParserEntity11.setPropertyToMap("freeSpace");
        paramItemParserEntity11.setClientParam(clientParamsEntity2);

        clientParamsEntity2.setParamsItemParserList(Stream.of(paramItemParserEntity7, paramItemParserEntity8, paramItemParserEntity9, paramItemParserEntity10, paramItemParserEntity11).collect(Collectors.toSet()));
        clientParamsRepository.save(clientParamsEntity2);


    }
}
