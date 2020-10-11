package fr.dawoox.akasuki.utils;

import discord4j.core.event.domain.message.MessageCreateEvent;

/**
 * Interface for executing commands
 * @author Dawoox
 * @version 1.0.0
 */
public interface Command {
    void execute(MessageCreateEvent event);
}
