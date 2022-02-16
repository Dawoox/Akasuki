package fr.dawoox.akasuki.core.command.slashcommands;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ApplicationCommandInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Permission;
import fr.dawoox.akasuki.Akasuki;
import fr.dawoox.akasuki.core.command.CommandPermission;

import java.util.List;
import java.util.Objects;

public class SlashContext {

    private final ChatInputInteractionEvent event;
    private final String cmdName;

    public SlashContext(ChatInputInteractionEvent event) {
        this.event = event;
        this.cmdName = event.getCommandName();
    }

    public List<ApplicationCommandInteractionOption> getOptions() {
        return event.getOptions();
    }

    public String getCommandName() {
        return this.cmdName;
    }

    public User getAuthor() {
        return event.getInteraction().getUser();
    }

    public Member getAuthorAsMember() {
        return event.getInteraction().getMember().get();
    }

    public Snowflake getAuthorId() {
        return this.getAuthor().getId();
    }

    public MessageChannel getChannel() {
        return event.getInteraction().getChannel().block();
    }

    public Snowflake getChannelId() {
        return getChannel().getId();
    }

    public Guild getGuild() {
        return event.getInteraction().getGuild().block();
    }

    public Snowflake getGuildId() {
        return getGuild().getId();
    }

    public ApplicationCommandInteractionOption getOption(String name) {
        return event.getOption(name).get();
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

    public ApplicationCommandInteractionEvent getEvent() {
        return event;
    }
}
