package net.edigest.journalApp.service;

import net.edigest.journalApp.api.response.WeatherResponse;
import net.edigest.journalApp.cachehandler.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WheatherApiService {

    @Autowired
    private  RestTemplate restTemplate;

    @Value("${API.weather.key}")
    private  String apikey;
    @Autowired
    Cache cache;
    @Autowired
    RedisService redisService;
    public  WeatherResponse getWeather(String city)
    {
        WeatherResponse weatherResponse=redisService.get("Weather_of_city"+city,WeatherResponse.class);
        if (weatherResponse!=null)
        {
            return weatherResponse;
        }
        else {
            String finalAPl = cache.apis.get("Weather_API").toString();
            finalAPl = finalAPl.replace("<city>", city).replace("<apikey>", apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPl, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body!=null)
            {
                redisService.set("weather_of"+city,body,300l);
            }
            return body;
        }
    }
}
