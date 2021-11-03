package fr.dawoox.akasuki.commands.utils;

import com.sun.tools.javac.util.List;
import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.GuildEmoji;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import fr.dawoox.akasuki.utils.MemberUtils;

import java.time.Instant;
import java.util.Objects;

import static fr.dawoox.akasuki.utils.TimeUtils.format;
import static fr.dawoox.akasuki.utils.TimeUtils.diffInDays;

/**
 * Return informations about the mender mentioned or who has send the command
 * @author Dawoox
 * @version 1.0.0
 */
public class UserInfoCmd extends BaseCmd{

    private static String author;
    private static String memberOld ;
    private static String accountOld;
    private static Member member;
    private static String statut;

    public UserInfoCmd() {
        super(CommandCategory.UTILS, CommandPermission.USER, List.of("userinfo", "ui"));
    }

    @Override
    public void execute(Context context) {

        final MessageChannel channel = context.getChannel();
        GuildEmoji onlineEmoji = Objects.requireNonNull(context.getGuild()).getGuildEmojiById(Snowflake.of("749009520507879556")).block();
        GuildEmoji offlineEmoji = Objects.requireNonNull(context.getGuild()).getGuildEmojiById(Snowflake.of("749009521006870598")).block();
        GuildEmoji idleEmoji = Objects.requireNonNull(context.getGuild()).getGuildEmojiById(Snowflake.of("749009520713531503")).block();
        GuildEmoji distrubEmoji = Objects.requireNonNull(context.getGuild()).getGuildEmojiById(Snowflake.of("749009520180723825")).block();

        if (!context.getMessage().getUserMentionIds().isEmpty()){
            UserInfoCmd.member = Objects.requireNonNull(context.getMessage().getUserMentions().get(0)).asMember(context.getGuildId()).block();
            UserInfoCmd.author = Objects.requireNonNull(member).getUsername() + "#" + member.getDiscriminator();
            UserInfoCmd.memberOld = format(member.getJoinTime().get())
                    + "\n(soit il y a " + diffInDays(member.getJoinTime().get(), Instant.now()) + " jours)";
            UserInfoCmd.accountOld = format(MemberUtils.getAccountCreationDate(member))
                    + "\n(soit il y a " + diffInDays(MemberUtils.getAccountCreationDate(member), Instant.now()) + " jours)";
            UserInfoCmd.statut = Objects.requireNonNull(member.getPresence().block()).getStatus().toString();

        } else {
            UserInfoCmd.member = context.getMember();
            UserInfoCmd.author = Objects.requireNonNull(member).getUsername() + "#" + member.getDiscriminator();
            UserInfoCmd.memberOld = format(member.getJoinTime().get())
                    + "\n(soit il y a " + diffInDays(member.getJoinTime().get(), Instant.now()) + " jours)";
            UserInfoCmd.accountOld = format(MemberUtils.getAccountCreationDate(member))
                    + "\n(soit il y a " + diffInDays(MemberUtils.getAccountCreationDate(member), Instant.now()) + " jours)";
            UserInfoCmd.statut = Objects.requireNonNull(member.getPresence().block()).getStatus().toString();

        }

        switch (statut){
            case "DO_NOT_DISTURB":
                UserInfoCmd.statut = Objects.requireNonNull(distrubEmoji).asFormat() + "Ne pas déranger";
                break;
            case "IDLE":
                UserInfoCmd.statut = Objects.requireNonNull(idleEmoji).asFormat() + "Inactif";
                break;
            case "ONLINE":
                UserInfoCmd.statut = Objects.requireNonNull(onlineEmoji).asFormat() + "En ligne";
                break;
            case "OFFLINE":
                UserInfoCmd.statut = Objects.requireNonNull(offlineEmoji).asFormat() + "Hors ligne";
                break;
        }

        channel.createEmbed(embed ->
                embed.setColor(Color.of(54,57,63))
                        .setAuthor(author, null, member.getAvatarUrl())
                        .addField("Membre depuis le", memberOld, true)
                        .addField("Création du compte le", "           " + accountOld, true)
                        .addField("Statut", statut, true)
                        .addField("ID", member.getId().toString().substring(10, 28), false)
                        .setFooter("Akasuki", null)
                        .setTimestamp(Instant.now())
        ).block();
    }
}
