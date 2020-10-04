package fr.dawoox.akasuki.commands.gifs;

import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.utils.database.DBManager;

import java.time.Instant;

public class GifTemplate {

    public static void sendEmbed(MessageChannel channel, String reply, String db){

        channel.createEmbed(embed -> embed.setColor(Color.DEEP_LILAC)
                .setAuthor(reply, null, null)
                .setImage(DBManager.getRandomLink(db))
                .setFooter("Yua", null)
                .setTimestamp(Instant.now())).block();
    }

}
