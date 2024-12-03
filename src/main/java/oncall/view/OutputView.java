package oncall.view;

public class OutputView {
    private static final String ONCALL_MONTH_REQUEST = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String WEEKDAY_MEMBERS_ORDER_REQUEST = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String HOLIDAY_MEMBERS_ORDER_REQUEST = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

    public void printOncallMonthRequest() {
        System.out.print(ONCALL_MONTH_REQUEST);
    }

    public void printWeekdayMembersOrderRequest() {
        System.out.println(WEEKDAY_MEMBERS_ORDER_REQUEST);
    }

    public void printHolidayMembersOrderRequest() {
        System.out.println(HOLIDAY_MEMBERS_ORDER_REQUEST);
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
