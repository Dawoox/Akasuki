package fr.dawoox.akasuki.core.thread;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import fr.dawoox.akasuki.data.Config;
import fr.dawoox.akasuki.data.Maven;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class ActivityManager extends Thread{

    public ActivityManager() { setName("ActivityManager"); }

    public void run(GatewayDiscordClient gateway) {
        JSONObject JSON = null;
        try {
            JSON = new JSONObject(readStream(new FileInputStream("C:\\Akasuki\\activities.json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert JSON != null;

        JSONArray activities = JSON.getJSONArray("activities");
        int rollout = JSON.getJSONObject("config").getInt("rollout_time") * 1000;
        String type = JSON.getJSONObject("config").getString("type");
        int state = 1;

        while (true) {
            switch (type) {
                case "watching":
                    gateway.updatePresence(Presence.online(Activity.watching(activities.get(state).toString()
                            .replace("%v", Maven.PROJECT_VERSION).replace("%gl", Config.GITHUB_URL)))).block();
                    break;

                case "listening":
                    gateway.updatePresence(Presence.online(Activity.listening(activities.get(state).toString()
                            .replace("%v", Maven.PROJECT_VERSION).replace("%gl", Config.GITHUB_URL)))).block();
                    break;

                case "competing":
                    gateway.updatePresence(Presence.online(Activity.competing(activities.get(state).toString()
                            .replace("%v", Maven.PROJECT_VERSION).replace("%gl", Config.GITHUB_URL)))).block();
                    break;

                case "playing":
                    gateway.updatePresence(Presence.online(Activity.playing(activities.get(state).toString()
                            .replace("%v", Maven.PROJECT_VERSION).replace("%gl", Config.GITHUB_URL)))).block();
                    break;

                default:
                    gateway.updatePresence(Presence.online(Activity.watching("Invalid Activity Type /!\\"))).block();
                    break;
            }

            try {
                sleep(rollout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (state >= activities.length()) {
                state=1;
            } else {
                state++;
            }
        }
    }

    public static String readStream(InputStream is) {
        StringBuilder sb = new StringBuilder(512);
        try {
            Reader r = new InputStreamReader(is, "UTF-8");
            int c = 0;
            while ((c = r.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}
