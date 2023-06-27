package com.example.parkingchallenge.dto;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Field;

@Data
@EqualsAndHashCode
public class Parking {

    private String parkingName;
    private int distance;
    private Integer  space;
    private Integer freeSpace;
    private String geoPoint;



    public void setProperty(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(fieldName);
        if (field.getType().isAssignableFrom(Integer.class)) {
            field.set(this, (int)Double.parseDouble(value));
            return;
        }
        field.set(this, value);
    }


}
