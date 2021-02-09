package fr.dawoox.akasuki.utils.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Condition {
    public final String text;
    public final String icon;

    public Condition(@JsonProperty("text") String text,
                     @JsonProperty("icon") String icon) {
        this.text = text;
        this.icon = icon;
    }
}
