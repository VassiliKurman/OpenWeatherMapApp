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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * {@link WeatherResponse} holds data that is delivered from the weather call for
 * a specific location
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
public class WeatherResponse {

    /**
     * City ID
     */
    @SerializedName("id")
    @Expose
    private int id;

    /**
     * City name
     */
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * Shift in seconds from UTC
     */
    @SerializedName("timezone")
    @Expose
    private int timezone;

    /**
     * Internal parameter
     */
    @SerializedName("cod")
    @Expose
    private int cod;

    /**
     * Internal parameter
     */
    @SerializedName("base")
    @Expose
    private String base;

    /**
     * Visibility
     */
    @SerializedName("visibility")
    @Expose
    private int visibility;

    /**
     * Time of data calculation, unix, UTC
     */
    @SerializedName("dt")
    @Expose
    private long dt;

    /**
     * Location coordinates
     */
    @SerializedName("coord")
    @Expose
    private Coord coord;

    /**
     * Weather data
     */
    @SerializedName("weather")
    @Expose
    private Weather[] weather;

    /**
     * Main data
     */
    @SerializedName("main")
    @Expose
    private Main main;

    /**
     * Wind data
     */
    @SerializedName("wind")
    @Expose
    private Wind wind;

    /**
     * Data about clouds
     */
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    /**
     * System data
     */
    @SerializedName("sys")
    @Expose
    private Sys sys;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }
}