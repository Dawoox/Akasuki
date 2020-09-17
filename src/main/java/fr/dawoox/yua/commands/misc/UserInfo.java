package fr.dawoox.yua.commands.misc;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.GuildEmoji;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import fr.dawoox.yua.utils.Command;
import fr.dawoox.yua.utils.LogsManager;
import fr.dawoox.yua.utils.MemberManager;

import java.time.Instant;
import java.util.Map;

import static fr.dawoox.yua.utils.TimeManager.format;
import static fr.dawoox.yua.utils.TimeManager.diffInDays;

public class UserInfo {

    private static String author;
    private static String memberOld ;
    private static String accountOld;
    private static Member member;
    private static String statut;

    public static void reg(Map<String, Command> commands){
        commands.put("userinfo", event -> {
            final MessageChannel channel = event.getMessage().getChannel().block();
            GuildEmoji onlineEmoji = event.getGuild().block().getGuildEmojiById(Snowflake.of("749009520507879556")).block();
            GuildEmoji offlineEmoji = event.getGuild().block().getGuildEmojiById(Snowflake.of("749009521006870598")).block();
            GuildEmoji idleEmoji = event.getGuild().block().getGuildEmojiById(Snowflake.of("749009520713531503")).block();
            GuildEmoji distrubEmoji = event.getGuild().block().getGuildEmojiById(Snowflake.of("749009520180723825")).block();

            if (!event.getMessage().getUserMentionIds().isEmpty()){
                UserInfo.member = event.getMessage().getUserMentions().blockFirst().asMember(event.getGuildId().get()).block();
                UserInfo.author = member.getUsername() + "#" + member.getDiscriminator();
                UserInfo.memberOld = format(member.getJoinTime())
                        + "\n(soit il y a " + diffInDays(member.getJoinTime(), Instant.now()) + " jours)";
                UserInfo.accountOld = format(MemberManager.getAccountCreationDate(member))
                        + "\n(soit il y a " + diffInDays(MemberManager.getAccountCreationDate(member), Instant.now()) + " jours)";
                UserInfo.statut = member.getPresence().block().getStatus().toString();

            } else {
                UserInfo.member = event.getMember().orElse(null);
                UserInfo.author = member.getUsername() + "#" + member.getDiscriminator();
                UserInfo.memberOld = format(member.getJoinTime())
                        + "\n(soit il y a " + diffInDays(member.getJoinTime(), Instant.now()) + " jours)";
                UserInfo.accountOld = format(MemberManager.getAccountCreationDate(member))
                        + "\n(soit il y a " + diffInDays(MemberManager.getAccountCreationDate(member), Instant.now()) + " jours)";
                UserInfo.statut = member.getPresence().block().getStatus().toString();

            }

            switch (statut){
                case "DO_NOT_DISTURB":
                    UserInfo.statut = distrubEmoji.asFormat() + "Ne pas déranger";
                    break;
                case "IDLE":
                    UserInfo.statut = idleEmoji.asFormat() + "Inactif";
                    break;
                case "ONLINE":
                    UserInfo.statut = onlineEmoji.asFormat() + "En ligne";
                    break;
                case "OFFLINE":
                    UserInfo.statut = offlineEmoji.asFormat() + "Hors ligne";
                    break;
            }

            channel.createEmbed(embed ->
                    embed.setColor(Color.of(54,57,63))
                            .setAuthor(author, null, member.getAvatarUrl())
                            .addField("Membre depuis le", memberOld, true)
                            .addField("Création du compte le", "           " + accountOld, true)
                            .addField("Statut", statut, true)
                            .addField("ID", member.getId().toString(), false)
                            .setFooter("Yua", null)
                            .setTimestamp(Instant.now())
            ).block();
            LogsManager.logAction("UserInfo", member, UserInfo.class);
        });
    }
 //
}
