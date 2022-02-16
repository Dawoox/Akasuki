package fr.dawoox.akasuki.commands.utils;

import com.sun.tools.javac.util.List;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.core.command.slashcommands.SlashBaseCmd;
import fr.dawoox.akasuki.core.command.slashcommands.SlashContext;
import fr.dawoox.akasuki.features.EmbedActivityLoader;
import fr.dawoox.akasuki.utils.EmbedActivities;

public class LaunchEmbedActivityCmd implements SlashBaseCmd {

    @Override
    public String getName() {
        return "launch-embed-activity";
    }

    @Override
    public ApplicationCommandRequest getRequest() {
        return ApplicationCommandRequest.builder()
                .name("launch-embed-activity")
                .description("Start an embed activity in your voice channel")
                .addAllOptions(List.of(
                        ApplicationCommandOptionData.builder()
                            .name("activity")
                            .description("The embed activity to start")
                            .type(ApplicationCommandOption.Type.STRING.getValue())
                            .required(true)
                            .build(),
                        ApplicationCommandOptionData.builder()
                            .name("channel")
                            .description("The voice channel the activity should be started in")
                            .type(ApplicationCommandOption.Type.CHANNEL.getValue())
                            .required(true)
                            .build()
                        ))
                .build();
    }

    @Override
    public void handle(SlashContext context) {
        Channel channel = context.getOption("channel").getValue().get().asChannel().block();
        EmbedActivities activity = null;

        try {
            activity = EmbedActivities.valueOf(context.getOption("activity").getValue().get().asString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (channel instanceof VoiceChannel && activity != null) {
            String inviteCode = EmbedActivityLoader.requestActivityInvite((VoiceChannel) channel, activity);

            context.getEvent().reply()
                    .withEphemeral(false)
                    .withContent(String.format("Click to join the activity -> https://discord.gg/%s", inviteCode))
                    .block();
        }

    }
}
