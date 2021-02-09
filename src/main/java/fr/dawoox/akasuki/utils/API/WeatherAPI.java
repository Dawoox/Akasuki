package fr.dawoox.akasuki.utils.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawoox.akasuki.commands.CommandException;
import fr.dawoox.akasuki.data.Config;
import fr.dawoox.akasuki.utils.json.WeatherBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {
    private static final String API_KEY = Config.WEATHER_API_KEY;

    public static WeatherBody requestWeather(String city) {
        WeatherBody weather = null;

        try {
            URL url = new URL("https://api.weatherapi.com/v1/current.json?key="+API_KEY+"&q="+city.replace(" ", "%20")+"&lang=fr");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            weather = new ObjectMapper().readValue(responseStream, WeatherBody.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("That city seems to not exist : " + city);
        }

        return weather;
    }
}
