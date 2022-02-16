package fr.dawoox.akasuki.utils.API.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    public final String name;
    public final String region;
    public final String country;
    public final short lat;
    public final short lon;
    public final String tz_id;
    public final long localtime_epoch;
    public final String localtime;

    public Location(@JsonProperty("name") String name,
                    @JsonProperty("region") String region,
                    @JsonProperty("country") String country,
                    @JsonProperty("lat") short lat,
                    @JsonProperty("lon") short lon,
                    @JsonProperty("tz_id") String tz_id,
                    @JsonProperty("localtime_epoch") long localtime_epoch,
                    @JsonProperty("locatime") String localtime) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.tz_id = tz_id;
        this.localtime_epoch = localtime_epoch;
        this.localtime = localtime;
    }

}
