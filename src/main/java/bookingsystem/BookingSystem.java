package bookingsystem;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

class BookingSystem {
    private Room classRoom = new ClassRoom();

    void book(DayOfWeek day, int i) {
        if (!(0 <= i && i < 24) || day == null)
            throw new IllegalArgumentException();
        classRoom.book(day, i);
    }

    List<Room> getAvailableRooms(DayOfWeek day, int hour) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        if (classRoom.isAvailable(new TimeSlot(day, hour)))
            availableRooms.add(classRoom);
        return availableRooms;
    }
}
