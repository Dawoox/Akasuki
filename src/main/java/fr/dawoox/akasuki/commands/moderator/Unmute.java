package fr.dawoox.akasuki.commands.moderator;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Member;
import fr.dawoox.akasuki.commands.gifs.EmbedTemplate;
import fr.dawoox.akasuki.utils.Command;
import fr.dawoox.akasuki.utils.LogsWriter;

import java.util.Map;
import java.util.Objects;

public class Unmute {
    private static String reply = "default error";

    public static void reg(Map<String, Command> commands){
        commands.put("unmute", event -> {
            final Member sender = event.getMessage().getAuthorAsMember().block();
            final boolean canUnmute = Objects.requireNonNull(Objects.requireNonNull(sender).getBasePermissions().block()).toString().contains("MANAGE_ROLES");
            Boolean roleAdded = true;
            Snowflake roleId = null;

            if (canUnmute && !event.getMessage().getUserMentionIds().isEmpty()){
                final Member target = Objects.requireNonNull(event.getMessage().getUserMentions().blockFirst()).asMember(event.getGuildId().get()).block();
                assert target != null;
                reply = target.getUsername() + " n'est plus muet";

                for (int i=0;i<event.getGuild().block().getRoleIds().size();i++){
                    String roleName = event.getGuild().block().getRoles().collectList().block().get(i).getName();
                    if (roleName.equals("Muet")){
                        roleAdded = false;
                        roleId = event.getGuild().block().getRoles().collectList().block().get(i).getId();
                    }
                }

                if (!roleAdded){
                    target.removeRole(roleId).block();

                    EmbedTemplate.sendEmbed(event.getMessage().getChannel().block(), reply);
                    LogsWriter.logAction("Unmute : ", sender, Unmute.class);
                }
            }
        });
    }
}
