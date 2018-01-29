package bookingsystem;

import java.time.DayOfWeek;

class TimeSlot {
    private final DayOfWeek day;
    private final Integer hour;

    TimeSlot(DayOfWeek day, Integer hour) {
        this.day = day;
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        if (day != null ? !day.equals(timeSlot.day) : timeSlot.day != null) return false;
        return hour != null ? hour.equals(timeSlot.hour) : timeSlot.hour == null;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (hour != null ? hour.hashCode() : 0);
        return result;
    }
}
