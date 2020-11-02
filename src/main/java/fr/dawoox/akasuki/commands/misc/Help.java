package fr.dawoox.akasuki.commands.misc;

import fr.dawoox.akasuki.utils.Command;

import java.util.Map;

/**
 * Return a embed message with all the help guide to understand the bot
 * @author Dawoox
 * @version 1.0.0
 */
public class Help {
    public static void reg(Map<String, Command> commands){
        commands.put("help", event -> {
            
        });
    }
}
