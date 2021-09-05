package fr.dawoox.akasuki.commands.owner;

import com.sun.tools.javac.util.List;
import discord4j.core.object.entity.Guild;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;

public class ListGuildCmd extends BaseCmd {
    private final int maxShowServers = 10;

    public ListGuildCmd() {
        super(CommandCategory.OWNER, CommandPermission.OWNER, List.of("list_guilds"));
    }

    @Override
    public void execute(Context context) {
        java.util.List<Guild> guild = context.getClient().getGuilds().collectList().block();
        if (guild.size() < maxShowServers) {
            for (Guild value : guild) {
                context.getChannel().createMessage(value.getName()).block();
            }
        } else {
            for (int i = 0; i < maxShowServers; i++) {
                context.getChannel().createMessage(guild.get(i).getName()).block();
            }
            context.getChannel().createMessage("+ "+String.valueOf(guild.size()-maxShowServers) +" more servers.").block();
        }

    }

}
