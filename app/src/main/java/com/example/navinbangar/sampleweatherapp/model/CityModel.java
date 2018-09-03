package com.example.navinbangar.sampleweatherapp.model;

import android.arch.persistence.room.Embedded;

public class CityModel {
    private int id;
    private String name;
    @Embedded
    private CoordinatesModel coord;
    private String country;
    private int population;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordinatesModel getCoord() {
        return coord;
    }

    public void setCoord(CoordinatesModel coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
