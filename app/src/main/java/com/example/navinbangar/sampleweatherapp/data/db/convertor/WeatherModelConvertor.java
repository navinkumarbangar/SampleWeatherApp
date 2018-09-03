package com.example.navinbangar.sampleweatherapp.data.db.convertor;

import android.arch.persistence.room.TypeConverter;
import com.example.navinbangar.sampleweatherapp.model.WeatherModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class WeatherModelConvertor {
    private static Gson gson = new Gson();
    @TypeConverter
    public static List<WeatherModel> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<WeatherModel>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<WeatherModel> someObjects) {
        return gson.toJson(someObjects);
    }
}
