package fr.dawoox.akasuki.listeners;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.rest.service.ApplicationService;
import fr.dawoox.akasuki.Akasuki;
import fr.dawoox.akasuki.commands.owner.SayCmd;
import fr.dawoox.akasuki.commands.owner.SendMessageCmd;
import fr.dawoox.akasuki.commands.utils.PingCmd;
import fr.dawoox.akasuki.core.SlashBaseCmd;
import fr.dawoox.akasuki.data.Config;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandListener {
    private final static List<SlashBaseCmd> commands = new ArrayList<>();

    static {
        commands.add(new PingCmd());
        commands.add(new SayCmd());
        commands.add(new SendMessageCmd());
    }

    public static void handle(ChatInputInteractionEvent event) {
        commands.forEach(command -> {
            if (command.getName().equalsIgnoreCase(event.getCommandName())){
                command.handle(event);
            }
        });
    }

    public static void registerCommands(ApplicationService applicationService, Boolean asGlobal) {
        try {
            if (asGlobal) {
                //TODO
            } else {
                cleanCommands(applicationService);
                commands.forEach(command -> {
                    applicationService.createGuildApplicationCommand(Akasuki.getApplicationId(), Config.GUILD_ID.asLong(), command.getRequest()).block();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cleanCommands(ApplicationService applicationService) {
        applicationService.getGuildApplicationCommands(Akasuki.getApplicationId(), Config.GUILD_ID.asLong())
                .doOnEach(application -> applicationService.deleteGuildApplicationCommand(Akasuki.getApplicationId(),
                        Config.GUILD_ID.asLong(), Long.valueOf(application.get().id())).block());
        applicationService.getGlobalApplicationCommands(Akasuki.getApplicationId())
                .doOnEach(application -> applicationService.deleteGlobalApplicationCommand(Akasuki.getApplicationId(),
                        Snowflake.asLong(application.get().id())).block());
    }
}
