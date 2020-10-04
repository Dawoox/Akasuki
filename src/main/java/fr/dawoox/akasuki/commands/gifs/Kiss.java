package fr.dawoox.akasuki.commands.gifs;

import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.MessageChannel;
import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.LogsManager;

import java.util.Map;
import java.util.Objects;

public class Kiss {

    private static Member target;
    private static String reply = "default error";

    public static void reg(Map<String, Command> commands){
        commands.put("kiss", event -> {
            MessageChannel channel = event.getMessage().getChannel().block();
            Member sender = event.getMessage().getAuthorAsMember().block();

            assert sender != null;
            assert channel != null;

            if (!event.getMessage().getUserMentionIds().isEmpty()){
                Kiss.target = Objects.requireNonNull(event.getMessage().getUserMentions().blockFirst()).asMember(event.getGuildId().get()).block();
                reply = sender.getUsername() + " embrasse " + target.getUsername();
            } else {
                reply = sender.getUsername() + " embrasse quelqu'un";
            }

            GifTemplate.sendEmbed(channel, reply, "kiss");
            LogsManager.logAction("Kiss : \" + randomLink", sender, Kiss.class);
        });
    }

}
