package oncall.model;

import java.util.HashSet;
import java.util.Set;
import oncall.constant.ExceptionMessage;

public class MembersSchedule {
    private final MembersOrder weekdayMembersOrder;
    private final MembersOrder holidayMembersOrder;
    private int weekdayCursor;
    private int holidayCursor;
    private boolean isWeekdaySubstituted;
    private boolean isHolidaySubstituted;

    private MembersSchedule(MembersOrder weekdayMembersOrder, MembersOrder holidayMembersOrder) {
        this.weekdayMembersOrder = weekdayMembersOrder;
        this.holidayMembersOrder = holidayMembersOrder;
        this.weekdayCursor = 0;
        this.holidayCursor = 0;
        this.isWeekdaySubstituted = false;
        this.isHolidaySubstituted = false;
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
            Nickname member = holidayMembersOrder.getMembers().get(holidayCursor);
            holidayCursor = holidayMembersOrder.getNextIndex(holidayCursor);
            if (isHolidaySubstituted) {
                holidayCursor = holidayMembersOrder.getNextIndex(holidayCursor);
                isHolidaySubstituted = false;
            }
            return member;
        }
        Nickname member = weekdayMembersOrder.getMembers().get(weekdayCursor);
        weekdayCursor = weekdayMembersOrder.getNextIndex(weekdayCursor);
        if (isWeekdaySubstituted) {
            weekdayCursor = weekdayMembersOrder.getNextIndex(weekdayCursor);
            isWeekdaySubstituted = false;
        }
        return member;
    }

    public Nickname getSubstitutedMember(WorkMonth workMonth, DayOfWeek dayOfWeek, int date) {
        if (dayOfWeek.isHoliday() || workMonth.isHoliday(date)) {
            Nickname member = holidayMembersOrder.getMembers().get(holidayCursor);
            isHolidaySubstituted = true;
            holidayCursor = holidayMembersOrder.getPreviousIndex(holidayCursor);
            return member;
        }
        Nickname member = weekdayMembersOrder.getMembers().get(weekdayCursor);
        isWeekdaySubstituted = true;
        weekdayCursor = weekdayMembersOrder.getPreviousIndex(weekdayCursor);
        return member;
    }
}
