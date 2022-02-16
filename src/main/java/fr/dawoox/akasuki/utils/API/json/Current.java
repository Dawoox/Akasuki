package fr.dawoox.akasuki.utils.API.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Current {
    public final int last_updated_epoch;
    public final String last_updated;
    public final byte temp_c;
    public final String temp_f;
    public final boolean is_day;
    public final Condition condition;
    public final short wind_mph;
    public final short wind_kph;
    public final short wind_degree;
    public final String wind_dir;
    public final short pressure_mb;
    public final short pressure_in;
    public final short precip_mm;
    public final short precip_in;
    public final short humidity;
    public final byte cloud;
    public final byte feelslike_c;
    public final short feelslike_f;
    public final short vis_km;
    public final short vis_miles;
    public final byte uv;
    public final short gust_mph;
    public final short gust_kph;

    public Current(@JsonProperty("last_updated_epoch") int last_updated_epoch,
                   @JsonProperty("last_updated") String last_updated,
                   @JsonProperty("temp_c") byte temp_c,
                   @JsonProperty("temp_f") String temp_f,
                   @JsonProperty("is_day") byte is_day,
                   @JsonProperty("condition") Condition condition,
                   @JsonProperty("wind_mph") short wind_mph,
                   @JsonProperty("wind_kph") short wind_kph,
                   @JsonProperty("wind_degree") short wind_degree,
                   @JsonProperty("wind_dir") String wind_dir,
                   @JsonProperty("pressure_mb") short pressure_mb,
                   @JsonProperty("pressure_in") short pressure_in,
                   @JsonProperty("precip_mm") short precip_mm,
                   @JsonProperty("precip_in") short precip_in,
                   @JsonProperty("humidity") short humidity,
                   @JsonProperty("cloud") byte cloud,
                   @JsonProperty("feelslike_c") byte feelslike_c,
                   @JsonProperty("feelslike_f") short feelslike_f,
                   @JsonProperty("vis_km") short vis_km,
                   @JsonProperty("vis_miles") short vis_miles,
                   @JsonProperty("uv") byte uv,
                   @JsonProperty("gust_mph") short gust_mph,
                   @JsonProperty("gust_kph") short gust_kph) {
        this.last_updated_epoch = last_updated_epoch;
        this.last_updated = last_updated;
        this.temp_c = temp_c;
        this.temp_f = temp_f;
        if (is_day == 1) {
            this.is_day = true;
        } else {
            this.is_day = false;
        }
        this.condition = condition;
        this.wind_mph = wind_mph;
        this.wind_kph = wind_kph;
        this.wind_degree = wind_degree;
        this.wind_dir = wind_dir;
        this.pressure_mb = pressure_mb;
        this.pressure_in = pressure_in;
        this.precip_mm = precip_mm;
        this.precip_in = precip_in;
        this.humidity = humidity;
        this.cloud = cloud;
        this.feelslike_c = feelslike_c;
        this.feelslike_f = feelslike_f;
        this.vis_km = vis_km;
        this.vis_miles = vis_miles;
        this.uv = uv;
        this.gust_mph = gust_mph;
        this.gust_kph = gust_kph;
    }
}
