package com.weather.info.services.controller;

import com.weather.info.services.model.Weather;
import com.weather.info.services.service.WeatherService;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bnaragani created on 13/08/2021
 */
@RestController
@RequestMapping("/api")
public class WeatherApiController {

    @Autowired
    WeatherService weatherService;

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ResponseEntity<Optional<Weather>> getWeather(@RequestParam("city") String city, @RequestParam("key") String key)
            throws Exception {
        if (!weatherService.validateKey(key)) {
            return new ResponseEntity("Invalid Key", HttpStatus.FORBIDDEN);
        }
        if (!weatherService.getWeather(city, key)) {
            return new ResponseEntity("Error! No data Found!", HttpStatus.FORBIDDEN);
        }
        if (city.contains(",")) {
            city = city.substring(0, city.indexOf(","));
        }

        Optional<Weather> wt = weatherService.getWeatherByCity(city.toUpperCase(Locale.ROOT));

        if (wt.toString().equals("Optional.empty")) {
            return new ResponseEntity("No Data Found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Optional<Weather>>(wt, HttpStatus.OK);
    }


}
