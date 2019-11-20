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

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import vkurman.openweathermapapp.model.WeatherResponse;

/**
 * {@link WeatherResponseDao} is {@link Dao} interface
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
@Dao
public interface WeatherResponseDao {

    @Query("SELECT * FROM weatherresponses")
    LiveData<List<WeatherResponse>> getAll();

    @Query("SELECT * FROM weatherresponses WHERE id = :cityId LIMIT 1")
    LiveData<WeatherResponse> loadByCityId(int cityId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(WeatherResponse... weatherResponses);

    @Delete
    void delete(WeatherResponse weatherResponse);
}