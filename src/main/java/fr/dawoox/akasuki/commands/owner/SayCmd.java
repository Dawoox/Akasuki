package fr.dawoox.akasuki.commands.owner;

import com.sun.tools.javac.util.List;
import fr.dawoox.akasuki.core.command.BaseCmd;
import fr.dawoox.akasuki.core.command.CommandCategory;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.core.command.Context;

public class SayCmd extends BaseCmd {

    public SayCmd() {
        super(CommandCategory.OWNER, CommandPermission.OWNER, List.of("say"));
    }

    @Override
    public void execute(Context context) {
        final java.util.List<String> args = context.requireArgs(1);

        context.getMessage().delete().block();
        context.getChannel().createMessage(args.get(0)).block();
    }
}
