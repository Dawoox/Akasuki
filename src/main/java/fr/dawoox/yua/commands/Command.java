package fr.dawoox.yua.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public interface Command {
    // Since we are expecting to do reactive things in this method, like
    // send a message, then this method will also return a reactive type.
    Mono<Void> execute(MessageCreateEvent event);
}
