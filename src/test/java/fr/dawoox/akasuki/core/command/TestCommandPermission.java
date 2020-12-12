package fr.dawoox.akasuki.core.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCommandPermission {

    @Test
    public void TestPowerLevels() {
        assertTrue(CommandPermission.OWNER.getPower() > CommandPermission.ADMIN.getPower());
        assertTrue(CommandPermission.ADMIN.getPower() > CommandPermission.USER.getPower());
    }
}
