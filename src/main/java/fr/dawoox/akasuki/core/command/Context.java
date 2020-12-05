package fr.dawoox.akasuki.core.command;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import fr.dawoox.akasuki.commands.MissingArgumentException;
import fr.dawoox.akasuki.utils.StringUtils;
import reactor.util.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public class Context {

    private final MessageCreateEvent event;
    private final String prefix;
    private final String cmdName;
    @Nullable
    private final String arg;

    public Context(MessageCreateEvent event, String prefix) {
        this.event = event;
        this.prefix = prefix;

        final List<String> splittedMsg = StringUtils.split(this.event.getMessage().getContent(), 2);
        this.cmdName = splittedMsg.get(0).substring(prefix.length()).toLowerCase();
        this.arg = splittedMsg.size() > 1 ? splittedMsg.get(1).trim() : null;
    }

    public Optional<String> getArg() {
        return Optional.ofNullable(this.arg);
    }

    public String requireArgs() {
        return this.getArg()
                .map(StringUtils::normalizeSpace)
                .orElseThrow(MissingArgumentException::new);
    }

    /*
    public List<String> requireArgs(int count) {
        return this.requireArgs(count, count);
    }*/

    public Message getMessage() {
        return event.getMessage();
    }

    public String getCommandName() {
        return this.cmdName;
    }
}
