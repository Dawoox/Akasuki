package fr.dawoox.akasuki.utils.API.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherBody {
    public final Location location;
    public final Current current;

    public WeatherBody(@JsonProperty("location") Location location,
                       @JsonProperty("current") Current current) {
        this.location = location;
        this.current = current;
    }
}
