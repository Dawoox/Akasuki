package fr.dawoox.akasuki.utils.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Condition {
    public final String text;
    public final String icon;
    public final short code;

    public Condition(@JsonProperty("text") String text,
                     @JsonProperty("icon") String icon,
                     @JsonProperty("code") short code) {
        this.text = text;
        this.icon = icon;
        this.code = code;
    }
}
