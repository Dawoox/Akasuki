package fr.dawoox.akasuki.commands.gifs;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.exception.GiphyException;
import com.sun.tools.javac.util.List;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import fr.dawoox.akasuki.data.Config;
import io.sentry.Sentry;

import java.time.Instant;

/**
 * Show a random kiss gif
 * @author Dawoox
 * @version 1.1.0
 */
public class Kiss extends BaseCmd {

    private static String reply = "uh, is that a error?";

    public Kiss() {
        super(CommandCategory.FUN, CommandPermission.USER, List.of("kiss"));
    }

    @Override
    public void execute(Context context) {
        Giphy giphy = new Giphy(Config.GIPHY_API_KEY);

        if (!context.getMessage().getUserMentionIds().isEmpty()) {
            reply = context.getAuthor().getUsername()+" kiss  "+context.getMessage().getUserMentions().blockFirst().asMember(context.getGuildId()).block();
        } else {
            reply = context.getAuthor().getUsername()+" kiss";
        }

        context.getChannel().createEmbed( embedCreateSpec -> {
            try {
                embedCreateSpec.setColor(Color.DEEP_LILAC)
                        .setAuthor(reply, null, null)
                        .setImage(giphy.searchRandom("kiss").getData().getImageOriginalUrl())
                        .setTimestamp(Instant.now())
                        .setFooter("Akasuki", null);
            } catch (GiphyException e) {
                Sentry.captureException(e);
                e.printStackTrace();
            }
        }).block();
    }

}
