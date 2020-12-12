package fr.dawoox.akasuki;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import discord4j.rest.response.ResponseFunction;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.MessageProcessor;
import fr.dawoox.akasuki.utils.ConfigReader;
import io.prometheus.client.exporter.HTTPServer;
import io.sentry.Sentry;
import org.slf4j.LoggerFactory;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Main class
 * @author Dawoox
 * @version 2.3.0
 */
public class Akasuki {

    public static final Logger DEFAULT_LOGGER = Loggers.getLogger("akasuki");

    private static final String prefix = ConfigReader.getEntry("default_prefix");
    private static Snowflake owner_id;

    /**
     * Main class, call on startup.
     * @param args
     * Args are the supplied command-line argument.
     * @since 1.0.0
     */
    public static void main(String[] args) {
        //Set default locale to FR
        Locale.setDefault(Locale.FRANCE);

        try {
            HTTPServer server = new HTTPServer(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (true){
            DEFAULT_LOGGER.info("Initializing Sentry");
            Sentry.init(sentryOptions -> sentryOptions.setDsn("https://8f8f812b07284a7b99b8527cb2b94839@o473268.ingest.sentry.io/5508041"));
        }

        DEFAULT_LOGGER.info("Initializing ");

        final DiscordClient client = DiscordClient.builder(ConfigReader.getEntry("token")).onClientResponse(ResponseFunction.emptyIfNotFound()).build();

        Akasuki.owner_id = Snowflake.of(client.getApplicationInfo().block().owner().id());

        DEFAULT_LOGGER.info("Connecting to Discord");

        final String TOKEN = ConfigReader.getEntry("token");
        final GatewayDiscordClient gateway = client.login().block();

        System.setProperty("http.agent", "Mozilla/5.0 (compatible; Discordbot/2.0; +https://discordapp.com)");
        assert gateway != null;


        //Get call when a new message is send in any guild where the bot is
        gateway.getEventDispatcher().on(MessageCreateEvent.class).subscribe(MessageProcessor::processEvent);


        //Get call when the bot start
        gateway.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(readyEvent -> {
                    LoggerFactory.getLogger(Akasuki.class).info("Akasuki Shard Initialing...");

                    //Output all guild's name where Akasuki is
                    for (int i = 0; i< Objects.requireNonNull(Objects.requireNonNull(gateway.getGuilds().collectList().block())).size(); i++){
                        System.out.println(Objects.requireNonNull(gateway.getGuilds().collectList().block()).get(i).getName());
                    }

                    //Update status if present.
                    if (args.length >= 2){
                        LoggerFactory.getLogger(Akasuki.class).info("Changing Activity...");
                        String activity = "";
                        for (int i=1;i<args.length;i++){
                            activity = activity += args[i] + " ";
                        }
                        gateway.updatePresence(Presence.online(Activity.watching(activity))).block();
                        LoggerFactory.getLogger(Akasuki.class).info("Activity Changed");
                    }
                });

        gateway.onDisconnect().block();
    }

    public static final Snowflake getOwnerId() {
        return owner_id;
    }

    public static final long getGuildCount() {
        return 1;
    }
}
