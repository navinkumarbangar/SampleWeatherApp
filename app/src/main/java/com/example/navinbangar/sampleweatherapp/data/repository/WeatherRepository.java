package com.example.navinbangar.sampleweatherapp.data.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.navinbangar.sampleweatherapp.Utilities.NetworkConfig;
import com.example.navinbangar.sampleweatherapp.data.api.WeatherWebService;
import com.example.navinbangar.sampleweatherapp.data.db.dao.WeatherDao;
import com.example.navinbangar.sampleweatherapp.data.db.entity.WeatherDetailsEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Singleton
public class WeatherRepository {
    private final WeatherWebService webservice;
    private final WeatherDao weatherDao;
    private final Executor executor;

    @Inject
    public WeatherRepository(WeatherWebService webservice, WeatherDao weatherDao, Executor executor) {
        this.webservice = webservice;
        this.weatherDao = weatherDao;
        this.executor = executor;
    }

    public void getWeather(String cityName) {
        getWeatherDetail(cityName);
    }
    public LiveData<WeatherDetailsEntity> loadWeather(String cityName) {
        return weatherDao.load(cityName);
    }

    private void getWeatherDetail(final String cityName) {
        executor.execute(() -> {
                webservice.getWeather(cityName, NetworkConfig.WEATHER_API_KEY).enqueue(new Callback<WeatherDetailsEntity>() {
                    @Override
                    public void onResponse(Call<WeatherDetailsEntity> call, Response<WeatherDetailsEntity> response) {
                        executor.execute(() -> {
                            WeatherDetailsEntity weatherApIResult = response.body();
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            System.out.println(" Test Weather " +gson.toJson(weatherApIResult));
                             //TODO-Need to do Rx java implementation to save and retrive data from Db
                              saveWeatherDetailInDb(weatherApIResult);
                        });
                    }

                    @Override
                    public void onFailure(Call<WeatherDetailsEntity> call, Throwable t) {
                        Log.e("WeatherRepository Error", call.toString());
                    }

                });

        });
    }

    private void saveWeatherDetailInDb(WeatherDetailsEntity weatherApIResult) {
        if (weatherApIResult!=null) {
            WeatherDetailsEntity weatherDetailsEntity = new WeatherDetailsEntity(weatherApIResult.getCity(), weatherApIResult.getCityModel(), weatherApIResult.getCod(), weatherApIResult.getMessage(), weatherApIResult.getCnt(), weatherApIResult.getDayModelArrayList());
            long result = weatherDao.save(weatherDetailsEntity);
            System.out.println("WeatherRepository result===" + result);
        }
    }

}

