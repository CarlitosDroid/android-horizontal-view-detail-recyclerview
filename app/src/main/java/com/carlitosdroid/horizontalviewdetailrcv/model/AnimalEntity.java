package com.carlitosdroid.horizontalviewdetailrcv.model;

import java.io.Serializable;

/**
 * Created by Carlos Vargas on 11/23/16.
 * CarlitosDroid
 */

public class AnimalEntity implements Serializable{

    private boolean isfavorite;
    private String title;

    public AnimalEntity(boolean isfavorite, String title) {
        this.isfavorite = isfavorite;
        this.title = title;
    }

    public boolean isfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(boolean isfavorite) {
        this.isfavorite = isfavorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
