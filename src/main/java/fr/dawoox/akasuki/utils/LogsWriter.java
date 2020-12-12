package fr.dawoox.akasuki.utils;

import discord4j.core.object.entity.Member;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Wrapper for logs
 * @author Dawoox
 * @version 1.0.0
 */
public class LogsWriter {

    /**
     * Write a action to logs
     * @param action
     * String which explain what action has been done
     * @param sender
     * The member who as done the action
     * @param actionClass
     * Class were the action has been run
     * @since 1.0.0
     */
    public static void logAction(String action, Member sender, Class actionClass){
        String reply = " " + sender.getUsername() + "." + Objects.requireNonNull(sender.getGuild().block()).getName() + " - " + action;
        LoggerFactory.getLogger(actionClass).debug(reply);
    }

}
