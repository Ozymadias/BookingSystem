package bookingSystem;

import java.time.DayOfWeek;
import java.util.List;

class BookingSystem {
    private Room classRoom = new ClassRoom();

    List<MyPair<DayOfWeek, Integer>> getBookedHours() {
        return classRoom.getBookedHours();
    }

    void book(DayOfWeek day, int i) {
        if (!(0 <= i && i < 24) || day == null)
            throw new IllegalArgumentException();
        classRoom.book(day, i);
    }
}
