package com.example.parkingchallenge.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARAM_ITEM_PARSER" , indexes = {
        @Index(name = "IX_PARAM_ITEM_PARSER_CLIENT_PARAM_ID", columnList = "PARAM_ITEM_PARSER_CLIENT_PARAM_ID ASC")
})
@Entity
@SequenceGenerator(name = "CLIENT_PARAMS_SEQ", sequenceName = "CLIENT_PARAMS_SEQ", allocationSize = 10)
public class ParamItemParserEntity {



    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_PARAMS_SEQ")
    @Id
    @Column(name = "PARAM_ITEM_PARSER_ID")
    private Long id;

    @Basic
    @Column(name = "PARAM_ITEM_PARSER_JSONNODE", nullable = false,length = 20)
    private String jsonNodeToGet;

    @Basic
    @Column(name = "PARAM_ITEM_PARSER_IS_ARRAY")
    private Boolean array;

    @Basic
    @Column(name = "PARAM_ITEM_PARSER_PROPERTY_TO_MAP", length = 20)
    private String propertyToMap;


    @ManyToOne
    @JoinColumn(name = "PARAM_ITEM_PARSER_CLIENT_PARAM_ID", referencedColumnName = "CLIENT_PARAMS_ID", nullable = false)
    @ToString.Exclude
    private ClientParamsEntity clientParam;
}
