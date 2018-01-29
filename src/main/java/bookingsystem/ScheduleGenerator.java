package bookingsystem;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

class ScheduleGenerator {
    static List<TimeSlot> generateWeek() {
        List<TimeSlot> weekSchedule = new ArrayList<>();
        for (DayOfWeek day : DayOfWeek.values())
            for (int hour = 0; hour < 24; hour++)
                weekSchedule.add(new TimeSlot(day, hour));
        return weekSchedule;
    }
}
