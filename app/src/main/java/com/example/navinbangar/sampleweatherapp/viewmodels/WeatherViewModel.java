package com.example.navinbangar.sampleweatherapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.navinbangar.sampleweatherapp.data.db.entity.WeatherDetailsEntity;
import com.example.navinbangar.sampleweatherapp.data.repository.WeatherRepository;

import javax.inject.Inject;

public class WeatherViewModel extends ViewModel {
    private LiveData<WeatherDetailsEntity> weatherDetail;
    private WeatherRepository weatherRepo;
    private  String city;

    @Inject
    public WeatherViewModel(WeatherRepository weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    public void init(String cityName) {
        this.city=cityName;
        weatherRepo.getWeather(cityName);
    }

    public LiveData<WeatherDetailsEntity> loadWeather() {
        return weatherRepo.loadWeather(this.city);
    }
}