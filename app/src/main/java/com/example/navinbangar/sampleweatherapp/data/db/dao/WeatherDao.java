package com.example.navinbangar.sampleweatherapp.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.navinbangar.sampleweatherapp.data.db.entity.WeatherDetailsEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface WeatherDao {
        @Insert(onConflict = REPLACE)
        long save(WeatherDetailsEntity weatherDetail);

        @Query("SELECT * FROM WeatherDetailsEntity WHERE city = :cityName")
        LiveData<WeatherDetailsEntity> load(String cityName);
}
