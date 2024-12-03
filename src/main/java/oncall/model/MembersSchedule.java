package oncall.model;

import java.util.HashSet;
import java.util.Set;
import oncall.constant.ExceptionMessage;

public class MembersSchedule {
    private final MembersOrder weekdayMembersOrder;
    private final MembersOrder holidayMembersOrder;

    private MembersSchedule(MembersOrder weekdayMembersOrder, MembersOrder holidayMembersOrder) {
        this.weekdayMembersOrder = weekdayMembersOrder;
        this.holidayMembersOrder = holidayMembersOrder;
    }

    public static MembersSchedule from(MembersOrder weekdayMembersOrder, MembersOrder holidayMembersOrder) {
        validate(weekdayMembersOrder, holidayMembersOrder);
        return new MembersSchedule(weekdayMembersOrder, holidayMembersOrder);
    }

    private static void validate(MembersOrder weekdayMembersOrder, MembersOrder holidayMembersOrder) {
        Set<Nickname> weekdayMembers = new HashSet<>(weekdayMembersOrder.getMembers());
        Set<Nickname> holidayMembers = new HashSet<>(holidayMembersOrder.getMembers());

        if (!weekdayMembers.containsAll(holidayMembers)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT);
        }
    }
}
