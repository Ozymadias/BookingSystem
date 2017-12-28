package bookingSystem;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private List<Integer> bookedHours = new ArrayList<>();

    public List<Integer> getBookedHours() {
        return bookedHours;
    }

    public void book(int i) {
        if (!(0 <= i && i < 24))
            throw new IllegalArgumentException();
        if (bookedHours.contains(i))
            throw new HourAlreadyBookedException();
        bookedHours.add(i);
    }
}
