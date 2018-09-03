package com.example.navinbangar.sampleweatherapp.di.modules;

import com.example.navinbangar.sampleweatherapp.ui.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();
}