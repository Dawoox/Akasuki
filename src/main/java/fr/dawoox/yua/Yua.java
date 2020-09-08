package fr.dawoox.yua;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import fr.dawoox.yua.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class Yua {

    private static final Map<String, Command> commands = new HashMap<>();

    public static void main(String[] args) {
        final String token = args[0];
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient g = client.login().block();

        g.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if ("!ping".equalsIgnoreCase(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("Pong!").block();
            }
        });

        g.onDisconnect().block();
    }
}
