package com.example.navinbangar.sampleweatherapp.di.modules;

import com.example.navinbangar.sampleweatherapp.ui.fragments.WeatherDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract WeatherDetailFragment contributeWeatherDetailFragment();
}