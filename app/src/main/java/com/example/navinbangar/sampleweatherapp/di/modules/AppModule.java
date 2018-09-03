package com.example.navinbangar.sampleweatherapp.di.modules;


import android.app.Application;
import android.arch.persistence.room.Room;
import com.example.navinbangar.sampleweatherapp.Utilities.NetworkConfig;
import com.example.navinbangar.sampleweatherapp.data.api.WeatherWebService;
import com.example.navinbangar.sampleweatherapp.data.db.WeatherDatabase;
import com.example.navinbangar.sampleweatherapp.data.db.dao.WeatherDao;
import com.example.navinbangar.sampleweatherapp.data.repository.WeatherRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    WeatherDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                WeatherDatabase.class, "WeatherDatabase.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    WeatherDao provideWeatherDao(WeatherDatabase database) {
        return database.weatherDao();
    }


    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherWebService webservice, WeatherDao weatherDao, Executor executor) {
        return new WeatherRepository(webservice, weatherDao, executor);
    }


    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(NetworkConfig.WEATHER_BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    WeatherWebService provideWeatherWebService(Retrofit restAdapter) {
        return restAdapter.create(WeatherWebService.class);
    }
}

