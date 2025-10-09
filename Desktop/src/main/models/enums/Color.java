package main.models.enums;

import lombok.Getter;

public enum Color {
    RED("\u001B[0;31m"),
    GREEN("\u001B[0;32m"),
    BLUE("\u001B[0;34m"),

    YELLOW("\u001B[0;33m"),
    PURPLE("\u001B[0;35m"),
    CYAN("\u001B[0;36m"),

    BLACK("\u001B[0;30m"),
    WHITE("\u001B[0;37m"),

    RED_BOLD("\u001B[1;31m"),
    GREEN_BOLD("\u001B[1;32m"),
    BLUE_BOLD("\u001B[1;34m"),

    YELLOW_BOLD("\u001B[1;33m"),
    PURPLE_BOLD("\u001B[1;35m"),
    CYAN_BOLD("\u001B[1;36m"),

    BLACK_BOLD("\u001B[1;30m"),
    WHITE_BOLD("\u001B[1;37m");

    @Getter public final String unicodeValue;

    private Color(String unicodeValue) {
        this.unicodeValue = unicodeValue;
    }

    public static String paint(Color color, String content) {
        if (color == null || content == null) {
            return content;
        }

        return color.getUnicodeValue() + content + "\u001B[0m";
    }
}
