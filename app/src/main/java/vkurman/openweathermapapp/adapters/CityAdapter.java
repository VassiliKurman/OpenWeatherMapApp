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
package vkurman.openweathermapapp.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import vkurman.openweathermapapp.R;
import vkurman.openweathermapapp.model.City;

/**
 * {@link CityAdapter} is adapter for {@link android.widget.Spinner}
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
public class CityAdapter extends ArrayAdapter<City> {

    private LayoutInflater inflater;
    private List<City> cities;

    public CityAdapter(@NonNull Activity context, int resource, int viewId, @NonNull List<City> cities) {
        super(context, resource, viewId, cities);
        this.cities = cities;
        this.inflater = context.getLayoutInflater();
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.city_layout, parent, false);
        }

        City city = getItem(position);
        if(city != null) {
            TextView txtName = convertView.findViewById(R.id.city_name);
            txtName.setText(city.getName());
        }
        return convertView;
    }

    /**
     * Returns city at specified position
     *
     * @param position - int
     * @return City
     */
    public City getCity(int position) {
        if(cities == null || cities.isEmpty()) {
            return null;
        }
        return cities.get(position);
    }

    /**
     * Updates list of cities.
     *
     * @param cities - List<City>
     */
    public void updateCities(List<City> cities) {
        this.cities = cities;
        Log.e(this.getClass().getSimpleName(), "Cities in adapter: " + this.cities.size());
        notifyDataSetChanged();
    }
}