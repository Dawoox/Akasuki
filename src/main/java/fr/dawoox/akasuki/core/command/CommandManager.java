package fr.dawoox.akasuki.core.command;

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
                new SendMessageCmd()
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
}
