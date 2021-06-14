package fr.dawoox.akasuki.commands.moderator;

import com.sun.tools.javac.util.List;
import discord4j.core.object.entity.Member;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import fr.dawoox.akasuki.utils.ArgumentUtils;
import fr.dawoox.akasuki.core.command.BaseCmd;

import java.util.Objects;

/**
 * Kick the member mentioned /!\ IN BETA /!\
 * @author Dawoox
 * @version 2.0.0
 */
public class KickCmd extends BaseCmd {
    private static String reply = "default error";

    public KickCmd() {
        super(CommandCategory.ADMIN, CommandPermission.ADMIN, List.of("kick", "k"));
    }

    @Override
    public void execute(Context context) {
        final Member sender = context.getAuthorAsMember();
        final java.util.List<String> args = context.requireArgs(1);

        if (!context.getMessage().getUserMentionIds().isEmpty()){
            final Member target = Objects.requireNonNull(context.getMessage().getUserMentions().blockFirst()).asMember(context.getGuildId()).block();
            assert target != null;
            reply = sender.getUsername() + " vient d'expulser " + target.getUsername();

            String[] temp = ArgumentUtils.getBody(context.getMessage().getContent());
            if (temp.length >= 3){
                reply += " pour :";
                for (int i=2;i<=temp.length - 1; i++){
                    reply += " " + temp[i];
                }
            }

            target.kick(reply).block();

            //EmbedTemplate.sendEmbed(Objects.requireNonNull(context.getMessage().getChannel().block()), reply, "kick");
        }
    }
}
