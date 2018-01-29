package bookingSystem;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

class ClassRoom implements Room {
    private List<MyPair<DayOfWeek, Integer>> bookedHours = new ArrayList<>();

    @Override
    public List<MyPair<DayOfWeek, Integer>> getBookedHours() {
        return bookedHours;
    }

    @Override
    public void book(DayOfWeek day, int i) {
        MyPair<DayOfWeek, Integer> pair = new MyPair<>(day, i);
        if (bookedHours.contains(pair))
            throw new HourAlreadyBookedException();
        bookedHours.add(pair);
    }
}
