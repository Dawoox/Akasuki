package fr.dawoox.akasuki.commands.owner;

import com.sun.tools.javac.util.List;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.entity.User;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.slashcommands.SlashBaseCmd;
import fr.dawoox.akasuki.core.command.slashcommands.SlashContext;

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
    public void handle(SlashContext context){
        if (!context.getPermissions().equals(CommandPermission.OWNER)) {
            context.getEvent().reply().withEphemeral(true).withContent("This command require developer permissions").block();
            return;
        }

        String sentence = context.getOption("sentence").getValue().get().asString();
        User user = context.getOption("user").getValue().get().asUser().block();

        user.getPrivateChannel().block().createMessage(sentence).block();
        context.getEvent().reply()
                .withEphemeral(true)
                .withContent(String.format("Successfully send msg to **%s**", user.getMention()))
                .block();
    }
}
