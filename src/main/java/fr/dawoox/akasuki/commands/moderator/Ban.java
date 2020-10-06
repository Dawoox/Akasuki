package fr.dawoox.akasuki.commands.moderator;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import discord4j.core.object.entity.Member;
import fr.dawoox.akasuki.commands.gifs.EmbedTemplate;
import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.LogsManager;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Ban {
    private static String reply = "default error";

    public static void reg(Map<String, Command> commands){
        commands.put("ban", event -> {
            final Member sender = event.getMessage().getAuthorAsMember().block();
            final boolean canBan = Objects.requireNonNull(Objects.requireNonNull(sender).getBasePermissions().block()).toString().contains("BAN_MEMBERS");

            if (canBan && !event.getMessage().getUserMentionIds().isEmpty()){
                final Member target = Objects.requireNonNull(event.getMessage().getUserMentions().blockFirst()).asMember(event.getGuildId().get()).block();
                assert target != null;
                reply = sender.getUsername() + " vient de bannir " + target.getUsername();

                String[] temp = getBody(event.getMessage().getContent());
                if (temp.length >= 2){
                    reply += " pour :";
                    for (int i=2;i<=temp.length - 1; i++){
                        reply += " " + temp[i];
                    }
                }

                System.out.println("1");
                target.ban(banQuerySpec -> banQuerySpec.setReason(reply).setDeleteMessageDays(0)).block();
                System.out.println("2");

                EmbedTemplate.sendEmbed(Objects.requireNonNull(event.getMessage().getChannel().block()), reply, "ban");
                LogsManager.logAction("Ban", sender, Ban.class);
            }
        });
    }

    public static String[] getBody(String string){
        String[] temp = Pattern.compile(" ").split(string);
        return temp;
    }
}
