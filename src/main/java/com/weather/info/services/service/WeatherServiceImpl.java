package com.weather.info.services.service;

import com.weather.info.services.model.ApiKey;
import com.weather.info.services.model.Weather;
import com.weather.info.services.repository.WeatherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author bnaragani created on 13/08/2021
 */
@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {


    private static HashMap<String, ApiKey> apiKeyData = new HashMap<>();

    private static final String key1 = "a11a2e24cfb3e9e4af5e5e7acf471967";
    private static final String key2 = "9fbd1a30db0523198a5fd64a77d80e05";
    private static final String key3 = "470f7a381961230cbf33ccccd631100e";
    private static final String key4 = "f497c169876ac888bc377a0b11820413";
    private static final String key5 = "5cfc685a3eb844cff967722f8527323b";


    @Autowired
    WeatherRepository weatherRepository;


    @Override
    public boolean getWeather(String city, String Key) throws IOException {
        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/weather")
                .path("")
                .query("q={keyword}&appid={appid}")
                .buildAndExpand(city, Key);

        String uri = uriComponents.toUriString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        ObjectMapper mapper = new ObjectMapper();
        Weather weather = mapper.readValue(resp.getBody(), Weather.class);
        weather.setName(weather.getName().toUpperCase(Locale.ROOT));
        saveOrUpdate(weather);
        return true;
    }

    @Override
    public boolean validateKey(String key) throws Exception {
        switch (key) {
            case key1:
                if (apiKeyData.containsKey(key1)) {
                    return isRateTimeNotReached(apiKeyData.get(key1));
                } else {
                    ApiKey apiKey = new ApiKey(key1, 1, new Date());
                    apiKeyData.put(key1, apiKey);
                }
                break;
            case key2:
                if (apiKeyData.containsKey(key2)) {
                    return isRateTimeNotReached(apiKeyData.get(key2));
                } else {
                    ApiKey apiKey = new ApiKey(key2, 1, new Date());
                    apiKeyData.put(key2, apiKey);
                }
                break;
            case key3:
                if (apiKeyData.containsKey(key3)) {
                    return isRateTimeNotReached(apiKeyData.get(key3));
                } else {
                    ApiKey apiKey = new ApiKey(key3, 1, new Date());
                    apiKeyData.put(key3, apiKey);
                }
                break;
            case key4:
                if (apiKeyData.containsKey(key4)) {
                    return isRateTimeNotReached(apiKeyData.get(key4));
                } else {
                    ApiKey apiKey = new ApiKey(key4, 1, new Date());
                    apiKeyData.put(key4, apiKey);
                }
                break;
            case key5:
                if (apiKeyData.containsKey(key5)) {
                    return isRateTimeNotReached(apiKeyData.get(key5));
                } else {
                    ApiKey apiKey = new ApiKey(key5, 1, new Date());
                    apiKeyData.put(key5, apiKey);
                }
                break;
            default:
                return false;
        }

        return true;
    }

    private boolean isRateTimeNotReached(ApiKey apiKey) {
        if (apiKey.getCount() == 5) {
            long differenceInMilliSeconds = Math.abs(new Date().getTime() - apiKey.getKeyDate().getTime());
            double differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
            if (differenceInHours > 1) {
                apiKey.setCount(1);
                return true;
            } else {
                return false;
            }
        }
        int count = apiKey.getCount();
        apiKey.setCount(++count);
        return true;
    }


    public Optional<Weather> getWeatherByCity(String city) {
        return weatherRepository.findById(city);
    }

    public void saveOrUpdate(Weather wt) {
        weatherRepository.save(wt);
    }

}
