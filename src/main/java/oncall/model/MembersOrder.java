package oncall.model;

import java.util.ArrayList;
import java.util.List;
import oncall.constant.ExceptionMessage;

public class MembersOrder {
    private static final int MIN_NUMBER_OF_MEMBERS = 5;
    private static final int MAX_NUMBER_OF_MEMBERS = 35;

    private final List<Nickname> members;

    private MembersOrder(List<Nickname> members) {
        this.members = members;
    }

    public static MembersOrder of(List<Nickname> members) {
        validate(members);
        return new MembersOrder(members);
    }

    private static void validate(List<Nickname> members) {
        validateDuplication(members);
        validateSize(members);
    }

    private static void validateDuplication(List<Nickname> members) {
        long distinctCount = members.stream().distinct().count();
        if (distinctCount != members.size()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT);
        }
    }

    private static void validateSize(List<Nickname> members) {
        if (members.size() < MIN_NUMBER_OF_MEMBERS || members.size() > MAX_NUMBER_OF_MEMBERS) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT);
        }
    }

    public List<Nickname> getMembers() {
        return new ArrayList<>(members);
    }

    public int getNextIndex(int index) {
        return (index % (members.size() - 1)) + 1;
    }
}
