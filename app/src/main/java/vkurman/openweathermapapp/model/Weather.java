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
package vkurman.openweathermapapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {@link Weather} data for {@link WeatherResponse}.
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
@Entity(tableName = "weather",
        indices = {@Index("weather_id")})
public class Weather {

    /**
     * Weather condition id
     */
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "weather_id")
    private long id;

    /**
     * Group of weather parameters (Rain, Snow, Extreme etc.)
     */
    @SerializedName("main")
    @Expose
    @ColumnInfo(name = "weather_main")
    private String main;

    /**
     * Weather condition within the group
     */
    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "weather_description")
    private String description;

    /**
     * Weather icon id
     */
    @SerializedName("icon")
    @Expose
    @ColumnInfo(name = "weather_icon")
    private String icon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}