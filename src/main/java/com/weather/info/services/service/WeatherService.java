package com.weather.info.services.service;

import com.weather.info.services.model.Weather;
import java.io.IOException;
import java.util.Optional;

/**
 * @author bnaragani created on 13/08/2021
 */
public interface WeatherService {

    boolean getWeather(String city, String key) throws IOException;

    boolean validateKey(String key) throws Exception;

    Optional<Weather> getWeatherByCity(String city);

}
