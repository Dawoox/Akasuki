package fr.dawoox.akasuki.commands.owner;

import com.sun.tools.javac.util.List;
import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.User;
import discord4j.rest.http.client.ClientException;
import fr.dawoox.akasuki.commands.CommandException;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * Display a list of commands or the usage of a specified command
 * @author Dawoox
 * @version 1.0.0
 */
public class SendMessageCmd extends BaseCmd {

    public SendMessageCmd() {
        super(CommandCategory.OWNER, CommandPermission.OWNER, List.of("send_msg"));
    }

    @Override
    public void execute(Context context){
        final java.util.List<String> args = context.requireArgs(2);

        if (Long.valueOf(args.get(0)).equals(context.getClient().getSelfId().asLong())) {
            throw IDerror(context);
        }

        User user = context.getClient().getUserById(Snowflake.of(args.get(0)))
                .onErrorMap(ClientException.isStatusCode(HttpResponseStatus.FORBIDDEN.code()), err -> Usererror(context)).block();

        user.getPrivateChannel().block().createMessage(args.get(1)).block();
        context.getChannel().createMessage(String.format(":white_check_mark: Successfully send msg to user with ID **%s**", args.get(0))).block();
    }

    public CommandException Usererror(Context context) {
        context.getChannel().createMessage(":x: Cannot send messages to this user").block();
        return new CommandException("User not found/does not accept dm.");
    }

    public CommandException IDerror(Context context) {
        context.getChannel().createMessage(":x: Cannot send messages to myself").block();
        return new CommandException("User is myself.");
    }
}
