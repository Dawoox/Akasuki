package fr.dawoox.akasuki.commands.misc;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.GuildEmoji;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.LogsWriter;
import fr.dawoox.akasuki.utils.MemberUtils;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

import static fr.dawoox.akasuki.utils.TimeUtils.format;
import static fr.dawoox.akasuki.utils.TimeUtils.diffInDays;

public class UserInfo {

    private static String author;
    private static String memberOld ;
    private static String accountOld;
    private static Member member;
    private static String statut;

    public static void reg(Map<String, Command> commands){
        commands.put("userinfo", event -> {
            final MessageChannel channel = event.getMessage().getChannel().block();
            GuildEmoji onlineEmoji = Objects.requireNonNull(event.getGuild().block()).getGuildEmojiById(Snowflake.of("749009520507879556")).block();
            GuildEmoji offlineEmoji = Objects.requireNonNull(event.getGuild().block()).getGuildEmojiById(Snowflake.of("749009521006870598")).block();
            GuildEmoji idleEmoji = Objects.requireNonNull(event.getGuild().block()).getGuildEmojiById(Snowflake.of("749009520713531503")).block();
            GuildEmoji distrubEmoji = Objects.requireNonNull(event.getGuild().block()).getGuildEmojiById(Snowflake.of("749009520180723825")).block();

            if (!event.getMessage().getUserMentionIds().isEmpty()){
                UserInfo.member = Objects.requireNonNull(event.getMessage().getUserMentions().blockFirst()).asMember(event.getGuildId().get()).block();
                UserInfo.author = Objects.requireNonNull(member).getUsername() + "#" + member.getDiscriminator();
                UserInfo.memberOld = format(member.getJoinTime())
                        + "\n(soit il y a " + diffInDays(member.getJoinTime(), Instant.now()) + " jours)";
                UserInfo.accountOld = format(MemberUtils.getAccountCreationDate(member))
                        + "\n(soit il y a " + diffInDays(MemberUtils.getAccountCreationDate(member), Instant.now()) + " jours)";
                UserInfo.statut = Objects.requireNonNull(member.getPresence().block()).getStatus().toString();

            } else {
                UserInfo.member = event.getMember().orElse(null);
                UserInfo.author = Objects.requireNonNull(member).getUsername() + "#" + member.getDiscriminator();
                UserInfo.memberOld = format(member.getJoinTime())
                        + "\n(soit il y a " + diffInDays(member.getJoinTime(), Instant.now()) + " jours)";
                UserInfo.accountOld = format(MemberUtils.getAccountCreationDate(member))
                        + "\n(soit il y a " + diffInDays(MemberUtils.getAccountCreationDate(member), Instant.now()) + " jours)";
                UserInfo.statut = Objects.requireNonNull(member.getPresence().block()).getStatus().toString();

            }

            switch (statut){
                case "DO_NOT_DISTURB":
                    UserInfo.statut = Objects.requireNonNull(distrubEmoji).asFormat() + "Ne pas déranger";
                    break;
                case "IDLE":
                    UserInfo.statut = Objects.requireNonNull(idleEmoji).asFormat() + "Inactif";
                    break;
                case "ONLINE":
                    UserInfo.statut = Objects.requireNonNull(onlineEmoji).asFormat() + "En ligne";
                    break;
                case "OFFLINE":
                    UserInfo.statut = Objects.requireNonNull(offlineEmoji).asFormat() + "Hors ligne";
                    break;
            }

            Objects.requireNonNull(channel).createEmbed(embed ->
                    embed.setColor(Color.of(54,57,63))
                            .setAuthor(author, null, member.getAvatarUrl())
                            .addField("Membre depuis le", memberOld, true)
                            .addField("Création du compte le", "           " + accountOld, true)
                            .addField("Statut", statut, true)
                            .addField("ID", member.getId().toString().substring(10, 28), false)
                            .setFooter("Yua", null)
                            .setTimestamp(Instant.now())
            ).block();
            LogsWriter.logAction("UserInfo", member, UserInfo.class);
        });
    }
 //
}
