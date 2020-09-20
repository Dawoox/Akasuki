package fr.dawoox.yua.commands.gifs;

import org.apache.commons.validator.UrlValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHug {

    @Test
    public void testGetRandomLink() {
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid(Hug.getRandomLink()));
    }
}
