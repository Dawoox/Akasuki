package fr.dawoox.akasuki.commands.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmoji {

    @Test
    public void testGetEmojiId() {
        String[] temp = Emoji.getEmojiId("<:test:145>");
        String emojiId = temp[2].substring(0, temp[2].length() -1);
        assertEquals("145", emojiId);
    }

}
