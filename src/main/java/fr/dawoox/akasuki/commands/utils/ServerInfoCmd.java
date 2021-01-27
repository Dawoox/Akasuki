package fr.dawoox.akasuki.commands.utils;

import com.sun.tools.javac.util.List;
import discord4j.core.object.entity.Member;
import discord4j.rest.util.Color;
import discord4j.rest.util.Image;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;

public class ServerInfoCmd extends BaseCmd {

    public ServerInfoCmd() {
        super(CommandCategory.UTILS, CommandPermission.USER, List.of("serverinfo", "si"));
    }

    @Override
    public void execute(Context context) {
        context.getChannel().createEmbed(embedCreateSpec -> embedCreateSpec
                        .setColor(Color.DEEP_LILAC)
                        .setThumbnail(context.getGuild().getIconUrl(Image.Format.JPEG).get())
                        .setAuthor("|> Server", null, null)
                        .addField("Name", context.getGuild().getName(), true)
                        .addField("ID:", context.getGuildId().asString(), true)
                        .addField("Region:", context.getGuild().getRegion().block().getName(), true)
                        //.addField("Verification Level:", context.getGuild().getVerificationLevel().name(), true)
                        .addField("Boosts:", String.valueOf(context.getGuild().getPremiumSubscriptionCount().orElse(0)), true)
                        .addField("Level:", "__"+context.getGuild().getPremiumTier().getValue() + "__/3", true)
                        .addField("Owner:", context.getGuild().getOwner().block().getTag(), true)
                        .addField("\u200F\u200F\u200E \u200E", "\u200F\u200F\u200E \u200E", false)
                        .addField("|> Members", "\u200F\u200F\u200E \u200E", false)
                        .addField("Total:", "__" + context.getGuild().getMemberCount() + "__/" + context.getGuild().getMaxMembers().getAsInt(), true)
                        .addField("Humains:", String.valueOf(context.getGuild().getMemberCount() - context.getGuild().getMembers()
                                .filter(Member::isBot).collectList().block().size()), true)
                        .addField("Bots:", String.valueOf(context.getGuild().getMembers().filter(Member::isBot).collectList().block().size()), true)
                        .addField("\u200F\u200F\u200E \u200E", "\u200F\u200F\u200E \u200E", false)
                        .addField("|> Roles", "\u200F\u200F\u200E \u200E", false)
                        .addField("Size:", String.valueOf(context.getGuild().getRoles().collectList().block().size()), true)
        ).block();
    }
}
