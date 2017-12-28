package bookingSystem;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private List<Integer> bookedHours = new ArrayList<>();

    public List<Integer> getBookedHours() {
        return bookedHours;
    }

    public void book(int i) {
        if (bookedHours.contains(i))
            throw new IllegalArgumentException();
        bookedHours.add(i);
    }
}
