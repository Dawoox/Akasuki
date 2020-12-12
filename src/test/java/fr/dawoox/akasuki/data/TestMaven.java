package fr.dawoox.akasuki.data;

import org.junit.jupiter.api.Test;

public class TestMaven {

    @Test
    public void TestProjectVersion() {
        assert Maven.PROJECT_VERSION != null;
    }

    @Test
    public void TestDiscord4JVersion() {
        assert Maven.DISCORD4J_VERSION != null;
    }
}
