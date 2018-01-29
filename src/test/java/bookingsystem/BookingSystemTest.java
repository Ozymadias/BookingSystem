package bookingsystem;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.DayOfWeek;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BookingSystemTest {
    BookingSystem bookingSystem;
    private int anyHour = 1;
    private DayOfWeek anyDay = DayOfWeek.MONDAY;

    @BeforeMethod
    private void setUp() {
        bookingSystem = new BookingSystem();
    }

    @Test
    public void newSystemShouldReturnEmptyListOfBookedHours() {
        assertEquals(bookingSystem.getAvailableRooms(anyDay, anyHour).size(), 1,
                "All rooms should be available after initialization");
    }

    @Test
    public void afterBookingListOfBookedHoursShouldBeNonEmpty() {
        bookingSystem.book(anyDay, anyHour);

        assertEquals(bookingSystem.getAvailableRooms(anyDay, anyHour).size(), 0,
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
    public void afterBookingListOfBookedHoursShouldContainBookedHours(DayOfWeek day, int hour) {
        bookingSystem.book(day, hour);

        assertTrue(bookingSystem.getAvailableRooms(day, hour).isEmpty());
    }

    @Test(dataProvider = "booked", expectedExceptions = HourAlreadyBookedException.class)
    public void nonHourCanBeDoubleBooked(DayOfWeek day, int hour) {
        bookingSystem.book(day, hour);
        bookingSystem.book(day, hour);
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
        bookingSystem.book(day, invalid);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenMethodIsCalledWithInvalidDayShouldThrowException() {
        bookingSystem.book(null, 1);
    }
}
