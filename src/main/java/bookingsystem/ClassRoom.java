package bookingsystem;

import java.time.DayOfWeek;
import java.util.List;

class ClassRoom implements Room {
    private List<TimeSlot> availableHours = ScheduleGenerator.generateWeek();

    @Override
    public boolean isAvailable(TimeSlot timeSlot) {
        return availableHours.contains(timeSlot);
    }

    @Override
    public void book(DayOfWeek day, int i) {
        TimeSlot pair = new TimeSlot(day, i);
        if (!availableHours.contains(pair))
            throw new HourAlreadyBookedException();
        availableHours.remove(pair);
    }
}
