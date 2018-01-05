package bookingSystem;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

class BookingSystem {
    private List<MyPair<DayOfWeek, Integer>> bookedHours = new ArrayList<>();

    List<MyPair<DayOfWeek, Integer>> getBookedHours() {
        return bookedHours;
    }

    void book(DayOfWeek day, int i) {
        if (!(0 <= i && i < 24))
            throw new IllegalArgumentException();
        MyPair<DayOfWeek, Integer> pair = new MyPair<>(day, i);
        if (bookedHours.contains(pair))
            throw new HourAlreadyBookedException();
        bookedHours.add(pair);
    }
}
