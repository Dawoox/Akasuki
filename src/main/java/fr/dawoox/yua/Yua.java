package fr.dawoox.yua;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import fr.dawoox.yua.commands.gifs.Hug;
import fr.dawoox.yua.commands.gifs.Kiss;
import fr.dawoox.yua.commands.misc.Ping;
import fr.dawoox.yua.commands.misc.UserInfo;
import fr.dawoox.yua.commands.music.Join;
import fr.dawoox.yua.commands.music.Play;
import fr.dawoox.yua.utils.Command;
import fr.dawoox.yua.utils.ConfigReader;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Yua {

    private static final Map<String, Command> commands = new HashMap<>();
    private static final String prefix = "*";

    public static void main(String[] args) {

        final String version = "0.5.4";
        final String token = ConfigReader.getToken();
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient g = client.login().block();


        g.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    final String content = event.getMessage().getContent();
                    for (final Map.Entry<String, Command> entry : commands.entrySet()) {
                        if (content.startsWith(prefix + entry.getKey())) {
                            entry.getValue().execute(event);
                            break;
                        }
                    }
                });


        g.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(readyEvent -> {
                    LoggerFactory.getLogger(Yua.class).info("dawoox.yua.Yua Shard Initialing ");
                    commands.clear();

                    Ping.reg(commands);
                    Join.reg(commands);
                    Play.reg(commands);
                    UserInfo.reg(commands);
                    Kiss.reg(commands);
                    Hug.reg(commands);
                    LoggerFactory.getLogger(Yua.class).info("Commands Initialized");
                    LoggerFactory.getLogger(Yua.class).info("dawoox.yua.Yua Shard Connected");

                    g.updatePresence(Presence.online(Activity.watching("la version " + version))).block();
                });

        g.onDisconnect().block();
    }
}
