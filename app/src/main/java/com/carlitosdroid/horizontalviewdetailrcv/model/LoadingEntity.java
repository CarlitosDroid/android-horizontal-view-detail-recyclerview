package com.carlitosdroid.horizontalviewdetailrcv.model;

import java.io.Serializable;

/**
 * Created by Carlos Vargas on 11/22/16.
 * CarlitosDroid
 */

public class LoadingEntity implements Serializable{

    private String description;

    public LoadingEntity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
