package fr.dawoox.yua.commands.gifs;

import com.mongodb.client.MongoCollection;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import fr.dawoox.yua.modules.DBChecker;
import fr.dawoox.yua.utils.Command;
import fr.dawoox.yua.utils.LogsManager;
import fr.dawoox.yua.utils.database.DBManager;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

public class Hug {

    private static Member target;
    private static String reply = "default error";

    public static void reg(Map<String, Command> commands){
        commands.put("hug", event -> {
            MongoCollection collection = DBManager.getDatabase().getCollection("hug");

            int R = (int) Math.floor(Math.random() * collection.countDocuments());
            String randomLink = Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString()
                    .substring(45, Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString().length() - 2);

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
            channel.createEmbed(embed -> embed.setColor(Color.DEEP_LILAC)
                    .setAuthor(reply, null, null)
                    .setImage(randomLink)
                    .setFooter("Yua", null)
                    .setTimestamp(Instant.now())).block();
            LogsManager.logAction("Hug[\" + R + \"] : \" + randomLink", sender, Hug.class);
        });
    }

}