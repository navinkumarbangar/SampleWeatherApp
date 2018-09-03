package com.example.navinbangar.sampleweatherapp.data.api;

import com.example.navinbangar.sampleweatherapp.data.db.entity.WeatherDetailsEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherWebService {
    @GET("/data/2.5/forecast/daily?")
    Call<WeatherDetailsEntity> getWeather(@Query("q")String city, @Query("APPID")String appId);
}
