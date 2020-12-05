package fr.dawoox.akasuki.commands.music;

import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.VoiceChannel;
import fr.dawoox.akasuki.core.command.BaseCmd;

import java.util.Map;

/**
 * Let the bot join the vocal channel where the sender is into /!\ IN BETA /!\
 * @author Dawoox
 * @version 1.0.0
 */
public class Join {

    public static void reg(Map<String, BaseCmd> commands){
        commands.put("join", event -> {
            final Member member = event.getMember().orElse(null);
            if (member != null) {
                final VoiceState voiceState = member.getVoiceState().block();
                if (voiceState != null) {
                    final VoiceChannel channel = voiceState.getChannel().block();
                    if (channel != null) {
                        channel.join(spec -> spec.setProvider(Play.getProvider())).block();
                    }
                }
            }
        });
    }

}
