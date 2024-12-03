package oncall.model;

public class Schedule {
    private final Month month;
    private final String startDay;

    public Schedule(Month month, String startDay) {
        this.month = month;
        this.startDay = startDay;
    }
}
