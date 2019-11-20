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
 * {@link Wind} holds data about wind for {@link WeatherResponse}.
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
@Entity
public class Wind {

    @PrimaryKey
    public int wind_id;

    /**
     * Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
     */
    @SerializedName("speed")
    @Expose
    @ColumnInfo(name = "wind_speed")
    private float speed;
    /**
     * Wind direction, degrees (meteorological)
     */
    @SerializedName("deg")
    @Expose
    @ColumnInfo(name = "wind_deg")
    private int deg;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}