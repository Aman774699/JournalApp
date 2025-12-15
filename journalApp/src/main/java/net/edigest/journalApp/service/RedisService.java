package net.edigest.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.edigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public  <T>T get(String key, Class<T>entityclass)
    {
        try {
            String jsonValue = (String) redisTemplate.opsForValue().get(key);

            if (jsonValue == null) {
                return null;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonValue, entityclass);

        } catch (Exception e) {
            log.error("Exception in getting the value", e);
            return null;
        }
    }

    public  void set(String key,Object o,Long ttl)
    {
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            String  jasonValue=objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key,jasonValue,ttl, TimeUnit.MINUTES);
        }
        catch (Exception e)
        {
            log.error("Exception in setting the value",e);
        }
    }
}
