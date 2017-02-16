package com.carlitosdroid.horizontalviewdetailrcv.model;

import java.io.Serializable;

/**
 * Created by Carlos Vargas on 11/23/16.
 * CarlitosDroid
 */

public class AnimalEntity implements Serializable{

    private boolean favorite;
    private String title;

    public AnimalEntity(boolean favorite, String title) {
        this.favorite = favorite;
        this.title = title;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
