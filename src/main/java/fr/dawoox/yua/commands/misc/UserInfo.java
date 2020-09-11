package fr.dawoox.yua.commands.misc;

import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import fr.dawoox.yua.utils.Command;

import java.time.Instant;
import java.util.Map;

import static fr.dawoox.yua.utils.InstantManager.JoinFormat;

public class UserInfo {

    public static void reg(Map<String, Command> commands){
        commands.put("userinfo", event -> {
            final Member member = event.getMember().orElse(null);
            final MessageChannel channel = event.getMessage().getChannel().block();

            final String author = member.getUsername() + "#" + member.getDiscriminator();

            channel.createEmbed(embed ->
                    embed.setColor(Color.of(54,57,63))
                            .setAuthor(author, null, member.getAvatarUrl())
                            .addField("**Membre depuis le**", JoinFormat(member.getJoinTime()), true)
                            .addField("**Création du compte le**", JoinFormat(member.getJoinTime()), true)
                            .setTimestamp(Instant.now())
            ).block();

            String test = Instant.now().toString();
            System.out.println("soit depuis " + test + " jours");
        });
    }

}
