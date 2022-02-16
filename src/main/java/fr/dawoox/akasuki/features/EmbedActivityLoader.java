package fr.dawoox.akasuki.features;

import discord4j.core.object.entity.channel.VoiceChannel;
import fr.dawoox.akasuki.data.Config;
import fr.dawoox.akasuki.utils.EmbedActivities;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class EmbedActivityLoader {
    private static String URL_TEMPLATE = "https://discord.com/api/v8/channels/%s/invites";

    public static String requestActivityInvite(VoiceChannel channel, EmbedActivities activity) {
        try {
            URL url = new URL(URL_TEMPLATE.replace("%s", channel.getId().asString()));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bot "+ Config.TOKEN);
            con.setRequestProperty("Accept", "application/json");

            con.setDoOutput(true);

            String jsonInputString = "{\n" +
                    "    \"max_ages\": 3600,\n" +
                    "    \"target_type\": 2,\n" +
                    "    \"target_application_id\": " + activity.toString() + ",\n" +
                    "    \"unique\": true\n" +
                    "}";

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(con.getInputStream());

            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject body = (JSONObject) new JSONParser().parse(inline.toString());
            return (String) body.get("code");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
