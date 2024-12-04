package oncall.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import oncall.model.DayOfWeek;
import oncall.model.MembersOrder;
import oncall.model.MembersSchedule;
import oncall.model.Month;
import oncall.model.Nickname;
import oncall.model.WorkMonth;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {
    private final InputView inputView;
    public final OutputView outputView;

    public OncallController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        WorkMonth workMonth = requestWithRetry(this::requestMonth);
        MembersSchedule membersSchedule = requestWithRetry(this::requestMembersSchedule);
        generateSchedule(workMonth, membersSchedule);
    }


    private WorkMonth requestMonth() {
        outputView.printOncallMonthRequest();
        String monthRequest = inputView.read();
        List<String> monthAndDayOfWeek = List.of(monthRequest.split(","));
        int month = Integer.parseInt(monthAndDayOfWeek.get(0));
        String dayOfWeek = monthAndDayOfWeek.get(1);
        return new WorkMonth(Month.getMonth(month), DayOfWeek.getDay(dayOfWeek));
    }

    private MembersSchedule requestMembersSchedule() {
        outputView.printWeekdayMembersOrderRequest();
        MembersOrder weekdayMembersOrder = createMembersOrder(inputView.read());
        outputView.printHolidayMembersOrderRequest();
        MembersOrder holidayMembersOrder = createMembersOrder(inputView.read());
        return MembersSchedule.from(weekdayMembersOrder, holidayMembersOrder);
    }

    private MembersOrder createMembersOrder(String request) {
        List<Nickname> members = Arrays.stream(request.split(","))
                .map(Nickname::of)
                .toList();
        return MembersOrder.of(members);
    }

    private void generateSchedule(WorkMonth workMonth, MembersSchedule membersSchedule) {
        LinkedList<Nickname> scheduler = new LinkedList<>();
        DayOfWeek dayOfWeek = workMonth.startDay();
        for (int date = 1; date <= workMonth.getNumberOfDays(); date++) {
            Nickname assignedMember = membersSchedule.assignMember(workMonth, dayOfWeek, date);
            if (!scheduler.isEmpty() && assignedMember == scheduler.getLast()) {
                assignedMember = membersSchedule.getSubstitutedMember(workMonth, dayOfWeek, date);
            }
            scheduler.add(assignedMember);
            dayOfWeek = dayOfWeek.getNextDay();
        }
    }

    private <T> T requestWithRetry(SupplierWithException<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return requestWithRetry(request);
        }
    }

    @FunctionalInterface
    private interface SupplierWithException<T> {
        T get() throws IllegalArgumentException;
    }
}
