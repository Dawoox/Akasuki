package fr.dawoox.akasuki.commands.owner;

import discord4j.common.util.Snowflake;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.entity.Guild;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.slashcommands.SlashBaseCmd;
import fr.dawoox.akasuki.core.command.slashcommands.SlashContext;

public class LeaveGuildCmd implements SlashBaseCmd {

    @Override
    public String getName() {
        return "leaveguild";
    }

    @Override
    public ApplicationCommandRequest getRequest() {
        return ApplicationCommandRequest.builder()
                .name("leaveguild")
                .description("Let the bot leave a server")
                .addOption(ApplicationCommandOptionData.builder()
                    .name("serverid")
                    .description("The id of the server to leave")
                    .type(ApplicationCommandOption.Type.STRING.getValue())
                    .required(true)
                    .build())
                .build();
    }

    @Override
    public void handle(SlashContext context) {
        if (!context.getPermissions().equals(CommandPermission.OWNER)) {
            context.getEvent().reply().withContent("This command require developer permissions").block();
            return;
        }

        Guild guild = context.getClient().getGuildById(Snowflake.of(context.getOption("serverid").getValue().get().asString())).block();
        String guildName = guild.getName();
        guild.leave().block();

        context.getEvent().reply()
                .withEphemeral(true)
                .withContent(String.format("Successfully leave guild **%s**", guildName))
                .block();
    }
}
