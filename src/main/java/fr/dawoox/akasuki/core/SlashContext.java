package fr.dawoox.akasuki.core;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ApplicationCommandInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import fr.dawoox.akasuki.Akasuki;

import java.util.List;

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

    public GatewayDiscordClient getClient() {
        return this.event.getClient();
    }

    public Boolean authorIsOwner() {
        return getAuthorId().equals(Akasuki.getOwnerId());
    }

    public Snowflake getSelfId() {
        return getClient().getSelfId();
    }

    public ApplicationCommandInteractionEvent getEvent() {
        return event;
    }
}
