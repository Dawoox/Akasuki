package fr.dawoox.akasuki.commands.owner;

import com.sun.tools.javac.util.List;
import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.User;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;

/**
 * Display a list of commands or the usage of a specified command
 * @author Dawoox
 * @version 1.0.0
 */
public class SendMessageCmd extends BaseCmd {

    public SendMessageCmd() {
        super(CommandCategory.OWNER, CommandPermission.OWNER, List.of("send_message"));
    }

    @Override
    public void execute(Context context){
        final java.util.List<String> args = context.requireArgs(2);

        final Long userId = Long.valueOf(args.get(0));
        if (userId == null || userId.equals(context.getClient().getSelfId().asLong())) {
            context.getChannel().createMessage("Je ne peut pas envoyer de message à moi-même ou à cette personne.. :f").block();
            return;
        }

        final User target = context.getClient().getUserById(Snowflake.of(userId)).block();
        target.getPrivateChannel().block().createMessage(args.get(1)).block();
    }

}
