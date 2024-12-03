package oncall.model;

import java.util.Arrays;
import oncall.constant.ExceptionMessage;

public enum Month {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private final int number;
    private final int endDate;

    Month(final int number, final int endDate) {
        this.number = number;
        this.endDate = endDate;
    }

    public static Month getMonth(int number) {
        return Arrays.stream(Month.values())
                .filter(month -> month.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_INPUT));
    }
}
