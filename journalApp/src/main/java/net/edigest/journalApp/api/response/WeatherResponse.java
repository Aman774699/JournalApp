package net.edigest.journalApp.api.response;

import lombok.Data;
import lombok.ToString;

import  java.util.ArrayList;

@ToString
@Data
public class WeatherResponse {
    private Current current;

    public class Current{
        public String observation_time;
        public int temperature;
        public int weather_code;

        public ArrayList<String> weather_descriptions;

        public int feelslike;

    }

}



