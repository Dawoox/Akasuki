package fr.dawoox.akasuki.core.command;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fr.dawoox.akasuki.utils.ConfigReader;

public class MessageProcessor {

    public static void processEvent(MessageCreateEvent event){
        //If the message is from a bot, ignore it
        if (event.getMessage().getAuthor().get().isBot()) {
            return;
        }

        if (!event.getGuildId().isPresent()){
            processPrivateMessage(event);
        } else {
            processGuildMessage(event.getGuildId().get(), event);
        }
    }

    private static void processPrivateMessage(MessageCreateEvent event){
        if (event.getMessage().getContent().startsWith(String.format("%shelp", ConfigReader.getEntry("default_prefix")))){
            CommandManager.getInstance().getCommand("help");
            return;
        }
    }

    private static void processGuildMessage(Snowflake guildId, MessageCreateEvent event){
        executeCommand(guildId, new Context(event, ConfigReader.getEntry("default_prefix")));
    }

    private static void executeCommand(Snowflake guildId, Context context) {
        final BaseCmd command = CommandManager.getInstance().getCommand(context.getCommandName());
        // The command does not exist
        if (command == null) {
            return;
        }

        // The command is not enabled.
        if (!command.isEnabled()) {
            context.getMessage().getChannel().block().createMessage("Cette commande est désactiver, désoler pour ça").block();
        }

        // This command is not allowed to this role
        if (!dbGuild.getSettings().isCommandAllowedToRole(command, context.getMember().getRoleIds())) {
            return Mono.empty();
        }

        COMMAND_USAGE_COUNTER.labels(command.getName()).inc();

        return context.getPermissions()
                .collectList()
                // The author has the permission to execute this command
                .filter(userPerms -> userPerms.contains(command.getPermission()))
                .switchIfEmpty(context.getChannel()
                        .flatMap(channel -> DiscordUtils.sendMessage(
                                String.format(Emoji.ACCESS_DENIED + " (**%s**) You do not have the permission to " +
                                        "execute this command.", context.getUsername()), channel)
                                .then(Mono.empty())))
                // The command is allowed in the guild and the user is not rate limited
                .filter(ignored -> dbGuild.getSettings().isCommandAllowed(command)
                        && !MessageProcessor.isRateLimited(context, command))
                .flatMap(ignored -> command.execute(context))
                .onErrorResume(err -> ExceptionHandler.handleCommandError(err, command, context));
    }

}
