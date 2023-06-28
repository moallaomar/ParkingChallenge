drop table if exists CLIENT_PARAMS CASCADE;;

drop table if exists PARAM_ITEM_PARSER CASCADE;;

drop sequence if exists CLIENT_PARAMS_SEQ;;
create sequence CLIENT_PARAMS_SEQ start with 1 increment by 10;;
drop sequence if exists PARAM_ITEM_PARSER_SEQ;;
create sequence PARAM_ITEM_PARSER_SEQ start with 1 increment by 10;;


create table CLIENT_PARAMS
(
    CLIENT_PARAMS_ID           bigint       not null,
    CLIENT_PARAMS_CITY_OR_TOWN varchar(50)  not null,
    CLIENT_PARAMS_URI          varchar(255) not null,
    primary key (CLIENT_PARAMS_ID)
);;

create table PARAM_ITEM_PARSER
(
    PARAM_ITEM_PARSER_ID              bigint      not null,
    PARAM_ITEM_PARSER_IS_ARRAY        boolean,
    PARAM_ITEM_PARSER_JSONNODE        varchar(20) not null,
    PARAM_ITEM_PARSER_PROPERTY_TO_MAP varchar(20),
    PARAM_ITEM_PARSER_CLIENT_PARAM_ID bigint      not null,
    primary key (PARAM_ITEM_PARSER_ID)
);;
create index IX_CLIENT_PARAMS_CITY_OR_TOWN on CLIENT_PARAMS (CLIENT_PARAMS_CITY_OR_TOWN asc);;
create index IX_PARAM_ITEM_PARSER_CLIENT_PARAM_ID on PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_CLIENT_PARAM_ID asc);;

alter table PARAM_ITEM_PARSER
    add constraint FKc6rfd7q2bckxmic01w8p3bomy foreign key (PARAM_ITEM_PARSER_CLIENT_PARAM_ID) references CLIENT_PARAMS;;


INSERT INTO CLIENT_PARAMS(CLIENT_PARAMS_ID, CLIENT_PARAMS_CITY_OR_TOWN, CLIENT_PARAMS_URI)
values (1, 'poitiers',
        'https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilite-parkings-grand-poitiers-donnees-metiers&rows=1000&facet=nom_du_parking&facet=zone_tarifaire&facet=statut2&facet=statut3');
INSERT INTO CLIENT_PARAMS(CLIENT_PARAMS_ID, CLIENT_PARAMS_CITY_OR_TOWN, CLIENT_PARAMS_URI)
values (2, 'poitiers',
        'https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilites-stationnement-des-parkings-en-temps-reel&facet=nom');

INSERT INTO CLIENT_PARAMS(CLIENT_PARAMS_ID, CLIENT_PARAMS_CITY_OR_TOWN, CLIENT_PARAMS_URI)
values (3, 'lille',
        'https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilite-parkings-grand-poitiers-donnees-metiers&rows=1000&facet=nom_du_parking&facet=zone_tarifaire&facet=statut2&facet=statut3');


INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (1, true, 'fields', null, 1);
INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (2, false, 'nom_du_par', 'parkingName', 1);
INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (3, false, 'geo_shape', 'geoPoint', 1);
INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (4, false, 'nb_places', 'space', 1);


INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (5, true, 'fields', null, 2);
INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (6, false, 'places', 'freeSpace', 2);
INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (7, false, 'nom', 'parkingName', 2);


INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (8, true, 'fields', null, 3);
INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (9, false, 'libelle', 'parkingName', 3);
INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (10, false, 'geometry', 'geoPoint', 3);
INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (11, false, 'max', 'space', 3);
INSERT INTO PARAM_ITEM_PARSER (PARAM_ITEM_PARSER_ID, PARAM_ITEM_PARSER_IS_ARRAY, PARAM_ITEM_PARSER_JSONNODE,
                               PARAM_ITEM_PARSER_PROPERTY_TO_MAP, PARAM_ITEM_PARSER_CLIENT_PARAM_ID)
values (12, false, 'dispo', 'freeSpace', 3);


