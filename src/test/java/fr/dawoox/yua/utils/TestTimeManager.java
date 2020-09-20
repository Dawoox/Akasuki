package fr.dawoox.yua.utils;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static fr.dawoox.yua.utils.TimeManager.diffInDays;
import static fr.dawoox.yua.utils.TimeManager.diffInMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTimeManager {

    @Test
    public void testDiffInDays() {
        assertEquals(0, diffInDays(Instant.now(), Instant.now()));
    }

    @Test
    public void testDiffInMillis() {
        assertEquals(0, diffInMillis(Instant.now(), Instant.now()));
    }
}
