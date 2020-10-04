package fr.dawoox.akasuki.commands.music;

import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.VoiceChannel;
import fr.dawoox.akasuki.utils.Command;

import java.util.Map;

public class Join {

    public static void reg(Map<String, Command> commands){
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
