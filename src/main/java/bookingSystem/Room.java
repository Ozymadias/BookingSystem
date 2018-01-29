package bookingSystem;

import java.time.DayOfWeek;
import java.util.List;

interface Room {
    void book(DayOfWeek day, int i);

    List<MyPair<DayOfWeek,Integer>> getBookedHours();
}
