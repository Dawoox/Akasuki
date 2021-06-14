package fr.dawoox.akasuki.commands.owner;

import com.sun.tools.javac.util.List;
import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Guild;
import discord4j.rest.http.client.ClientException;
import fr.dawoox.akasuki.commands.CommandException;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import io.netty.handler.codec.http.HttpResponseStatus;

public class LeaveGuildCmd extends BaseCmd {

    public LeaveGuildCmd() {
        super(CommandCategory.OWNER, CommandPermission.OWNER, List.of("leave_guild"));
    }

    @Override
    public void execute(Context context) {
        final String arg = context.requireArg();

        Guild guild = context.getClient().getGuildById(Snowflake.of(arg))
                .onErrorMap(ClientException.isStatusCode(HttpResponseStatus.FORBIDDEN.code()), err -> error(context)).block();

        guild.leave().block();
        context.getChannel().createMessage(String.format(":white_check_mark: Successfully leave guild with ID **%s**", arg)).block();
    }

    public CommandException error(Context context) {
        context.getChannel().createMessage(":x: Cannot leave that guild").block();
        return new CommandException("Guild not found.");
    }
}
