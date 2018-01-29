package bookingsystem;

import java.time.DayOfWeek;
import java.util.List;

class ClassRoom implements Room {
    private List<TimeSlot> availableHours = ScheduleGenerator.generateWeek();
    private int id;
    private int size;
    private Equipment equipment;

    ClassRoom(int id, int size) {
        this.id = id;
        this.size = size;
    }

    ClassRoom(int id, int size, Equipment equipment) {
        this(id, size);
        this.equipment = equipment;
    }

    @Override
    public boolean isAvailable(TimeSlot timeSlot) {
        return availableHours.contains(timeSlot);
    }

    @Override
    public void book(DayOfWeek day, int i) {
        book(new TimeSlot(day, i));
    }

    @Override
    public Equipment getEquipments() {
        return equipment;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void book(TimeSlot timeSlot) {
        if (!availableHours.contains(timeSlot))
            throw new HourAlreadyBookedException();
        availableHours.remove(timeSlot);
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
