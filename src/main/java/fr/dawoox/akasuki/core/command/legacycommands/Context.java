package fr.dawoox.akasuki.core.command.legacycommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Permission;
import fr.dawoox.akasuki.Akasuki;
import fr.dawoox.akasuki.commands.MissingArgumentException;
import fr.dawoox.akasuki.core.command.CommandPermission;
import fr.dawoox.akasuki.utils.StringUtils;
import reactor.util.annotation.Nullable;

import java.util.List;
import java.util.Objects;
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

    public String requireArg() {
        return this.getArg()
                .map(StringUtils::normalizeSpace)
                .orElseThrow(MissingArgumentException::new);
    }

    public List<String> requireArgs(int count) {
        return this.requireArgs(count, count);
    }

    public List<String> requireArgs(int min, int max) {
        return this.requireArgs(min, max, " ");
    }

    public List<String> requireArgs(int min, int max, String delimiter) {
        final List<String> args = StringUtils.split(this.requireArg(), max, delimiter);
        if (args.size() < min && args.size() > max) {
            throw new MissingArgumentException();
        }
        return args;
    }

    public Message getMessage() {
        return event.getMessage();
    }

    public String getCommandName() {
        return this.cmdName;
    }

    public User getAuthor() {
        return event.getMessage().getAuthor().get();
    }

    public Member getAuthorAsMember() {
        return getMessage().getAuthorAsMember().block();
    }

    public MessageChannel getChannel() {
        return event.getMessage().getChannel().block();
    }

    public Snowflake getAuthorId() {
        return this.getAuthor().getId();
    }

    public Guild getGuild() {
        return event.getGuild().block();
    }

    public Snowflake getGuildId() {
        return getGuild().getId();
    }

    public Member getMember() {
        return event.getMember().get();
    }

    public CommandPermission getPermissions() {
        if (this.getAuthorId().equals(Akasuki.getOwnerId())) {
            return CommandPermission.OWNER;
        } else if (this.getChannel().getType().equals(Channel.Type.DM)
                || Objects.requireNonNull(Objects.requireNonNull(getAuthor().asMember(getGuildId()).block()).getBasePermissions().block()).contains(Permission.ADMINISTRATOR)) {
            return CommandPermission.ADMIN;
        }

        return CommandPermission.USER;
    }

    public GatewayDiscordClient getClient() {
        return this.event.getClient();
    }

    public Snowflake getSelfId() {
        return getClient().getSelfId();
    }
}
