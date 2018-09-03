package com.example.navinbangar.sampleweatherapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.TypeConverters;

import com.example.navinbangar.sampleweatherapp.data.db.convertor.WeatherModelConvertor;

import java.util.List;

public class DayModel {
    private int dt;
    @Embedded
    private TempModel temp;
    private double pressure;
    private double humidity;
    @TypeConverters(WeatherModelConvertor.class)
    private List<WeatherModel> weather;
    private double speed;
    private int deg;
    private int clouds;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public TempModel getTemp() {
        return temp;
    }

    public void setTemp(TempModel temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public List<WeatherModel> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherModel> weather) {
        this.weather = weather;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }
}
