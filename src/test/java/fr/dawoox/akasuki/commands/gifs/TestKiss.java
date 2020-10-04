package fr.dawoox.akasuki.commands.gifs;

import fr.dawoox.akasuki.utils.UrlChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKiss {

    @Test
    public void testGetRandomLink() {
        assertTrue(UrlChecker.isValid(Kiss.getRandomLink()));
    }
}
