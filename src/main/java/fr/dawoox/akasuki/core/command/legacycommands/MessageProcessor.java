package fr.dawoox.akasuki.core.command.legacycommands;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fr.dawoox.akasuki.data.Config;
import fr.dawoox.akasuki.data.sqlite.UConfig;
import fr.dawoox.akasuki.features.ScamOMeter;

public class MessageProcessor {

    public static void processEvent(MessageCreateEvent event){
        //If the message contain a embed, ignore it (if not a NoSuchElementException will be throw)
        if (event.getMessage().getEmbeds().size() > 0) {
            return;
        }

        //If the message is from a bot, ignore it
        if (event.getMessage().getAuthor().get().isBot()) {
            return;
        }

        if (event.getMessage().getContent().toLowerCase().charAt(0) == Config.DEFAULT_PREFIX.charAt(0)) {
            if (event.getGuildId().isPresent()) {
                processGuildMessage(event.getGuildId().get(), event);
            }
        }

        if (ScamOMeter.analyse(event) && UConfig.isAutoModEnabled(event.getGuildId().get())) {
            ScamOMeter.sendSecurityAuditLog(event);
            event.getMessage().delete("AUTO-MOD::SCAM LINK").block();
        }
    }

    private static void processGuildMessage(Snowflake guildId, MessageCreateEvent event){
        Context context = new Context(event, Config.DEFAULT_PREFIX);
        executeCommand(guildId, context);
    }

    private static void executeCommand(Snowflake guildId, Context context) {
        final LegacyBaseCmd command = CommandManager.getInstance().getCommand(context.getCommandName());

        try {
            command.execute(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
