package com.carlitosdroid.horizontalviewdetailrcv.model;

import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 2/15/17.
 *
 */

public class JuryEntity {

    private String title;
    private List<AnimalEntity> animalEntities;


    public JuryEntity(String title, List<AnimalEntity> animalEntities) {
        this.title = title;
        this.animalEntities = animalEntities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AnimalEntity> getAnimalEntities() {
        return animalEntities;
    }

    public void setAnimalEntities(List<AnimalEntity> animalEntities) {
        this.animalEntities = animalEntities;
    }
}
