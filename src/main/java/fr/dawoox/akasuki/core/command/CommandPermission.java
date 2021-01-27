package fr.dawoox.akasuki.core.command;

public enum CommandPermission {

    USER(1),
    ADMIN(3),
    OWNER(9);

    private final int power;

    CommandPermission(int power) {
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }
}
