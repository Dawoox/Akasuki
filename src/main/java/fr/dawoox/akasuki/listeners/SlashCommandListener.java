package fr.dawoox.akasuki.listeners;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.rest.service.ApplicationService;
import fr.dawoox.akasuki.Akasuki;
import fr.dawoox.akasuki.modules.SayCmd;
import fr.dawoox.akasuki.core.SlashContext;
import fr.dawoox.akasuki.core.SlashBaseCmd;
import fr.dawoox.akasuki.data.Config;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandListener {
    private final static List<SlashBaseCmd> globalCommands = new ArrayList<>();
    private final static List<SlashBaseCmd> guildsCommands = new ArrayList<>();

    static {
        guildsCommands.add(new SayCmd());
    }

    public static void handle(ChatInputInteractionEvent event) {
        guildsCommands.forEach(command -> {
            if (command.getName().equalsIgnoreCase(event.getCommandName())) {
                command.handle(new SlashContext(event));
                return;
            }
        });
        globalCommands.forEach(command -> {
            if (command.getName().equalsIgnoreCase(event.getCommandName())) {
                command.handle(new SlashContext(event));
                return;
            }
        });
    }

    public static void registerCommands(ApplicationService applicationService) {
        try {
            cleanCommands(applicationService);
            guildsCommands.forEach(command -> {
                applicationService.createGuildApplicationCommand(Akasuki.getApplicationId(), Config.GUILD_ID.asLong(), command.getRequest()).block();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cleanCommands(ApplicationService applicationService) {
        applicationService.getGuildApplicationCommands(Akasuki.getApplicationId(), Config.GUILD_ID.asLong())
                .collectList().block().forEach(application -> applicationService.deleteGuildApplicationCommand(Akasuki.getApplicationId(),
                        Config.GUILD_ID.asLong(), Long.parseLong(application.id().toString())).block());
    }
}
