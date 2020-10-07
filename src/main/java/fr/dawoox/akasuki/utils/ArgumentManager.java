package fr.dawoox.akasuki.utils;

import java.util.regex.Pattern;

public class ArgumentManager {

    public static String[] getBody(String string){
        String[] temp = Pattern.compile(" ").split(string);
        return temp;
    }

}
