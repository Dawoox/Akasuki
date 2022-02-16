package fr.dawoox.akasuki.core;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.*;
import fr.dawoox.akasuki.data.Config;
import fr.dawoox.akasuki.data.Maven;;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

public class ActivityManager extends Thread{

    public ActivityManager() { setName("ActivityManager"); }

    public void run(GatewayDiscordClient gateway) {
        JSONObject JSON = null;
        try {
            String inline = "";
            Scanner scanner = new Scanner(new FileInputStream("activities.json"));
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            scanner.close();

            JSON = (JSONObject) new JSONParser().parse(inline);
        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        }

        assert JSON != null;

        JSONArray activities = (JSONArray) JSON.get("activities");
        JSONObject config = (JSONObject) JSON.get("config");
        int rollout = Integer.parseInt(config.get("rollout_time").toString()) * 1000;
        String type = config.get("type").toString();
        int state = 1;

        while (true) {
            String activity = activities.get(state).toString().replace("%v", Maven.PROJECT_VERSION).replace("%gl", Config.GITHUB_URL.substring(8));
            switch (type) {
                case "watching":
                    gateway.updatePresence(ClientPresence.online(ClientActivity.watching(activity))).block();
                    break;

                case "listening":
                    gateway.updatePresence(ClientPresence.online(ClientActivity.listening(activity))).block();
                    break;

                case "competing":
                    gateway.updatePresence(ClientPresence.online(ClientActivity.competing(activity))).block();
                    break;

                case "playing":
                    gateway.updatePresence(ClientPresence.online(ClientActivity.playing(activity))).block();
                    break;

                default:
                    gateway.updatePresence(ClientPresence.online(ClientActivity.watching("Invalid Activity Type !"))).block();
                    break;
            }

            try {
                sleep(rollout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (state >= activities.size()-1) {
                state=0;
            } else {
                state++;
            }
        }
    }

}
