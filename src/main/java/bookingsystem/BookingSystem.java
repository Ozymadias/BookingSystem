package bookingsystem;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BookingSystem {
    private final int endExclusive = 21;
    private Map<Integer, Room> classRoom = new HashMap<>();

    BookingSystem() {
        IntStream.range(1, endExclusive).forEach(id -> classRoom.put(id, new ClassRoom(id)));
    }

    void book(int id, DayOfWeek day, int i) {
        if (!(0 <= i && i < 24) || day == null)
            throw new IllegalArgumentException();
        classRoom.get(id).book(day, i);
    }

    List<Room> getAvailableRooms(DayOfWeek day, int hour) {
        return classRoom.values().stream().filter(room -> room.isAvailable(new TimeSlot(day, hour))).collect(Collectors.toList());
    }

    int numberOfRooms() {
        return classRoom.size();
    }
}
