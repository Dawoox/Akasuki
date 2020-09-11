package fr.dawoox.yua.commands.misc;

import fr.dawoox.yua.utils.Command;

import java.util.Map;

public class Ping {

    public static void reg(Map<String, Command> commands){
        commands.put("ping", event -> event.getMessage()
                .getChannel().block()
                .createMessage("Pong!").block());
    }

}
