package fr.dawoox.akasuki.commands;

public class CommandException extends RuntimeException {

    public CommandException(String msg) {
        super(msg, null, false, false);
    }

}
