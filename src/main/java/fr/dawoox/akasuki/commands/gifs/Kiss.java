package fr.dawoox.akasuki.commands.gifs;

import discord4j.core.object.entity.Member;

/**
 * Show a random kiss gif
 * @author Dawoox
 * @version 1.1.0
 */
public class Kiss {

    private static Member target;
    private static String reply = "default error";

    /*
    public static void reg(Map<String, BaseCmd> commands){
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

            EmbedTemplate.sendEmbed(channel, reply, "kiss");
            LogsWriter.logAction("Kiss : ", sender, Kiss.class);
        });
    }*/

}
