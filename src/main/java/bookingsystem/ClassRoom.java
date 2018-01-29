package bookingsystem;

import java.time.DayOfWeek;
import java.util.List;

class ClassRoom implements Room {
    private List<TimeSlot> availableHours = ScheduleGenerator.generateWeek();
    private int id;

    ClassRoom(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassRoom classRoom = (ClassRoom) o;

        return id == classRoom.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
