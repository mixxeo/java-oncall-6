package oncall.controller;

import java.util.List;
import oncall.model.Month;
import oncall.model.Schedule;
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
        requestWithRetry(this::requestOncallMonth);
    }


    private Schedule requestOncallMonth() {
        outputView.printOncallMonthRequest();
        String oncallMonthRequest = inputView.read();
        List<String> monthAndDay = List.of(oncallMonthRequest.split(","));
        int month = Integer.parseInt(monthAndDay.get(0));
        String day = monthAndDay.get(1);
        return new Schedule(Month.getMonth(month), day);
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
