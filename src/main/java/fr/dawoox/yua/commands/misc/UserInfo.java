package fr.dawoox.yua.commands.misc;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import fr.dawoox.yua.utils.Command;
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

    public static void reg(Map<String, Command> commands){
        commands.put("userinfo", event -> {
            final MessageChannel channel = event.getMessage().getChannel().block();

            if (!event.getMessage().getUserMentionIds().isEmpty()){
                UserInfo.member = event.getMessage().getUserMentions().blockFirst().asMember(event.getGuildId().get()).block();
                UserInfo.author = member.getUsername() + "#" + member.getDiscriminator();
                UserInfo.memberOld = format(member.getJoinTime())
                        + "\n(soit il y a " + diffInDays(member.getJoinTime(), Instant.now()) + " jours)";
                UserInfo.accountOld = format(MemberManager.getAccountCreationDate(member))
                        + "\n(soit il y a " + diffInDays(MemberManager.getAccountCreationDate(member), Instant.now()) + " jours)";

            } else {
                UserInfo.member = event.getMember().orElse(null);
                UserInfo.author = member.getUsername() + "#" + member.getDiscriminator();
                UserInfo.memberOld = format(member.getJoinTime())
                        + "\n(soit il y a " + diffInDays(member.getJoinTime(), Instant.now()) + " jours)";
                UserInfo.accountOld = format(MemberManager.getAccountCreationDate(member))
                        + "\n(soit il y a " + diffInDays(MemberManager.getAccountCreationDate(member), Instant.now()) + " jours)";

            }

            channel.createEmbed(embed ->
                    embed.setColor(Color.of(54,57,63))
                            .setAuthor(author, null, member.getAvatarUrl())
                            .addField("Membre depuis le", memberOld, true)
                            .addField("Création du compte le", "           " + accountOld, true)
                            .setFooter("Yua", null)
                            .setTimestamp(Instant.now())
            ).block();
        });
    }
 //
}
