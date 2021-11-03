package fr.dawoox.akasuki.core;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;

public interface SlashBaseCmd {
    String getName();
    ApplicationCommandRequest getRequest();

    void handle(ChatInputInteractionEvent event);
}
