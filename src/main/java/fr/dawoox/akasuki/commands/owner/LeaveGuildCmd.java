package fr.dawoox.akasuki.commands.owner;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.entity.Guild;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.Akasuki;
import fr.dawoox.akasuki.core.SlashBaseCmd;

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
    public void handle(ChatInputInteractionEvent event) {
        if (!event.getInteraction().getUser().getId().equals(Akasuki.getOwnerId())) {
            event.reply().withEphemeral(true).withContent("This command require developer permissions").block();
            return;
        }

        Guild guild = event.getClient().getGuildById(Snowflake.of(event.getOption("serverid").get().getValue().get().asString())).block();
        String guildName = guild.getName();
        guild.leave().block();

        event.reply()
                .withEphemeral(true)
                .withContent(String.format("Successfully leave guild **%s**", guildName))
                .block();
    }
}
