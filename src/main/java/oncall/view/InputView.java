package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.constant.ExceptionMessage;
import org.junit.platform.commons.util.StringUtils;

public class InputView {
    public String read() {
        String input = Console.readLine();
        validateEmptyInput(input);
        return input;
    }

    private void validateEmptyInput(final String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT);
        }
    }
}
