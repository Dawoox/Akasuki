package fr.dawoox.akasuki.core.command;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fr.dawoox.akasuki.data.Config;
import io.prometheus.client.Counter;
import io.sentry.Sentry;

public class MessageProcessor {


    private static final Counter COMMAND_USAGE_COUNTER = Counter.build()
            .namespace("akasuki").name("command_usage").help("Command Usage").labelNames("name").register();

    private static final Counter MESSAGE_TRIGGERED = Counter.build()
            .namespace("akasuki").name("message_triggered").help("Message triggered").register();

    private static final Counter MESSAGES_IN_GUILD = Counter.build()
            .namespace("akasuki").name("message_in_guild").help("Message Send In Guild").register();

    private static final Counter MESSAGE_IN_DM = Counter.build()
            .namespace("akasuki").name("message_in_dm").help("Message Send In DM").register();

    public static void processEvent(MessageCreateEvent event){
        //If the message is from a bot, ignore it
        if (event.getMessage().getAuthor().get().isBot()) {
            return;
        }

        if (event.getMessage().getContent().toLowerCase().charAt(0) != Config.DEFAULT_PREFIX.charAt(0)) {
            return;
        }

        MESSAGE_TRIGGERED.inc();
        if (!event.getGuildId().isPresent()){
            MESSAGE_IN_DM.inc();
            processPrivateMessage(event);
        } else {
            MESSAGES_IN_GUILD.inc();
            processGuildMessage(event.getGuildId().get(), event);
        }
    }

    private static void processPrivateMessage(MessageCreateEvent event){
        return;
        /*
        if (event.getMessage().getContent().startsWith(String.format("%shelp", ConfigReader.getEntry("default_prefix")))){
            CommandManager.getInstance().getCommand("help");
            return;
        }*/
    }

    private static void processGuildMessage(Snowflake guildId, MessageCreateEvent event){
        Context context = new Context(event, Config.DEFAULT_PREFIX);
        executeCommand(guildId, context);
    }

    private static void executeCommand(Snowflake guildId, Context context) {
        final BaseCmd command = CommandManager.getInstance().getCommand(context.getCommandName());
        // The command does not exist
        if (command == null) {
            return;
        }
        COMMAND_USAGE_COUNTER.labels(command.getName()).inc();

        // The command is not enabled.
        if (!command.isEnabled()) {
            context.getMessage().getChannel().block().createMessage("Cette commande est désactivée").block();
            return;
        }

        if (context.getPermissions().getPower() < command.getPermission().getPower()){
            context.getChannel().createMessage("Vous n'avez pas les permissions nécessaires /!\\").block();
            return;
        }

        try {
            command.execute(context);
        } catch (Exception e) {
            Sentry.captureException(e);
        }
    }

}
