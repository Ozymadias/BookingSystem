package bookingsystem;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.DayOfWeek;

import static org.testng.Assert.*;


public class BookingSystemTest {
    BookingSystem bookingSystem;
    private int anyHour = 1;
    private DayOfWeek anyDay = DayOfWeek.MONDAY;
    private int id = 1;
    private int numberOfAllRooms;

    @BeforeMethod
    private void setUp() {
        bookingSystem = new BookingSystem();
        numberOfAllRooms = bookingSystem.numberOfRooms();
    }

    @Test
    public void newSystemShouldHaveAllItsRoomAvailable() {
        assertEquals(bookingSystem.getAvailableRooms(anyDay, anyHour).size(), numberOfAllRooms,
                "All rooms should be available after initialization");
    }

    @Test
    public void afterBookingListOfAvailableRoomsShouldNotBeFull() {
        bookingSystem.book(id, anyDay, anyHour);

        assertNotEquals(bookingSystem.getAvailableRooms(anyDay, anyHour).size(), numberOfAllRooms,
                "List of available rooms should not contain room after booking it");
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

        assertEquals(bookingSystem.getAvailableRooms(day, hour).size(), numberOfAllRooms - 1);
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
}
