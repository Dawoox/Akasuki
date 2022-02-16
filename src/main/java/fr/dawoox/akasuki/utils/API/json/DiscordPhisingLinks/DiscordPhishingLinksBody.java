package fr.dawoox.akasuki.utils.API.json.DiscordPhisingLinks;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscordPhishingLinksBody {
    public final String[] domains;

    public DiscordPhishingLinksBody(@JsonProperty("domains") String[] domains) {
        this.domains = domains;
    }

}
