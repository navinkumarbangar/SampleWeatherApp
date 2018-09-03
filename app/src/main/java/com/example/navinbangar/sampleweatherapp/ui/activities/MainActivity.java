package com.example.navinbangar.sampleweatherapp.ui.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navinbangar.sampleweatherapp.R;
import com.example.navinbangar.sampleweatherapp.Utilities.Constants;
import com.example.navinbangar.sampleweatherapp.ui.fragments.WeatherDetailFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    @BindView(R.id.cityName)
    EditText cityName;
    @BindView(R.id.cityButton)
    Button cityButton;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureDagger();
        configureButterKnife();
    }


    @OnClick(R.id.cityButton)
    public void onButtonClick(View view) {
        if (cityName.getText().toString().length()>0) {
            ShowWeatherDetail();
        }else{
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_message_cityName),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void ShowWeatherDetail(){
            WeatherDetailFragment fragment = new WeatherDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.CITY_NAME,cityName.getText().toString());
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, fragment, null)
                    .commit();

    }

    private void configureButterKnife() {
        ButterKnife.bind(this);
    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}

