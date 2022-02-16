package fr.dawoox.akasuki.commands.utils;

import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.core.command.slashcommands.SlashBaseCmd;
import fr.dawoox.akasuki.core.command.slashcommands.SlashContext;

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
    public void handle(SlashContext context) {
        context.getEvent().reply()
                .withEphemeral(false)
                .withContent("Pong!")
                .block();
    }

}
