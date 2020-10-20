package fr.dawoox.akasuki;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import fr.dawoox.akasuki.commands.gifs.Hug;
import fr.dawoox.akasuki.commands.gifs.Kiss;
import fr.dawoox.akasuki.commands.images.Apod;
import fr.dawoox.akasuki.commands.images.Stonks;
import fr.dawoox.akasuki.commands.images.Wanted;
import fr.dawoox.akasuki.commands.misc.Ping;
import fr.dawoox.akasuki.commands.misc.UserInfo;
import fr.dawoox.akasuki.commands.misc.Emoji;
import fr.dawoox.akasuki.commands.moderator.Ban;
import fr.dawoox.akasuki.commands.moderator.Kick;
import fr.dawoox.akasuki.commands.moderator.Mute;
import fr.dawoox.akasuki.commands.moderator.Unmute;
import fr.dawoox.akasuki.commands.music.Join;
import fr.dawoox.akasuki.commands.music.Play;
import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.ConfigReader;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Main class
 * @author Dawoox
 * @version 2.3.0
 */
public class Akasuki {

    private static final Map<String, Command> commands = new HashMap<>();
    private static final String prefix = "*";

    /**
     * Main function, call on startup.
     * @param args
     * Args are the supplied command-line argument.
     * @since 1.0.0
     */
    public static void main(String[] args) {
        final String TOKEN = ConfigReader.getEntry("token");
        final DiscordClient client = DiscordClient.create(TOKEN);
        final GatewayDiscordClient g = client.login().block();

        System.setProperty("http.agent", "Mozilla/5.0 (compatible; Discordbot/2.0; +https://discordapp.com)");
        assert g != null;

        //Get call when a new message is send in any guild where the bot is
        g.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    final String content = event.getMessage().getContent();
                    if (event.getMessage().getAuthor().get().isBot()) { return; }
                    for (final Map.Entry<String, Command> entry : commands.entrySet()) {
                        if (content.startsWith(prefix + entry.getKey())) {
                            entry.getValue().execute(event);
                            break;
                        }
                    }
                });


        //Get call when the bot start
        g.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(readyEvent -> {
                    LoggerFactory.getLogger(Akasuki.class).info("Akasuki Shard Initialing ");
                    commands.clear();

                    //Output all guild's name where Akasuki is
                    for (int i = 0; i< Objects.requireNonNull(Objects.requireNonNull(g.getGuilds().collectList().block())).size(); i++){
                        System.out.println(Objects.requireNonNull(g.getGuilds().collectList().block()).get(i).getName());
                    }

                    //Register all commands
                    Ping.reg(commands);
                    UserInfo.reg(commands);
                    Kiss.reg(commands);
                    Hug.reg(commands);
                    Ban.reg(commands);
                    Kick.reg(commands);
                    Mute.reg(commands);
                    Unmute.reg(commands);
                    Apod.reg(commands);

                    //Register beta commands if the server is in dev mode
                    if (args[0].equalsIgnoreCase("dev")){
                        Emoji.reg(commands);
                        Stonks.reg(commands);
                        Wanted.reg(commands);
                        Join.reg(commands);
                        Play.reg(commands);
                    }
                    LoggerFactory.getLogger(Akasuki.class).info("Commands Initialized");
                    LoggerFactory.getLogger(Akasuki.class).info("Akasuki Shard Connected");

                    //Update status
                    g.updatePresence(Presence.online(Activity.watching("Release J-4"))).block();
                });

        g.onDisconnect().block();
    }
}
