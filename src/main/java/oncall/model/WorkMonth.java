package oncall.model;

public class WorkMonth {
    private final Month month;
    private final String startDay;

    public WorkMonth(Month month, String startDay) {
        this.month = month;
        this.startDay = startDay;
    }
}
