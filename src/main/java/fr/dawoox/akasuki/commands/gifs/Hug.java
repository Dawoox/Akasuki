package fr.dawoox.akasuki.commands.gifs;

import discord4j.core.object.entity.Member;

/**
 * Show a random hug gif
 * @author Dawoox
 * @version 1.1.0
 */
public class Hug {

    private static Member target;
    private static String reply = "default error";

    /*
    public static void reg(Map<String, BaseCmd> commands){
        commands.put("hug", event -> {
            MessageChannel channel = event.getMessage().getChannel().block();
            Member sender = event.getMessage().getAuthorAsMember().block();

            assert sender != null;
            assert channel != null;

            if (!event.getMessage().getUserMentionIds().isEmpty()){
                Hug.target = Objects.requireNonNull(event.getMessage().getUserMentions().blockFirst()).asMember(event.getGuildId().get()).block();
                reply = sender.getUsername() + " fait un calin à " + target.getUsername();
            } else {
                reply = sender.getUsername() + " fait un calin";
            }

            EmbedTemplate.sendEmbed(channel, reply, "hug");
            LogsWriter.logAction("Hug : ", sender, Hug.class);
        });
    }*/

}
