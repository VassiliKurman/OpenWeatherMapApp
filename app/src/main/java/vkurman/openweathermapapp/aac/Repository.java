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
package vkurman.openweathermapapp.aac;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vkurman.openweathermapapp.R;
import vkurman.openweathermapapp.model.Clouds;
import vkurman.openweathermapapp.model.Coord;
import vkurman.openweathermapapp.model.Main;
import vkurman.openweathermapapp.model.Sys;
import vkurman.openweathermapapp.model.Weather;
import vkurman.openweathermapapp.model.WeatherResponse;
import vkurman.openweathermapapp.model.Wind;
import vkurman.openweathermapapp.retrofit.ApiUtils;

/**
 * {@link Repository} is single point to retrieve data.
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
public class Repository {

    /**
     * Tag for log
     */
    private static final String TAG = Repository.class.getSimpleName();

    private final WeatherResponseDao weatherResponseDao;
    private final WeatherDao weatherDao;

    public Repository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        weatherResponseDao = db.weatherResponseDao();
        weatherDao = db.weatherDao();
    }

    public LiveData<WeatherResponse> getWeatherResponse(@NonNull Context context, final int cityId) {
        // Creating necessary data
        final Map<String, String> data = new HashMap<>();
        data.put("id", String.valueOf(cityId));
        data.put("appid", context.getString(R.string.appid));
        Log.e(TAG, Integer.toString(cityId));
        Log.e(TAG, String.valueOf(cityId));
        Log.e(TAG, context.getString(R.string.appid));
        // Requesting for data from external API
        ApiUtils.getOpenWeatherMapService().weather(data).enqueue(getWeatherCallback());

        return weatherResponseDao.loadByCityId(cityId);
    }

    public void insert(WeatherResponse weatherResponse) {
        // Saving WeatherResponse
        new InsertWeatherResponseAsyncTask(weatherResponseDao).execute(weatherResponse);
//        // Saving Weather
//        new InsertWeatherAsyncTask(weatherDao).execute(weatherResponse.getWeather());
    }

    /**
     * Creates and returns callback for retrofit enqueue method.
     *
     * @return - Callback<WeatherResponse>
     */
    private Callback<WeatherResponse> getWeatherCallback() {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "Data retrieved for " + response.body().getName());
                    WeatherResponse weatherResponse = new WeatherResponse();
                    // Setting data
                    weatherResponse.setCod(response.body().getCod());
                    weatherResponse.setId(response.body().getId());
                    weatherResponse.setName(response.body().getName());
                    weatherResponse.setTimezone(response.body().getTimezone());
                    weatherResponse.setDt(response.body().getDt());
                    weatherResponse.setVisibility(response.body().getVisibility());
                    weatherResponse.setBase(response.body().getBase());

                    Main main = new Main();
                    main.setTemp(response.body().getMain().getTemp());
                    main.setPressure(response.body().getMain().getPressure());
                    main.setHumidity(response.body().getMain().getHumidity());
                    main.setTemp_min(response.body().getMain().getTemp_min());
                    main.setTemp_max(response.body().getMain().getTemp_max());

                    Coord coord = new Coord();
                    coord.setLon(response.body().getCoord().getLon());
                    coord.setLat(response.body().getCoord().getLat());

                    Weather weather = new Weather();
                    weather.setId(response.body().getWeather()[0].getId());
                    weather.setMain(response.body().getWeather()[0].getMain());
                    weather.setDescription(response.body().getWeather()[0].getDescription());
                    weather.setIcon(response.body().getWeather()[0].getIcon());

                    Wind wind = new Wind();
                    wind.setSpeed(response.body().getWind().getSpeed());
                    wind.setDeg(response.body().getWind().getDeg());

                    Clouds clouds = new Clouds();
                    clouds.setAll(response.body().getClouds().getAll());

                    Sys sys = new Sys();
                    sys.setType(response.body().getSys().getType());
                    sys.setId(response.body().getSys().getId());
                    sys.setCountry(response.body().getSys().getCountry());
                    sys.setSunrise(response.body().getSys().getSunrise());
                    sys.setSunset(response.body().getSys().getSunset());

                    weatherResponse.setMain(main);
                    weatherResponse.setCoord(coord);
                    weatherResponse.setWeather(new Weather[]{weather});
                    weatherResponse.setWind(wind);
                    weatherResponse.setClouds(clouds);
                    weatherResponse.setSys(sys);

                    // Saving to database
                    insert(weatherResponse);
                } else {
                    // handle request errors depending on status code
                    Log.e(TAG, "Error status code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e(TAG, "error loading from API: " + t.getMessage());
            }
        };
    }

    /**
     * {@link InsertWeatherResponseAsyncTask} inserts data to database in background thread extending
     * {@link AsyncTask}.
     */
    private static class InsertWeatherResponseAsyncTask extends AsyncTask<WeatherResponse, Void, Void> {

        WeatherResponseDao mAsyncTaskDao;

        InsertWeatherResponseAsyncTask(WeatherResponseDao weatherResponseDao) {
            mAsyncTaskDao = weatherResponseDao;
        }

        @Override
        protected Void doInBackground(final WeatherResponse... params) {
            mAsyncTaskDao.insertAll(params);
            return null;
        }
    }

    /**
     * {@link InsertWeatherAsyncTask} inserts data to database in background thread extending
     * {@link AsyncTask}.
     */
    private static class InsertWeatherAsyncTask extends AsyncTask<Weather, Void, Void> {

        WeatherDao mAsyncTaskDao;

        InsertWeatherAsyncTask(WeatherDao weatherDao) {
            mAsyncTaskDao = weatherDao;
        }

        @Override
        protected Void doInBackground(final Weather... params) {
            mAsyncTaskDao.insert(params);
            return null;
        }
    }
}