package fr.dawoox.yua.commands.misc;

import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.GuildEmoji;
import fr.dawoox.yua.utils.Command;

import java.time.Duration;
import java.util.Map;
import java.util.regex.Pattern;

public class Emoji {
    public static void reg(Map<String, Command> commands){
        commands.put("emoji", event -> {
            String[] temp = getEmojiId(event.getMessage().getContent());
            if (temp.length <= 1) return;
            String emojiId = temp[2].substring(0, temp[2].length() -1);
            GuildEmoji emoji = event.getGuild().block().getGuildEmojiById(Snowflake.of(emojiId)).block(Duration.ofSeconds(2));
            event.getMessage().getChannel().block().createMessage(emoji.getImageUrl()).block();
        });
    }

    public static String[] getEmojiId(String string){
        String[] temp = Pattern.compile(":").split(string);
        return temp;
    }
}
