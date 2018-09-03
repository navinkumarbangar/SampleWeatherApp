package com.example.navinbangar.sampleweatherapp.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.navinbangar.sampleweatherapp.di.key.ViewModelKey;
import com.example.navinbangar.sampleweatherapp.viewmodels.FactoryViewModel;
import com.example.navinbangar.sampleweatherapp.viewmodels.WeatherViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel.class)
    abstract ViewModel bindWeatherDetailViewModel(WeatherViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
