package fr.dawoox.akasuki.commands.utils;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.core.SlashBaseCmd;

public class PingCmd implements SlashBaseCmd {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public ApplicationCommandRequest getRequest() {
        return ApplicationCommandRequest.builder()
                .name("ping")
                .description("Send you a pong")
                .build();
    }

    @Override
    public void handle(ChatInputInteractionEvent event) {
        event.reply()
                .withEphemeral(false)
                .withContent("Pong!")
                .block();
    }

}
