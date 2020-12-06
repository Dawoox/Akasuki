package fr.dawoox.akasuki.commands.moderator;

import discord4j.common.util.Snowflake;
import discord4j.core.object.PermissionOverwrite;
import discord4j.core.object.entity.Member;
import discord4j.rest.util.Color;
import discord4j.rest.util.PermissionSet;
import fr.dawoox.akasuki.utils.template.EmbedTemplate;
import fr.dawoox.akasuki.utils.ArgumentUtils;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.utils.LogsWriter;

import java.util.Map;
import java.util.Objects;

/**
 * Mute the member mentioned
 * @author Dawoox
 * @version 1.1.0
 */
public class Mute {
    private static String reply = "default error";

    /*
    public static void reg(Map<String, BaseCmd> commands){
        commands.put("mute", event -> {
            final Member sender = event.getMessage().getAuthorAsMember().block();
            final boolean canMute = Objects.requireNonNull(Objects.requireNonNull(sender).getBasePermissions().block()).toString().contains("MANAGE_ROLES");
            Boolean roleNotCreated = true;
            Snowflake roleId = null;

            if (canMute && !event.getMessage().getUserMentionIds().isEmpty()){
                final Member target = Objects.requireNonNull(event.getMessage().getUserMentions().blockFirst()).asMember(event.getGuildId().get()).block();
                assert target != null;
                reply = sender.getUsername() + " vient de rendre muet " + target.getUsername();

                String[] temp = ArgumentUtils.getBody(event.getMessage().getContent());
                if (temp.length >= 3){
                    reply += " pour :";
                    for (int i=2;i<=temp.length - 1; i++){
                        reply += " " + temp[i];
                    }
                }

                for (int i=0;i<event.getGuild().block().getRoleIds().size();i++){
                    String roleName = event.getGuild().block().getRoles().collectList().block().get(i).getName();
                    if (roleName.equals("Muet")){
                        roleNotCreated = false;
                        roleId = event.getGuild().block().getRoles().collectList().block().get(i).getId();
                    }
                }

                if (roleNotCreated){
                    event.getGuild().block().createRole(RoleCreateSpec ->
                            RoleCreateSpec
                                    .setReason("AKASUKI AUTOMATIC TASKS : PLEASE DON'T CHANGE THE NAME OF THE ROLE")
                                    .setColor(Color.DISCORD_BLACK).setName("Muet")
                                    .setPermissions(PermissionSet.of(0)))
                            .block();
                    roleId = event.getGuild().block().getRoles().blockLast().getId();
                }

                assert target != null;
                target.addRole(roleId).block();

                for (int i=0;i<event.getGuild().block().getChannels().collectList().block().size();i++){
                    Snowflake channelId = event.getGuild().block().getChannels().collectList().block().get(i).getId();
                    event.getGuild().block().getChannelById(channelId).block()
                            .addRoleOverwrite(roleId, PermissionOverwrite.forRole(roleId, PermissionSet.none(), PermissionSet.of(2048)), "AKASUKI AUTOMATIC TASKS").block();
                }

                EmbedTemplate.sendEmbed(event.getMessage().getChannel().block(), reply, "mute");
                LogsWriter.logAction("Mute : ", sender, Mute.class);
            }
        });
    }*/
}
