package fr.dawoox.akasuki.commands.owner;

import com.sun.tools.javac.util.List;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import discord4j.rest.util.Permission;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;
import io.prometheus.client.Counter;

import java.time.Instant;
import java.util.function.Consumer;

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
        context.getMessage().getChannel().block().createMessage("Test").block();
    }

    /*
    @Override
    public Consumer<EmbedCreateSpec> getHelp(Context context) {
        return new EmbedCreateSpec().setColor(Color.VIVID_VIOLET).setFooter("Test", null).setTimestamp(Instant.now());
    }*/

}
