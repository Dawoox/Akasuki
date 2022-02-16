package fr.dawoox.akasuki.core.command.slashcommands;

import discord4j.discordjson.json.ApplicationCommandRequest;

public interface SlashBaseCmd {
    String getName();
    ApplicationCommandRequest getRequest();

    void handle(SlashContext slashContext);
}
