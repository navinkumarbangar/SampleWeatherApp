package com.example.navinbangar.sampleweatherapp.data.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.navinbangar.sampleweatherapp.data.db.convertor.DataModelConverter;
import com.example.navinbangar.sampleweatherapp.model.CityModel;
import com.example.navinbangar.sampleweatherapp.model.DayModel;

import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "WeatherDetailsEntity")
public class WeatherDetailsEntity {
    @PrimaryKey(autoGenerate = true)
    private int tableId;
    private String city;
    @Embedded
    private CityModel cityModel;
    private double cod;
    private double message;
    private int cnt;
    @TypeConverters(DataModelConverter.class)
    private List<DayModel> dayModelArrayList = new ArrayList<DayModel>();
    public WeatherDetailsEntity(){}

    @Ignore
    public WeatherDetailsEntity( String cityName, CityModel city, double cod, double message, int cnt,List<DayModel> list) {
        this.city=cityName;
        this.cityModel = city;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.dayModelArrayList = list;
    }
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CityModel getCityModel() {
        return cityModel;
    }

    public void setCityModel(CityModel cityModel) {
        this.cityModel = cityModel;
    }


    public double getCod() {
        return cod;
    }

    public void setCod(double cod) {
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

    public String getCity() {
        return city;
    }

    public List<DayModel> getDayModelArrayList() {
        return dayModelArrayList;
    }

    public void setDayModelArrayList(List<DayModel> dayModelArrayList) {
        this.dayModelArrayList = dayModelArrayList;
    }
}
