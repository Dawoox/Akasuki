package fr.dawoox.akasuki.utils.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Current {
    public final String last_updated;
    public final byte temp_c;
    public final boolean is_day;
    public final Condition condition;
    public final short wind_kph;
    public final short humidity;
    public final byte uv;

    public Current(@JsonProperty("last_updated") String last_updated,
                   @JsonProperty("temp_c") byte temp_c,
                   @JsonProperty("is_day") byte is_day,
                   @JsonProperty("condition") Condition condition,
                   @JsonProperty("wind_kph") short wind_kph,
                   @JsonProperty("humidity") short humidity,
                   @JsonProperty("uv") byte uv) {
        this.last_updated = last_updated;
        this.temp_c = temp_c;
        if (is_day == 1) {
            this.is_day = true;
        } else {
            this.is_day = false;
        }
        this.condition = condition;
        this.wind_kph = wind_kph;
        this.humidity = humidity;
        this.uv = uv;
    }
}
