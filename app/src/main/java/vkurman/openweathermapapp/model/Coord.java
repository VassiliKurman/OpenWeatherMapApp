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
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {@link Coord} are coordinates for {@link WeatherResponse}.
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
@Entity
public class Coord {

    @PrimaryKey
    public int coord_id;
    /**
     * City geo location, longitude
     */
    @SerializedName("lon")
    @Expose
    @ColumnInfo(name = "coord_lon")
    private float lon;

    /**
     * City geo location, latitude
     */
    @SerializedName("lat")
    @Expose
    @ColumnInfo(name = "coord_lat")
    private float lat;

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}