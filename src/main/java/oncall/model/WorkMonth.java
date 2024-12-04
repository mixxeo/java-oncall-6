package oncall.model;

public record WorkMonth(Month month, DayOfWeek startDay) {
    public int getNumberOfDays() {
        return month.getNumberOfDays();
    }

    public boolean isHoliday(int date) {
        return month.isHoliday(date);
    }
}
