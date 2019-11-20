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
package vkurman.openweathermapapp.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import vkurman.openweathermapapp.model.ForecastResponse;
import vkurman.openweathermapapp.model.WeatherResponse;

/**
 * {@link OpenWeatherMapService} is an interface that contains methods
 * that are used to execute HTTP requests such as GET, POST, PUT, PATCH
 * and DELETE.
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
public interface OpenWeatherMapService {

    /**
     * {@link GET} request to retrieve weather data for specified location.
     *
     * @param options - should include "appid" as minimum
     * @return Call<WeatherResponse>
     */
    @GET("weather")
    Call<WeatherResponse> weather(@QueryMap Map<String, String> options);

    /**
     * {@link GET} request to retrieve forecast data for specified location.
     *
     * @param options - should include "appid" as minimum
     * @return Call<ForecastResponse>
     */
    @GET("forecast")
    Call<ForecastResponse> forecast(@QueryMap Map<String, String> options);

}