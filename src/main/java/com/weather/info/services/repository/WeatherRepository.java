package com.weather.info.services.repository;

import com.weather.info.services.model.Weather;
import org.springframework.data.repository.CrudRepository;

/**
 * @author bnaragani created on 13/08/2021
 */
public interface WeatherRepository extends CrudRepository<Weather, String> {

}
