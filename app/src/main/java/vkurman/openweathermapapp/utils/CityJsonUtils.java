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
package vkurman.openweathermapapp.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vkurman.openweathermapapp.model.City;

/**
 * Small utility class
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
public class CityJsonUtils {

    /**
     * Tag for Log
     */
    private static final String TAG = CityJsonUtils.class.getSimpleName();

    // Review name fields from json file
    private static final String JSON_ID = "id";
    private static final String JSON_NAME = "name";
    private static final String JSON_COUNTRY = "country";
    private static final String JSON_COORDINATES = "coord";
    private static final String JSON_LATITUDE = "lat";
    private static final String JSON_LONGITUDE = "lon";

    /**
     * Fetches and returns list of movies from json string.
     *
     * @param json - string in json format
     * @return List<Movie>
     */
    public static List<City> parseCityJson(String json) {

        final List<City> cities = new ArrayList<>();

        try {
            // Getting json array results from json object
            JSONArray array = new JSONArray(json);
            if(array.length() > 0) {
                Log.e(TAG, "Objects in json results array: " + array.length());
                for(int i = 0; i < array.length(); i++) {
                    JSONObject cityJsonObject = array.optJSONObject(i);
                    // Getting individual values from json object
                    int id = cityJsonObject.optInt(JSON_ID);
                    String name = cityJsonObject.optString(JSON_NAME);
//                    String country = cityJsonObject.optString(JSON_COUNTRY);
//                    JSONObject coordinates = cityJsonObject.optJSONObject(JSON_COORDINATES);
//                    double lon = coordinates.optDouble(JSON_LONGITUDE);
//                    double lat = coordinates.optDouble(JSON_LATITUDE);

//                    cities.add(new City(id, name, country, lon, lat));
                    cities.add(new City(id, name, "", 0, 0));
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parse City Json: " + e);
        }

        return cities;
    }
}