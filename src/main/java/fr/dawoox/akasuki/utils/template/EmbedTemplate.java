package fr.dawoox.akasuki.utils.template;

import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.utils.database.DBUtils;

import java.time.Instant;

/**
 * Utils class to help sending Embed.
 * @author Dawoox
 * @version 1.3.0
 */
public class EmbedTemplate {

    /**
     * Send Embed to a Channel with a reply, and a random link from a database
     * @param channel
     * Channel to send the embed
     * @param reply
     * Message to add to the embed
     * @param db
     * Database to retrieve the random gif
     * @since 1.0.0
     */
    public static void sendEmbed(MessageChannel channel, String reply, String db){

        channel.createEmbed(embed -> embed.setColor(Color.DEEP_LILAC)
                .setAuthor(reply, null, null)
                .setImage(DBUtils.getRandomLink(db))
                .setFooter("Akasuki", null)
                .setTimestamp(Instant.now())).block();
    }

    /**
     * Send Embed to a Channel with only a reply
     * @param channel
     * Channel to send the embed
     * @param reply
     * Message to add to the embed
     * @since 1.2.0
     */
    public static void sendEmbed(MessageChannel channel, String reply){

        channel.createEmbed(embed -> embed.setColor(Color.DEEP_LILAC)
                .setAuthor(reply, null, null)
                .setFooter("Akasuki", null)
                .setTimestamp(Instant.now())).block();
    }

    /**
     * Send Embed to a Channel with a reply, a color, and a image url
     * @param channel
     * Channel to send the embed
     * @param reply
     * Message to add to the embed
     * @param color
     * Color of the embed
     * @param image_url
     * Url to a image to display
     * @param description
     * Description to add to the embed
     * @since 1.3.0
     */
    public static void sendEmbed(MessageChannel channel, String reply, String description, Color color, String image_url){

        channel.createEmbed(embed -> embed.setColor(color)
                .setAuthor(reply, null, null)
                .setImage(image_url)
                .setDescription(description)
                .setFooter("Akasuki", null)
                .setTimestamp(Instant.now())
        ).block();
    }

}
