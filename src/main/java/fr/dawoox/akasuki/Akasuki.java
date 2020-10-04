package fr.dawoox.akasuki;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import fr.dawoox.akasuki.commands.gifs.Hug;
import fr.dawoox.akasuki.commands.gifs.Kiss;
import fr.dawoox.akasuki.commands.misc.Ping;
import fr.dawoox.akasuki.commands.misc.UserInfo;
import fr.dawoox.akasuki.commands.misc.Emoji;
import fr.dawoox.akasuki.commands.music.Join;
import fr.dawoox.akasuki.commands.music.Play;
import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.ConfigReader;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Akasuki {

    private static final Map<String, Command> commands = new HashMap<>();
    private static final String prefix = "*";

    public static void main(String[] args) {
        final String version = "0.5.4";
        final String TOKEN = ConfigReader.getEntry("token");
        final DiscordClient client = DiscordClient.create(TOKEN);
        final GatewayDiscordClient g = client.login().block();

        assert g != null;
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
                    LoggerFactory.getLogger(Akasuki.class).info("Akasuki Shard Initialing ");
                    commands.clear();

                    Ping.reg(commands);
                    Join.reg(commands);
                    Play.reg(commands);
                    UserInfo.reg(commands);
                    Kiss.reg(commands);
                    Hug.reg(commands);
                    Emoji.reg(commands);
                    LoggerFactory.getLogger(Akasuki.class).info("Commands Initialized");
                    LoggerFactory.getLogger(Akasuki.class).info("Akasuki Shard Connected");

                    g.updatePresence(Presence.online(Activity.watching("la version " + version))).block();
                });

        g.onDisconnect().block();
    }
}