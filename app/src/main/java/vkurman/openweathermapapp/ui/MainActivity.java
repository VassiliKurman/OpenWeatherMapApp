/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vkurman.openweathermapapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vkurman.openweathermapapp.R;
import vkurman.openweathermapapp.aac.Repository;
import vkurman.openweathermapapp.aac.WeatherResponseViewModel;
import vkurman.openweathermapapp.adapters.CityAdapter;
import vkurman.openweathermapapp.model.City;
import vkurman.openweathermapapp.model.WeatherResponse;
import vkurman.openweathermapapp.retrofit.ApiUtils;
import vkurman.openweathermapapp.retrofit.OpenWeatherMapService;
import vkurman.openweathermapapp.utils.CityJsonUtils;
import vkurman.openweathermapapp.utils.OpenWeatherMapUtils;

/**
 * {@link MainActivity} is an {@link AppCompatActivity} that is displayed at
 * start of the App.
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener {

    /**
     * Tag for Log
     */
    private static final String TAG = MainActivity.class.getSimpleName();
    /**
     * Permission Request id
     */
    private static final int PERMISSIONS_REQUEST_INTERNET = 101;

    private CityAdapter cityAdapter;

    /**
     * {@link WeatherResponse} {@link ViewModel}
     */
    private WeatherResponseViewModel viewModel;

//    @BindView(R.id.cities) Spinner mCities;
    @BindView(R.id.ed_city_id) EditText edCity;
    @BindView(R.id.btn_load) Button load;

    @BindView(R.id.code) TextView mCode;
    @BindView(R.id.id) TextView mId;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.timezone) TextView mTimezone;
    @BindView(R.id.datetime) TextView mDateTime;
    @BindView(R.id.visibility) TextView mVisibility;
    @BindView(R.id.base) TextView mBase;
    @BindView(R.id.main_temperature) TextView mMainTemperature;
    @BindView(R.id.main_pressure) TextView mMainPressure;
    @BindView(R.id.main_humidity) TextView mMainHumidity;
    @BindView(R.id.main_temperature_min) TextView mMainTemperatureMin;
    @BindView(R.id.main_temperature_max) TextView mMainTemperatureMax;
    @BindView(R.id.coordinates_longitude) TextView mCoordinatesLongitude;
    @BindView(R.id.coordinates_latitude) TextView mCoordinatesLatitude;
    @BindView(R.id.weather_id) TextView mWeatherId;
    @BindView(R.id.weather_main) TextView mWeatherMain;
    @BindView(R.id.weather_description) TextView mWeatherDescription;
    @BindView(R.id.wind_speed) TextView mWindSpeed;
    @BindView(R.id.wind_degree) TextView mWindDegree;
    @BindView(R.id.clouds_all) TextView mCloudsAll;
    @BindView(R.id.system_type) TextView mSystemType;
    @BindView(R.id.system_id) TextView mSystemId;
    @BindView(R.id.system_country) TextView mSystemCountry;
    @BindView(R.id.system_sunrise) TextView mSystemSunrise;
    @BindView(R.id.system_sunset) TextView mSystemSunset;

    @BindView(R.id.weather_icon) ImageView mWeatherIcon;

//    private OpenWeatherMapService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Binding views
        ButterKnife.bind(this);
        // Getting reference to service
//        mService = ApiUtils.getOpenWeatherMapService();
        // Checking for INTERNET permission
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.INTERNET)
//                != PackageManager.PERMISSION_GRANTED) {
//            // Requesting the permission
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.INTERNET},
//                    PERMISSIONS_REQUEST_INTERNET);
//        } else {
//            // Loading data
//            loadData();
//        }

        // Create an ArrayAdapter using the string array and a default spinner layout
//        cityAdapter = new CityAdapter(this,
//                R.layout.city_layout,
//                R.id.city_name,
//                new ArrayList<>());
//        // Specify the layout to use when the list of choices appears
//        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        mCities.setAdapter(cityAdapter);
//        mCities.setOnItemSelectedListener(this);

        load.setOnClickListener(this);

