package com.example.navinbangar.sampleweatherapp.ui.fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.navinbangar.sampleweatherapp.R;
import com.example.navinbangar.sampleweatherapp.Utilities.Constants;
import com.example.navinbangar.sampleweatherapp.data.db.entity.WeatherDetailsEntity;
import com.example.navinbangar.sampleweatherapp.viewmodels.WeatherViewModel;
import com.github.mikephil.charting.charts.LineChart;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

import static java.text.DateFormat.getDateTimeInstance;

public class WeatherDetailFragment extends Fragment {

    @BindView(R.id.currentTemperature)
    TextView currentTemperature;
    @BindView(R.id.weatherSummary)
    TextView weatherSummary;
    @BindView(R.id.cloudCoverageValue)
    TextView cloudCoverageValue;
    @BindView(R.id.windSpeedValue)
    TextView windSpeedValue;
    @BindView(R.id.humidityValue)
    TextView humidityValue;
    @BindView(R.id.currentTime)
    TextView currentTime;
    @BindView(R.id.chartHourlyWeather)
    LineChart chartHourlyWeather;
    @BindView(R.id.closeButton)
    Button closeButton;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private WeatherViewModel viewModel;
    private String cityName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null && getArguments().containsKey(Constants.CITY_NAME)) {
            cityName = getArguments().getString(Constants.CITY_NAME);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }


    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel.class);
        viewModel.init(cityName);
        viewModel.loadWeather().observe(
                this, (WeatherDetailsEntity weatherDetailsEntity) -> {
                    updateUI(weatherDetailsEntity);
                });

    }

    private void updateUI(WeatherDetailsEntity weatherDetailsEntity) {
        if (weatherDetailsEntity != null) {
            System.out.println("WeatherDetail Fragmnet===" + weatherDetailsEntity.getCity().toString());
            setupMainWeatherDetailsInfo(weatherDetailsEntity);
            //TODO update dtail in MP Chart here
            setupHourlyChart(chartHourlyWeather, weatherDetailsEntity);
        }
    }


    @SuppressLint({ "SetTextI18n"})
    private void setupMainWeatherDetailsInfo(WeatherDetailsEntity weatherDetailsEntity) {
         currentTime.setText("Current time"/*getDateTimeInstance().format(Calendar.getInstance().getTime())*/);
        if (weatherDetailsEntity.getDayModelArrayList()!=null&&weatherDetailsEntity.getDayModelArrayList().size()>0) {
            currentTemperature.setText(Double.toString(convertFahrenheitToCelcius(weatherDetailsEntity.getDayModelArrayList().get(0).getTemp().getDay())));
            weatherSummary.setText(weatherDetailsEntity.getDayModelArrayList().get(0).getWeather().get(0).getDescription());
            cloudCoverageValue.setText(Integer.toString(weatherDetailsEntity.getDayModelArrayList().get(0).getClouds()));
            windSpeedValue.setText(Double.toString(weatherDetailsEntity.getDayModelArrayList().get(0).getSpeed()));
            humidityValue.setText(Double.toString(weatherDetailsEntity.getDayModelArrayList().get(0).getHumidity()));
        }
    }

    private void setupHourlyChart(LineChart lineChart, WeatherDetailsEntity weatherDetailsEntity) {
        List hoursList = new ArrayList();
        List tempratureList = new ArrayList();
    }

    @OnClick(R.id.closeButton)
    public void onButtonClick(View view) {
        Objects.requireNonNull(getActivity()).finishAffinity();
    }

    // Converts to celcius
    private double convertFahrenheitToCelcius(Double fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

}

