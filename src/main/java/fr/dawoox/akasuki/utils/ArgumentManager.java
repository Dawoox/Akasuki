package fr.dawoox.akasuki.utils;

import java.util.regex.Pattern;

public class ArgumentManager {

    public static String[] getBody(String string){
        return Pattern.compile(" ").split(string);
    }

}
