package fr.dawoox.akasuki.core.command.legacycommands;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static fr.dawoox.akasuki.Akasuki.DEFAULT_LOGGER;

public class CommandManager {

    private static CommandManager instance;

    static {
        CommandManager.instance = new CommandManager();
    }

    private final Map<String, LegacyBaseCmd> commandsMap;

    private CommandManager() {
        this.commandsMap = CommandManager.initialize(
                //cmd
        );
    }

    private static Map<String, LegacyBaseCmd> initialize(LegacyBaseCmd... cmds) {
        final Map<String, LegacyBaseCmd> map = new LinkedHashMap<>();
        for (final LegacyBaseCmd cmd : cmds){
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

    public Map<String, LegacyBaseCmd> getCommands() {
        return this.commandsMap;
    }

    public LegacyBaseCmd getCommand(String name) {
        return this.commandsMap.get(name);
    }

    public static CommandManager getInstance() {
        return instance;
    }

    public int getCommandsCount() {
        return getCommands().size();
    }
}
