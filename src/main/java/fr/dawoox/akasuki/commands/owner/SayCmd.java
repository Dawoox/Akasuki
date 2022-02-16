package fr.dawoox.akasuki.commands.owner;

import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.slashcommands.SlashContext;
import fr.dawoox.akasuki.core.command.slashcommands.SlashBaseCmd;

public class SayCmd implements SlashBaseCmd {

    @Override
    public String getName() {
        return "say";
    }

    @Override
    public ApplicationCommandRequest getRequest() {
        return ApplicationCommandRequest.builder()
                .name("say")
                .description("Let the bot say something")
                .addOption(ApplicationCommandOptionData.builder()
                    .name("sentence")
                    .description("The sentence the bot is going to say")
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

        String sentence = context.getOption("sentence").getValue().get().asString();
        context.getChannel().createMessage(sentence).block();

        context.getEvent().reply()
                .withEphemeral(true)
                .withContent("Message send")
                .block();
    }
}
