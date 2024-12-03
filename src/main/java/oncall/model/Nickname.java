package oncall.model;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Nickname nickname = (Nickname) o;
        return Objects.equals(name, nickname.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
