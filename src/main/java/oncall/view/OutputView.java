package oncall.view;

import java.util.List;
import java.util.ListIterator;
import oncall.model.DayOfWeek;
import oncall.model.Nickname;
import oncall.model.WorkMonth;

public class OutputView {
    private static final String ONCALL_MONTH_REQUEST = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String WEEKDAY_MEMBERS_ORDER_REQUEST = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String HOLIDAY_MEMBERS_ORDER_REQUEST = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String SCHEDULE_FORMAT = "%d월 %d일 %s %s%n";
    private static final String DAY_OF_WEEK_HOLIDAY_MARK = "(휴일)";

    public void printOncallMonthRequest() {
        System.out.print(ONCALL_MONTH_REQUEST);
    }

    public void printWeekdayMembersOrderRequest() {
        System.out.print(WEEKDAY_MEMBERS_ORDER_REQUEST);
    }

    public void printHolidayMembersOrderRequest() {
        System.out.print(HOLIDAY_MEMBERS_ORDER_REQUEST);
    }

    public void printOncallScheduler(WorkMonth workMonth, List<Nickname> scheduler) {
        StringBuilder schedules = new StringBuilder();
        ListIterator<Nickname> iterator = scheduler.listIterator();
        DayOfWeek dayOfWeek = workMonth.startDay();
        while (iterator.hasNext()) {
            int date = iterator.nextIndex() + 1;
            schedules.append(String.format(SCHEDULE_FORMAT, workMonth.month().getNumber(), date,
                    getDayOfWeek(workMonth, date, dayOfWeek), iterator.next()));
            dayOfWeek = dayOfWeek.getNextDay();
        }
        System.out.println(schedules);
    }

    private String getDayOfWeek(WorkMonth workMonth, int date, DayOfWeek dayOfWeek) {
        if (!dayOfWeek.isHoliday() && workMonth.isHoliday(date)) {
            return dayOfWeek.getKorean();
        }
        return dayOfWeek.getKorean() + DAY_OF_WEEK_HOLIDAY_MARK;
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
