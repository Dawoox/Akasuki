package fr.dawoox.akasuki.commands.moderator;

import discord4j.core.object.entity.Member;
import fr.dawoox.akasuki.utils.template.EmbedTemplate;
import fr.dawoox.akasuki.utils.ArgumentUtils;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.utils.LogsWriter;

import java.util.Map;
import java.util.Objects;

/**
 * Ban the member mentioned
 * @author Dawoox
 * @version 1.0.0
 */
public class Ban {
    private static String reply = "default error";

    public static void reg(Map<String, BaseCmd> commands){
        commands.put("ban", event -> {
            final Member sender = event.getMessage().getAuthorAsMember().block();
            final boolean canBan = Objects.requireNonNull(Objects.requireNonNull(sender).getBasePermissions().block()).toString().contains("BAN_MEMBERS");

            if (canBan && !event.getMessage().getUserMentionIds().isEmpty()){
                final Member target = Objects.requireNonNull(event.getMessage().getUserMentions().blockFirst()).asMember(event.getGuildId().get()).block();
                assert target != null;
                reply = sender.getUsername() + " vient de bannir " + target.getUsername();

                String[] temp = ArgumentUtils.getBody(event.getMessage().getContent());
                if (temp.length >= 3){
                    reply += " pour :";
                    for (int i=2;i<=temp.length - 1; i++){
                        reply += " " + temp[i];
                    }
                }

                System.out.println("1");
                target.ban(banQuerySpec -> banQuerySpec.setReason(reply).setDeleteMessageDays(0)).block();
                System.out.println("2");

                EmbedTemplate.sendEmbed(Objects.requireNonNull(event.getMessage().getChannel().block()), reply, "ban");
                LogsWriter.logAction("Ban : ", sender, Ban.class);
            }
        });
    }
}
