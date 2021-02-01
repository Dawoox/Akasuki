package fr.dawoox.akasuki.commands.gifs;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.exception.GiphyException;
import com.sun.tools.javac.util.List;
import discord4j.core.object.entity.Member;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import fr.dawoox.akasuki.data.Config;
import fr.dawoox.akasuki.utils.template.EmbedTemplate;
import io.sentry.Sentry;

import java.time.Instant;

/**
 * Show a random hug gif
 * @author Dawoox
 * @version 1.1.0
 */
public class Hug extends BaseCmd {

    private static String reply = "uh, is that a error?";

    public Hug() {
        super(CommandCategory.FUN, CommandPermission.USER, List.of("hug"));
    }

    @Override
    public void execute(Context context) {
        Giphy giphy = new Giphy(Config.GIPHY_API_KEY);

        if (!context.getMessage().getUserMentionIds().isEmpty()) {
            reply = context.getAuthor().getUsername()+" hug "+context.getMessage().getUserMentions().blockFirst().asMember(context.getGuildId()).block();
        } else {
            reply = context.getAuthor().getUsername()+" hug";
        }

        context.getChannel().createEmbed( embedCreateSpec -> {
            try {
                embedCreateSpec.setColor(Color.DEEP_LILAC)
                        .setAuthor(reply, null, null)
                        .setImage(giphy.searchRandom("hug").getData().getImageOriginalUrl())
                        .setTimestamp(Instant.now())
                        .setFooter("Akasuki", null);
            } catch (GiphyException e) {
                Sentry.captureException(e);
                e.printStackTrace();
            }
        }).block();
    }

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
