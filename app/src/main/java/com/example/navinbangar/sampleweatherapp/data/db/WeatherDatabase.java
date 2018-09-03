package com.example.navinbangar.sampleweatherapp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.navinbangar.sampleweatherapp.data.db.convertor.DataModelConverter;
import com.example.navinbangar.sampleweatherapp.data.db.convertor.WeatherModelConvertor;
import com.example.navinbangar.sampleweatherapp.data.db.dao.WeatherDao;
import com.example.navinbangar.sampleweatherapp.data.db.entity.WeatherDetailsEntity;

@Database(entities = {WeatherDetailsEntity.class}, version = 1,exportSchema = false)
@TypeConverters({ DataModelConverter.class,WeatherModelConvertor.class})
public abstract class WeatherDatabase  extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
