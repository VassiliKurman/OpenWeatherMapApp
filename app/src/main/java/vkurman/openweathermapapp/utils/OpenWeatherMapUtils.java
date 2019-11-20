
package vkurman.openweathermapapp.utils;

public class OpenWeatherMapUtils {

    private static final String BASE_ICON_URL = "http://openweathermap.org/img/wn/";
    private static final String BASE_ICON_EXTENSION = "@2x.png";

    public static String createIconPath(String icon) {
        return BASE_ICON_URL + icon + BASE_ICON_EXTENSION;
    }

}