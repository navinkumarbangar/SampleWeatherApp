package com.example.navinbangar.sampleweatherapp.model;

import java.util.ArrayList;
import java.util.List;

public class WeatherApIResult {

    private CityModel city;
    private int cod;
    private double message;
    private int cnt;
    private List<DayModel> list = new ArrayList<DayModel>();

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<DayModel> getList() {
        return list;
    }

    public void setList(List<DayModel> list) {
        this.list = list;
    }
}
