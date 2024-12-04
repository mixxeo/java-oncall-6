package oncall.model;

import java.util.HashSet;
import java.util.Set;
import oncall.constant.ExceptionMessage;

public class MembersSchedule {
    private final MembersOrder weekdayMembersOrder;
    private final MembersOrder holidayMembersOrder;
    private int weekdayCursor;
    private int holidayCursor;

    private MembersSchedule(MembersOrder weekdayMembersOrder, MembersOrder holidayMembersOrder) {
        this.weekdayMembersOrder = weekdayMembersOrder;
        this.holidayMembersOrder = holidayMembersOrder;
        this.weekdayCursor = -1;
        this.holidayCursor = -1;
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

    public Nickname assignMember(WorkMonth workMonth, DayOfWeek dayOfWeek, int date) {
        if (dayOfWeek.isHoliday() || workMonth.isHoliday(date)) {
            holidayCursor = holidayMembersOrder.getNextIndex(holidayCursor);
            return holidayMembersOrder.getMembers().get(holidayCursor);
        }
        weekdayCursor = holidayMembersOrder.getNextIndex(weekdayCursor);
        return weekdayMembersOrder.getMembers().get(weekdayCursor);
    }

    public Nickname getSubstitutedMember(WorkMonth workMonth, DayOfWeek dayOfWeek, int date) {
        if (dayOfWeek.isHoliday() || workMonth.isHoliday(date)) {
            return holidayMembersOrder.getMembers().get(holidayMembersOrder.getNextIndex(holidayCursor));
        }
        return weekdayMembersOrder.getMembers().get(weekdayMembersOrder.getNextIndex(weekdayCursor));
    }
}
