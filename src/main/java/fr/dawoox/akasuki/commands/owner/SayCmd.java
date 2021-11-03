package fr.dawoox.akasuki.commands.owner;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.core.SlashBaseCmd;

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
    public void handle(ChatInputInteractionEvent event) {
        String sentence = event.getOption("sentence").get().getValue().get().asString();
        event.getInteraction().getChannel().block().createMessage(sentence).block();

        event.reply()
                .withEphemeral(true)
                .withContent("Message send")
                .block();
    }
}
