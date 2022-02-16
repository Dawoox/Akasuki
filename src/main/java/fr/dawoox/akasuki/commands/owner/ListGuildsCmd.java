package fr.dawoox.akasuki.commands.owner;

import discord4j.core.object.entity.Guild;
import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.slashcommands.SlashBaseCmd;
import fr.dawoox.akasuki.core.command.slashcommands.SlashContext;

import java.util.List;

public class ListGuildsCmd implements SlashBaseCmd {
    private final int maxShowServers = 10;

    @Override
    public String getName() {
        return "listguilds";
    }

    @Override
    public ApplicationCommandRequest getRequest() {
        return ApplicationCommandRequest.builder()
                .name("listguilds")
                .description("List all servers the bot is in (max "+maxShowServers+" servers render)")
                .build();
    }

    @Override
    public void handle(SlashContext context) {
        if (!context.getPermissions().equals(CommandPermission.OWNER)) {
            context.getEvent().reply().withContent("This command require developer permissions").block();
            return;
        }

        List<Guild> guilds = context.getClient().getGuilds().collectList().block();
        StringBuilder content = new StringBuilder();

        if (guilds.size() < maxShowServers) {
            for (Guild value : guilds) {
                content.append(value.getName()+" ("+value.getId().asString()+")\n");
            }

        } else {
            for (int i = 0; i < maxShowServers; i++) {
                content.append(guilds.get(i).getName()+" ("+guilds.get(i).getId().asString()+")\n");
            }
            content.append("\n"+(guilds.size()-maxShowServers)+" more servers.");
        }

        context.getEvent().reply()
                .withEphemeral(true)
                .withContent(content.toString())
                .block();
    }

}