//        new AsyncTask<Void, Void, List<City>>() {
//
//            @Override
//            protected List<City> doInBackground(Void... voids) {
//                Log.e(TAG, "Reading file ...");
//                List<City> list = new ArrayList<>();
//                try(BufferedReader reader = new BufferedReader(
//                        new InputStreamReader(getResources().openRawResource(R.raw.city)));) {
//                    StringBuffer builder = new StringBuffer();
//                    String line = reader.readLine();
//                    while (line != null) {
//                        Log.e(TAG, line);
//                        builder.append(line);
//                        line = reader.readLine();
//                    }
//                    list.addAll(CityJsonUtils.parseCityJson(builder.toString()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Log.e(TAG, "List: " + list.size());
//                return list;
//            }
//
//            @Override
//            protected void onPostExecute(List<City> list) {
//                cityAdapter.updateCities(list);
//            }
//        }.execute();

        viewModel = ViewModelProviders.of(this).get(WeatherResponseViewModel.class);
        viewModel.getWeatherResponse().observe(this, weatherResponse -> {
            if(weatherResponse != null) {
                mCode.setText(String.valueOf(weatherResponse.getCod()));
                mId.setText(String.valueOf(weatherResponse.getId()));
                mName.setText(weatherResponse.getName());
                mTimezone.setText(String.valueOf(weatherResponse.getTimezone()));
                mDateTime.setText(OpenWeatherMapUtils.convertTimestampToDateString(weatherResponse.getDt()));
                mVisibility.setText(String.valueOf(weatherResponse.getVisibility()));
                mBase.setText(weatherResponse.getBase());
                mMainTemperature.setText(String.valueOf(weatherResponse.getMain().getTemp()));
                mMainPressure.setText(String.valueOf(weatherResponse.getMain().getPressure()));
                mMainHumidity.setText(String.valueOf(weatherResponse.getMain().getHumidity()));
                mMainTemperatureMin.setText(String.valueOf(weatherResponse.getMain().getTemp_min()));
                mMainTemperatureMax.setText(String.valueOf(weatherResponse.getMain().getTemp_max()));
                mCoordinatesLongitude.setText(String.valueOf(weatherResponse.getCoord().getLon()));
                mCoordinatesLatitude.setText(String.valueOf(weatherResponse.getCoord().getLat()));
                if(weatherResponse.getWeather() != null && weatherResponse.getWeather().length > 0) {
                    mWeatherId.setText(String.valueOf(weatherResponse.getWeather()[0].getId()));
                    mWeatherMain.setText(weatherResponse.getWeather()[0].getMain());
                    mWeatherDescription.setText(weatherResponse.getWeather()[0].getDescription());
                    // Loading icon
                    Picasso.get()
                            .load(
                                    OpenWeatherMapUtils.createIconPath(weatherResponse.getWeather()[0].getIcon()))
                            .into(mWeatherIcon);
                }
                mWindSpeed.setText(String.valueOf(weatherResponse.getWind().getSpeed()));
                mWindDegree.setText(String.valueOf(weatherResponse.getWind().getDeg()));
                mCloudsAll.setText(String.valueOf(weatherResponse.getClouds().getAll()));
                mSystemType.setText(String.valueOf(weatherResponse.getSys().getType()));
                mSystemId.setText(String.valueOf(weatherResponse.getSys().getId()));
                mSystemCountry.setText(weatherResponse.getSys().getCountry());
                mSystemSunrise.setText(OpenWeatherMapUtils.convertTimestampToDateString(weatherResponse.getSys().getSunrise()));
                mSystemSunset.setText(OpenWeatherMapUtils.convertTimestampToDateString(weatherResponse.getSys().getSunset()));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final City city = cityAdapter.getCity(position);
        // Requesting to update data
        viewModel.getRepository().getWeatherResponse(getApplicationContext(), city.getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing at the moment
    }

    @Override
    public void onClick(View v) {
        if(v == load) {
            int id = Integer.parseInt(edCity.getText().toString());
            edCity.setText("");
            // Requesting to update data
            viewModel.getRepository().getWeatherResponse(getApplicationContext(), id);
        }
    }

//    public void loadData() {
//        // Id for London
//        final String cityId = "2643743";
//        // Retrieving appid
//        final Map<String, String> data = new HashMap<>();
//        data.put("id", cityId);
//        data.put("appid", getString(R.string.appid));
//        // Loading data
//        mService.weather(data).enqueue(getWeatherCallback());
//    }

//    @Override
//    public void onRequestPermissionsResult(
//            int requestCode,
//            @NonNull String[] permissions,
//            @NonNull int[] grantResults) {
//
//        if(requestCode == PERMISSIONS_REQUEST_INTERNET) {
//            // If request is cancelled, the result arrays are empty.
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                loadData();
//            } else {
//                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    /**
//     * Creates and returns callback for retrofit enqueue method.
//     *
//     * @return - Callback<WeatherResponse>
//     */
//    private Callback<WeatherResponse> getWeatherCallback() {
//        return new Callback<WeatherResponse>() {
//            @Override
//            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
//                if(response.isSuccessful()) {
//                    Log.d(TAG, "Data retrieved for " + response.body().getName());
//                    // Changing data
//                    mCode.setText(Integer.toString(response.body().getCod()));
//                    mId.setText(Integer.toString(response.body().getId()));
//                    mName.setText(response.body().getName());
//                    mTimezone.setText(Integer.toString(response.body().getTimezone()));
//                    mDateTime.setText(OpenWeatherMapUtils.convertTimestampToDateString(response.body().getDt()));
//                    mVisibility.setText(Integer.toString(response.body().getVisibility()));
//                    mBase.setText(response.body().getBase());
//                    mMainTemperature.setText(Double.toString(response.body().getMain().getTemp()));
//                    mMainPressure.setText(Integer.toString(response.body().getMain().getPressure()));
//                    mMainHumidity.setText(Integer.toString(response.body().getMain().getHumidity()));
//                    mMainTemperatureMin.setText(Double.toString(response.body().getMain().getTemp_min()));
//                    mMainTemperatureMax.setText(Double.toString(response.body().getMain().getTemp_max()));
//                    mCoordinatesLongitude.setText(Float.toString(response.body().getCoord().getLon()));
//                    mCoordinatesLatitude.setText(Float.toString(response.body().getCoord().getLat()));
//                    mWeatherId.setText(Long.toString(response.body().getWeather()[0].getId()));
//                    mWeatherMain.setText(response.body().getWeather()[0].getMain());
//                    mWeatherDescription.setText(response.body().getWeather()[0].getDescription());
//
//                    mWindSpeed.setText(Float.toString(response.body().getWind().getSpeed()));
//                    mWindDegree.setText(Integer.toString(response.body().getWind().getDeg()));
//                    mCloudsAll.setText(Integer.toString(response.body().getClouds().getAll()));
//                    mSystemType.setText(Integer.toString(response.body().getSys().getType()));
//                    mSystemId.setText(Long.toString(response.body().getSys().getId()));
//                    mSystemCountry.setText(response.body().getSys().getCountry());
//                    mSystemSunrise.setText(OpenWeatherMapUtils.convertTimestampToDateString(response.body().getSys().getSunrise()));
//                    mSystemSunset.setText(OpenWeatherMapUtils.convertTimestampToDateString(response.body().getSys().getSunset()));
//
//                    Picasso.get()
//                            .load(
//                                    OpenWeatherMapUtils.createIconPath(response.body().getWeather()[0].getIcon()))
//                            .into(mWeatherIcon);
//                } else {
//                    int statusCode  = response.code();
//                    // handle request errors depending on status code
//                    Log.e(TAG, "Error status code: " + statusCode);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<WeatherResponse> call, Throwable t) {
//                Log.e(TAG, "error loading from API: " + t.getMessage());
//                showErrorMessage();
//            }
//        };
//    }
//
//    /**
//     * Displays error message.
//     */
//    private void showErrorMessage() {
//        Toast.makeText(this, getString(R.string.error_retrieving_data), Toast.LENGTH_SHORT).show();
//    }
}