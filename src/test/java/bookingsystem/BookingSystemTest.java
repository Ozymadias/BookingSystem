package bookingsystem;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BookingSystemTest {
    BookingSystem bookingSystem;
    private int anyHour = 1;
    private DayOfWeek anyDay = DayOfWeek.MONDAY;
    private int id = 1;

    @BeforeMethod
    private void setUp() {
        bookingSystem = new BookingSystem();
    }

    @Test
    public void newSystemShouldHaveAllItsRoomAvailable() {
        assertEquals(bookingSystem.getAvailableRooms(anyDay, anyHour), bookingSystem.getAllRooms(),
                "All rooms should be available after initialization");
    }

    @Test
    public void afterBookingListOfAvailableRoomsShouldNotContainsBookedRoom() {
        bookingSystem.book(id, anyDay, anyHour);
        Room bookedRoom = new ClassRoom(id, anyInt(), Equipment.PROJECTOR);

        assertThat(bookingSystem.getAvailableRooms(anyDay, anyHour)).excludes(bookedRoom);
    }

    @Test
    public void afterBookingListOfAvailableRoomsShouldContainsNotBookedRoom() {
        Room unbooked = new ClassRoom(2, anyInt(), Equipment.PROJECTOR);

        bookingSystem.book(id, anyDay, anyHour);

        assertThat(bookingSystem.getAvailableRooms(anyDay, anyHour)).contains(unbooked);
    }

    @DataProvider
    private static Object[][] booked() {
        return new Object[][]{
                {DayOfWeek.MONDAY, 1},
                {DayOfWeek.MONDAY, 2},
                {DayOfWeek.MONDAY, 3}
        };
    }

    @Test(dataProvider = "booked")
    public void afterBookingListOfAvailableShouldNotContainBookedRoom(DayOfWeek day, int hour) {
        bookingSystem.book(id, day, hour);
        Room bookedRoom = new ClassRoom(id, anyInt(), Equipment.PROJECTOR);

        assertThat(bookingSystem.getAvailableRooms(day, hour)).excludes(bookedRoom);
    }

    @Test(dataProvider = "booked", expectedExceptions = HourAlreadyBookedException.class)
    public void nonHourCanBeDoubleBooked(DayOfWeek day, int hour) {
        bookingSystem.book(id, day, hour);
        bookingSystem.book(id, day, hour);
    }

    @DataProvider
    private static Object[][] invalidHours() {
        return new Object[][]{
                {DayOfWeek.MONDAY, -1},
                {DayOfWeek.MONDAY, 25},
                {DayOfWeek.MONDAY, 24}
        };
    }

    @Test(dataProvider = "invalidHours", expectedExceptions = IllegalArgumentException.class)
    public void whenMethodIsCalledWithInvalidHourShouldThrowException(DayOfWeek day, int invalid) {
        bookingSystem.book(id, day, invalid);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenMethodIsCalledWithInvalidDayShouldThrowException() {
        bookingSystem.book(id, null, 1);
    }

    @Test
    public void whenRoomWithCapacityAndEquipmentIsBookedItShouldMatchRequirements() {
        Equipment equipment = Equipment.PROJECTOR;

        int minimalCapacity = 17;
        bookingSystem.book(minimalCapacity, equipment, new TimeSlot(anyDay, anyHour));

        List<Room> rooms = bookingSystem.getAllRooms();
        rooms.removeAll(bookingSystem.getAvailableRooms(anyDay, anyHour));

        assertThat(rooms).isNotEmpty();
        assertThat(rooms.size()).isEqualTo(1);
        assertTrue(rooms.get(0).getEquipments() == equipment);
        assertTrue(rooms.get(0).size() >= minimalCapacity);
    }
}
