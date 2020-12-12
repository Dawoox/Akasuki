package fr.dawoox.akasuki.commands.moderator;

import discord4j.core.object.entity.Member;
import fr.dawoox.akasuki.utils.template.EmbedTemplate;
import fr.dawoox.akasuki.utils.ArgumentUtils;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.utils.LogsWriter;

import java.util.Map;
import java.util.Objects;

/**
 * Kick the member mentioned /!\ IN BETA /!\
 * @author Dawoox
 * @version 1.0.0
 */
public class Kick {
    private static String reply = "default error";

    /*
    public static void reg(Map<String, BaseCmd> commands){
        commands.put("kick", event -> {
            final Member sender = event.getMessage().getAuthorAsMember().block();
            final boolean canKick = Objects.requireNonNull(Objects.requireNonNull(sender).getBasePermissions().block()).toString().contains("KICK_MEMBERS");

            if (canKick && !event.getMessage().getUserMentionIds().isEmpty()){
                final Member target = Objects.requireNonNull(event.getMessage().getUserMentions().blockFirst()).asMember(event.getGuildId().get()).block();
                assert target != null;
                reply = sender.getUsername() + " vient d'expulser " + target.getUsername();

                String[] temp = ArgumentUtils.getBody(event.getMessage().getContent());
                if (temp.length >= 3){
                    reply += " pour :";
                    for (int i=2;i<=temp.length - 1; i++){
                        reply += " " + temp[i];
                    }
                }

                target.kick(reply).block();

                EmbedTemplate.sendEmbed(Objects.requireNonNull(event.getMessage().getChannel().block()), reply, "kick");
                LogsWriter.logAction("Kick : ", sender, Ban.class);
            }
        });
    }*/
}
