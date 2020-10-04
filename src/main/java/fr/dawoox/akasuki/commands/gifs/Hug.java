package fr.dawoox.akasuki.commands.gifs;

import com.mongodb.client.MongoCollection;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.LogsManager;
import fr.dawoox.akasuki.utils.database.DBManager;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

public class Hug {

    private static Member target;
    private static String reply = "default error";

    public static void reg(Map<String, Command> commands){
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

            channel.createEmbed(embed -> embed.setColor(Color.DEEP_LILAC)
                    .setAuthor(reply, null, null)
                    .setImage(getRandomLink())
                    .setFooter("Yua", null)
                    .setTimestamp(Instant.now())).block();
            LogsManager.logAction("Hug[\" + R + \"] : \" + randomLink", sender, Hug.class);
        });
    }

    public static String getRandomLink() {
        MongoCollection collection = DBManager.getDatabase().getCollection("hug");

        int R = (int) Math.floor(Math.random() * collection.countDocuments());
        String randomLink = Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString()
                .substring(45, Objects.requireNonNull(collection.find().limit(1).skip(R).first()).toString().length() - 2);

        return randomLink;
    }

}
