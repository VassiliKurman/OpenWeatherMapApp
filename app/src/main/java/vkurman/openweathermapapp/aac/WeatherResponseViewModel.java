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

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import vkurman.openweathermapapp.model.WeatherResponse;

/**
 * {@link WeatherResponseViewModel} is {@link AndroidViewModel} extension.
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
public class WeatherResponseViewModel extends AndroidViewModel {

    private Repository mRepository;

    private LiveData<WeatherResponse> mWeatherResponse;

    public WeatherResponseViewModel (Application application) {
        super(application);
        mRepository = new Repository(application);
        mWeatherResponse = mRepository.getWeatherResponse(application.getApplicationContext(), 2643743);
    }

    public LiveData<WeatherResponse> getWeatherResponse() {
        return mWeatherResponse;
    }

    public void insert(WeatherResponse weatherResponse) {
        mRepository.insert(weatherResponse);
    }
}