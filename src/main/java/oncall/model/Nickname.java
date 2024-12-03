package oncall.model;

import oncall.constant.ExceptionMessage;

public record Nickname(String name) {
    private static final int MAX_LENGTH = 5;

    public static Nickname of(String name) {
        validate(name);
        return new Nickname(name);
    }

    private static void validate(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT);
        }
    }
}
