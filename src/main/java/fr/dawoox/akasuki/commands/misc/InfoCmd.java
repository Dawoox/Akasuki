package fr.dawoox.akasuki.commands.misc;

import com.sun.tools.javac.util.List;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.Akasuki;
import fr.dawoox.akasuki.core.command.*;
import fr.dawoox.akasuki.data.ConfigLoader;
import fr.dawoox.akasuki.data.Maven;
import fr.dawoox.akasuki.utils.TimeUtils;

import java.time.Instant;

public class InfoCmd extends BaseCmd {

    private static final String links = "[Support Server]("+ConfigLoader.SUPPORT_SERVER_URL+")\n" +
            "[Github]("+ConfigLoader.GITHUB_URL+")\n" +
            "[Invite]("+ConfigLoader.INVITE_URL+")\n";

    public InfoCmd() {
        super(CommandCategory.UTILS, CommandPermission.USER, List.of("info", "i"));
    }

    @Override
    public void execute(Context context) {
        context.getChannel().createEmbed(embedCreateSpec -> embedCreateSpec
                        .setColor(Color.DEEP_LILAC)
                        .setAuthor("Bot info", null, null)
                        .setThumbnail(context.getClient().getSelf().block().getAvatarUrl())
                        .addField("Tag", context.getClient().getSelf().block().getTag(), true)
                        .addField("Owner", context.getClient().getApplicationInfo().block().getOwner().block().getTag(), true)
                        .addField("Library", "Discord4J " + Maven.DISCORD4J_VERSION, true)
                        .addField("Commands", String.valueOf(CommandManager.getInstance().getCommandsCount()), true)
                        .addField("Prefix", ConfigLoader.DEFAULT_PREFIX, true)
                        .addField("Server Count", String.valueOf(Akasuki.getGuildCount()), true)
                        .addField("Ping", TimeUtils.diffInMillis(context.getMessage().getTimestamp(), Instant.now()) + "ms", true)
                        .addField("Links", links, true)
                        .setFooter("Akasuki", null)
                        .setTimestamp(Instant.now())

        ).block();
    }

}
