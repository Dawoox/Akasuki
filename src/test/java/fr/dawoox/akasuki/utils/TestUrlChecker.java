package fr.dawoox.akasuki.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing class for UrlChecker
 * @author Dawoox
 * @version 1.0.0
 */
public class TestUrlChecker {

    /**
     * isValid test methode
     * @since 1.0.0
     */
    @Test
    public void testIsValid(){
        assertTrue(UrlChecker.isValid("https://google.fr"));
    }

}
