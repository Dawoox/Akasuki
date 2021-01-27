package fr.dawoox.akasuki.utils;

import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {

    private static final Pattern SPACES_PATTERN = Pattern.compile(" +");

    public static List<String> split(@Nullable String str){
        return StringUtils.split(str, -1);
    }

    public static List<String> split(@Nullable String str, int limit){
        return StringUtils.split(str, limit, " ");
    }

    public static List<String> split(@Nullable String str, int limit, @NonNull String delimiter){
        if (str == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(str.split(delimiter, limit))
                .map(String::trim)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
    }

    public static List<String> split(@Nullable String str, @NonNull String delimiter) {
        return StringUtils.split(str, -1, delimiter);
    }

    @Nullable
    public static String normalizeSpace(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return SPACES_PATTERN.matcher(str.trim()).replaceAll(" ");
    }
}
