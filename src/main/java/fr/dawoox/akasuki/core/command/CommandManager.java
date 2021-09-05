package fr.dawoox.akasuki.core.command;

import fr.dawoox.akasuki.commands.gifs.Hug;
import fr.dawoox.akasuki.commands.gifs.Kiss;
import fr.dawoox.akasuki.commands.images.Apod;
import fr.dawoox.akasuki.commands.images.Stonks;
import fr.dawoox.akasuki.commands.music.PlayCmd;
import fr.dawoox.akasuki.commands.owner.LeaveGuildCmd;
import fr.dawoox.akasuki.commands.owner.SayCmd;
import fr.dawoox.akasuki.commands.utils.*;
import fr.dawoox.akasuki.commands.moderator.KickCmd;
import fr.dawoox.akasuki.commands.owner.SendMessageCmd;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static fr.dawoox.akasuki.Akasuki.DEFAULT_LOGGER;

public class CommandManager {

    private static CommandManager instance;

    static {
        CommandManager.instance = new CommandManager();
    }

    private final Map<String, BaseCmd> commandsMap;

    private CommandManager() {
        this.commandsMap = CommandManager.initialize(
                //Owner Commands
                new SendMessageCmd(), new LeaveGuildCmd(), new SayCmd(),

                //Utilities Commands
                new UserInfoCmd(), new InfoCmd(), /*new ServerInfoCmd(), region check is deactivated now on discord side*/ new WeatherCmd(), new EpicGameStoreCmd(),

                //Moderation Commands
                //new KickCmd(), command to fix

                //Image Commands
                new Apod(), new Stonks(),

                //Music Commands
                new PlayCmd(),

                //Fun Commands
                new Hug(), new Kiss()
        );
    }

    private static Map<String, BaseCmd> initialize(BaseCmd... cmds) {
        final Map<String, BaseCmd> map = new LinkedHashMap<>();
        for (final BaseCmd cmd : cmds){
            for (final String name : cmd.getNames()) {
                if (map.putIfAbsent(name, cmd) != null) {
                    DEFAULT_LOGGER.error("Collision between names of {} and {}", name, map.get(name).getClass().getSimpleName());
                }
            }
            cmd.setEnabled(true);
        }
        DEFAULT_LOGGER.info("{} commands initialized", cmds.length);
        return Collections.unmodifiableMap(map);
    }

    public Map<String, BaseCmd> getCommands() {
        return this.commandsMap;
    }

    public BaseCmd getCommand(String name) {
        return this.commandsMap.get(name);
    }

    public static CommandManager getInstance() {
        return instance;
    }

    public int getCommandsCount() {
        return getCommands().size();
    }
}
