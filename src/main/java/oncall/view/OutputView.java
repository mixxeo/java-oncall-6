package oncall.view;

public class OutputView {
    private static final String ONCALL_REQUEST = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";

    public void printOncallMonthRequest() {
        System.out.print(ONCALL_REQUEST);
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
