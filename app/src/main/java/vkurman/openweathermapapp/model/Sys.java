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
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {@link Sys} data for {@link WeatherResponse}.
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
@Entity
public class Sys {

    @PrimaryKey
    public int sys_primary_id;

    /**
     * Internal parameter
     */
    @SerializedName("type")
    @Expose
    @ColumnInfo(name = "sys_type")
    private int type;

    /**
     * Internal parameter
     */
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "sys_id")
    private long id;

    /**
     * Internal parameter
     */
    @SerializedName("message")
    @Expose
    @ColumnInfo(name = "sys_message")
    private double message;

    /**
     * Country code (GB, JP etc.)
     */
    @SerializedName("country")
    @Expose
    @ColumnInfo(name = "sys_country")
    private String country;

    /**
     * Sunrise time, unix, UTC
     */
    @SerializedName("sunrise")
    @Expose
    @ColumnInfo(name = "sys_sunrise")
    private long sunrise;

    /**
     * Sunset time, unix, UTC
     */
    @SerializedName("sunset")
    @Expose
    @ColumnInfo(name = "sys_sunset")
    private long sunset;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
}