package fr.dawoox.akasuki.commands.misc;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.GuildEmoji;
import fr.dawoox.akasuki.utils.Command;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Emoji {
    public static void reg(Map<String, Command> commands){
        commands.put("emoji", event -> {
            String[] temp = getEmojiId(event.getMessage().getContent());
            if (temp.length <= 1) return;
            String emojiId = temp[2].substring(0, temp[2].length() -1);
            GuildEmoji emoji = Objects.requireNonNull(event.getGuild().block()).getGuildEmojiById(Snowflake.of(emojiId)).block(Duration.ofSeconds(2));
            Objects.requireNonNull(event.getMessage().getChannel().block()).createMessage(Objects.requireNonNull(emoji).getImageUrl()).block();
        });
    }

    public static String[] getEmojiId(String string){
        return Pattern.compile(":").split(string);
    }
}
