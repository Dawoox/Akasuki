package fr.dawoox.akasuki.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUrlChecker {

    @Test
    public void testIsValid(){
        assertTrue(UrlChecker.isValid("https://google.fr"));
    }

}
