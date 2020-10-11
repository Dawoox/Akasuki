package fr.dawoox.akasuki.utils;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static fr.dawoox.akasuki.utils.TimeUtils.diffInDays;
import static fr.dawoox.akasuki.utils.TimeUtils.diffInMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing class for TimeUtils
 * @author Dawoox
 * @version 1.1.0
 */
public class TestTimeUtils {

    /**
     * testDiffInDays test methode
     * @since 1.0.0
     */
    @Test
    public void testDiffInDays() {
        assertEquals(0, diffInDays(Instant.now(), Instant.now()));
    }

    /**
     * testDiffInMillis test methode
     * @since 1.1.0
     */
    @Test
    public void testDiffInMillis() {
        assertEquals(0, diffInMillis(Instant.now(), Instant.now()));
    }
}
