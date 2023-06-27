package com.example.parkingchallenge.model;
import lombok.*;

import javax.persistence.*;
import javax.persistence.FetchType;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "CLIENT_PARAMS" , indexes = {@Index(name = "IX_CLIENT_PARAMS_CITY_OR_TOWN", columnList = "CLIENT_PARAMS_CITY_OR_TOWN ASC")
})
@Entity
@SequenceGenerator(name = "CLIENT_PARAMS_SEQ", sequenceName = "CLIENT_PARAMS_SEQ", allocationSize = 10)
@NamedEntityGraph(name = "clientParams-with-params-item-parser",
        attributeNodes = {
                @NamedAttributeNode(value = "paramsItemParserList")})
public class ClientParamsEntity {



    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_PARAMS_SEQ")
    @Id
    @Column(name = "CLIENT_PARAMS_ID")
    private Long id;

    @Basic
    @Column(name = "CLIENT_PARAMS_CITY_OR_TOWN", nullable = false, length = 50)
    private String city;

    @Basic
    @Column(name = "CLIENT_PARAMS_URI", nullable = false)
    private String uriWithQueryParams;

    @OneToMany(mappedBy = "clientParam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ParamItemParserEntity> paramsItemParserList;


}
