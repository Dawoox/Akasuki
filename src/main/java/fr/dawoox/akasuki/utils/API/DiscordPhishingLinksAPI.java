package fr.dawoox.akasuki.utils.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawoox.akasuki.utils.API.json.DiscordPhisingLinks.DiscordPhishingLinksBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DiscordPhishingLinksAPI {

    public static DiscordPhishingLinksBody requestDomains() {
        DiscordPhishingLinksBody body = null;

        try {
            URL url = new URL("https://raw.githubusercontent.com/nikolaischunk/discord-phishing-links/main/domain-list.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            body = new ObjectMapper().readValue(responseStream, DiscordPhishingLinksBody.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

}
