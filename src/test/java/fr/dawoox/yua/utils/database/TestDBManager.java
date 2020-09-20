package fr.dawoox.yua.utils.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDBManager {

    @Test
    public void TestDBManager() {
        assertTrue(DBManager.isLink());
    }

}
