package oncall.model;

import java.util.Arrays;
import oncall.constant.ExceptionMessage;

public enum DayOfWeek {
    MONDAY("월", 1),
    TUESDAY("화", 2),
    WEDNESDAY("수", 3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6),
    SUNDAY("일", 7);

    private static final int DAYS_OF_A_WEEK = 7;

    private final String korean;
    private final int order;

    DayOfWeek(final String korean, final int order) {
        this.korean = korean;
        this.order = order;
    }

    public static DayOfWeek getDay(String korean) {
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> dayOfWeek.korean.equals(korean))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_INPUT));
    }

    public boolean isHoliday() {
        return this == SATURDAY || this == SUNDAY;
    }

    public DayOfWeek getNextDay() {
        int nextOrder = getNextOrder();
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> dayOfWeek.order == nextOrder)
                .findFirst()
                .orElse(null);
    }

    private int getNextOrder() {
        return (order % DAYS_OF_A_WEEK) + 1;
    }

    public String getKorean() {
        return korean;
    }
}
