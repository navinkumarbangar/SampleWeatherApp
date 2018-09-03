package com.example.navinbangar.sampleweatherapp.di.component;

import android.app.Application;

import com.example.navinbangar.sampleweatherapp.App;
import com.example.navinbangar.sampleweatherapp.di.modules.ActivityModule;
import com.example.navinbangar.sampleweatherapp.di.modules.AppModule;
import com.example.navinbangar.sampleweatherapp.di.modules.FragmentModule;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;


@Singleton
@Component(modules={ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}

