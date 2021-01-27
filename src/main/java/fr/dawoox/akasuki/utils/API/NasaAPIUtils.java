package fr.dawoox.akasuki.utils.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawoox.akasuki.data.Config;
import fr.dawoox.akasuki.utils.json.APOD;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for the NASA API
 * @author Dawoox
 * @version 1.1.1
 */
public class NasaAPIUtils {
    private static final String API_KEY = Config.NASA_API_KEY;

    /**
     * Return a APOD Object from the API
     * @return APOD
     *  APOD Object fill with information about the APOD image/video of the day.
     * @since 1.0.0
     */
    public static APOD requestAPOD(){
        URL url = null;
        HttpURLConnection connection = null;
        InputStream responseStream = null;
        ObjectMapper mapper = new ObjectMapper();
        APOD apod = null;

        try {
            url = new URL("https://api.nasa.gov/planetary/apod?api_key=" + API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection.setRequestProperty("accept", "application/json");

        try {
            responseStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            apod = mapper.readValue(responseStream, APOD.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apod;
    }
}
