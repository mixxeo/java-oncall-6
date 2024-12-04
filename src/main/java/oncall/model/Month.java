package oncall.model;

import java.util.Arrays;
import java.util.List;
import oncall.constant.ExceptionMessage;

public enum Month {
    JANUARY(1, 31, List.of(1)),
    FEBRUARY(2, 28, List.of()),
    MARCH(3, 31, List.of(1)),
    APRIL(4, 30, List.of()),
    MAY(5, 31, List.of(5)),
    JUNE(6, 30, List.of(6)),
    JULY(7, 31, List.of()),
    AUGUST(8, 31, List.of(15)),
    SEPTEMBER(9, 30, List.of()),
    OCTOBER(10, 31, List.of(3, 9)),
    NOVEMBER(11, 30, List.of()),
    DECEMBER(12, 31, List.of(25));

    private final int number;
    private final int numberOfDays;
    private final List<Integer> holidays;

    Month(final int number, final int numberOfDays, final List<Integer> holidays) {
        this.number = number;
        this.numberOfDays = numberOfDays;
        this.holidays = holidays;
    }

    public static Month getMonth(int number) {
        return Arrays.stream(Month.values())
                .filter(month -> month.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_INPUT));
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public boolean isHoliday(int date) {
        return holidays.contains(date);
    }
}
