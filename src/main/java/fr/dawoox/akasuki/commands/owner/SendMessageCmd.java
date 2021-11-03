package fr.dawoox.akasuki.commands.owner;

import com.sun.tools.javac.util.List;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.entity.User;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.core.SlashBaseCmd;

/**
 * Display a list of commands or the usage of a specified command
 * @author Dawoox
 * @version 1.0.0
 */
public class SendMessageCmd implements SlashBaseCmd {

    @Override
    public String getName() {
        return "sendMessage";
    }

    @Override
    public ApplicationCommandRequest getRequest() {
        return ApplicationCommandRequest.builder()
                .name("sendmessage")
                .description("Send a message in the DM of a user")
                .addAllOptions(List.of(
                        ApplicationCommandOptionData.builder()
                            .name("sentence")
                            .description("The sentence the bot is going to say")
                            .type(ApplicationCommandOption.Type.STRING.getValue())
                            .required(true)
                            .build(),
                        ApplicationCommandOptionData.builder()
                            .name("user")
                            .description("The user")
                            .type(ApplicationCommandOption.Type.USER.getValue())
                            .required(true)
                            .build()
                        ))
                .build();
    }

    @Override
    public void handle(ChatInputInteractionEvent event){
        String sentence = event.getOption("sentence").get().getValue().get().asString();
        User user = event.getOption("user").get().getValue().get().asUser().block();

        user.getPrivateChannel().block().createMessage(sentence).block();
        event.reply()
                .withEphemeral(true)
                .withContent(String.format("Successfully send msg to **%s**", user.getMention()))
                .block();
    }
}
