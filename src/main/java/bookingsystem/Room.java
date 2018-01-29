package bookingsystem;

import java.time.DayOfWeek;

interface Room {
    boolean isAvailable(TimeSlot timeSlot);

    void book(DayOfWeek day, int i);

    Equipment getEquipments();

    int size();

    void book(TimeSlot timeSlot);
}
