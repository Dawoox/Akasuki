package fr.dawoox.akasuki;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.rest.response.ResponseFunction;
import fr.dawoox.akasuki.core.ActivityManager;
import fr.dawoox.akasuki.core.command.legacycommands.MessageProcessor;
import fr.dawoox.akasuki.data.Config;
import fr.dawoox.akasuki.data.sqlite.SQLiteJBC;
import fr.dawoox.akasuki.listeners.SlashCommandListener;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Main class
 * @author Dawoox
 * @version 2.3.0
 */
public class Akasuki {

    public static final Logger DEFAULT_LOGGER = Loggers.getLogger("akasuki");
    private static int guildCounts;

    private static Snowflake ownerId;
    private static long applicationId;
    private static final Instant startup = Instant.now();

    public static void main(String[] args) {
        //Set default locale to FR
        Locale.setDefault(Locale.FRANCE);
        DEFAULT_LOGGER.info(Figlet.render());

        DEFAULT_LOGGER.info("Initializing");
        final DiscordClient client = DiscordClient.builder(Config.TOKEN).onClientResponse(ResponseFunction.emptyIfNotFound()).build();
        Akasuki.ownerId = Snowflake.of(client.getApplicationInfo().block().owner().id());
        Akasuki.applicationId = client.getApplicationId().block();

        DEFAULT_LOGGER.info("Connecting to the database");
        SQLiteJBC.initialize();

        DEFAULT_LOGGER.info("Connecting to Discord");
        final GatewayDiscordClient gateway = client.login().block();
        System.setProperty("http.agent", Config.USER_AGENT);
        assert gateway != null;

        DEFAULT_LOGGER.info("Start thread bot activity");
        gateway.getEventDispatcher().on(ReadyEvent.class).subscribe(readyEvent -> {
                    guildCounts = gateway.getGuilds().collectList().block().size();
                    new ActivityManager().run(gateway);
                });

        DEFAULT_LOGGER.info("Registering commands with Discord");
        SlashCommandListener.registerCommands(client.getApplicationService());

        DEFAULT_LOGGER.info("Listening to events now");
        gateway.getEventDispatcher().on(ChatInputInteractionEvent.class).subscribe(SlashCommandListener::handle);
        gateway.getEventDispatcher().on(MessageCreateEvent.class).subscribe(MessageProcessor::processEvent);

        DEFAULT_LOGGER.info("All initialized");

        gateway.onDisconnect().block();
    }

    public static Snowflake getOwnerId() {
        return ownerId;
    }
    public static long getGuildCount() {
        return guildCounts;
    }
    public static long getUptime() {
        return ChronoUnit.SECONDS.between(startup, Instant.now());
    }
    public static long getApplicationId() {
        return applicationId;
    }
}
