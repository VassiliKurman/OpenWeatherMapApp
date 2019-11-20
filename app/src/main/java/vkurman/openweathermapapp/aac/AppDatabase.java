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

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import vkurman.openweathermapapp.model.Clouds;
import vkurman.openweathermapapp.model.Coord;
import vkurman.openweathermapapp.model.Main;
import vkurman.openweathermapapp.model.Sys;
import vkurman.openweathermapapp.model.Weather;
import vkurman.openweathermapapp.model.WeatherResponse;
import vkurman.openweathermapapp.model.Wind;

/**
 * {@link AppDatabase} is abstract class extension of {@link RoomDatabase}
 *
 * Created by Vassili Kurman on 20/11/2019.
 * Version 1.0
 */
@Database(entities = {
        WeatherResponse.class,
        Clouds.class,
        Coord.class,
        Main.class,
        Sys.class,
        Weather.class,
        Wind.class,}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String ROOM_DATABASE_NAME = "weather";

    public abstract WeatherResponseDao weatherResponseDao();

    public abstract WeatherDao weatherDao();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            ROOM_DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}