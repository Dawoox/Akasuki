package fr.dawoox.akasuki.core.command;

public enum CommandCategory {

    HIDDEN("Hidden"),
    UTILS("Utility"),
    IMAGE("Image"),
    ADMIN("Admin"),
    FUN("Fun"),
    MUSIC("Music"),
    OWNER("Owner");

    private final String name;

    CommandCategory(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
