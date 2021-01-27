package fr.dawoox.akasuki;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.rest.response.ResponseFunction;
import fr.dawoox.akasuki.core.command.MessageProcessor;
import fr.dawoox.akasuki.core.thread.ActivityManager;
import fr.dawoox.akasuki.data.Config;
import io.prometheus.client.exporter.HTTPServer;
import io.sentry.Sentry;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.io.IOException;
import java.util.Locale;

/**
 * Main class
 * @author Dawoox
 * @version 2.3.0
 */
public class Akasuki {

    public static final Logger DEFAULT_LOGGER = Loggers.getLogger("akasuki");
    private static int guildCounts;

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
        DEFAULT_LOGGER.info(Figlet.render());

        try {
            HTTPServer server = new HTTPServer(8080);
        } catch (IOException e) { e.printStackTrace(); }

        if (true){
            DEFAULT_LOGGER.info("Initializing Sentry");
            Sentry.init(sentryOptions -> sentryOptions.setDsn("https://8f8f812b07284a7b99b8527cb2b94839@o473268.ingest.sentry.io/5508041"));
        }

        DEFAULT_LOGGER.info("Initializing");
        final DiscordClient client = DiscordClient.builder(Config.TOKEN).onClientResponse(ResponseFunction.emptyIfNotFound()).build();
        Akasuki.owner_id = Snowflake.of(client.getApplicationInfo().block().owner().id());

        DEFAULT_LOGGER.info("Connecting to Discord");
        final GatewayDiscordClient gateway = client.login().block();
        System.setProperty("http.agent", Config.USER_AGENT);
        assert gateway != null;

        gateway.getEventDispatcher().on(MessageCreateEvent.class).subscribe(MessageProcessor::processEvent);

        DEFAULT_LOGGER.info("Updating bot activity");
        gateway.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(readyEvent -> {
                    guildCounts = gateway.getGuilds().collectList().block().size();
                    new ActivityManager().run(gateway);
                });

        gateway.onDisconnect().block();
    }

    public static Snowflake getOwnerId() {
        return owner_id;
    }
    public static long getGuildCount() {
        return guildCounts;
    }
}
