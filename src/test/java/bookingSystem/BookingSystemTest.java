package bookingSystem;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.DayOfWeek;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class BookingSystemTest {
    BookingSystem bookingSystem;

    @BeforeMethod
    private void setUp() {
        bookingSystem = new BookingSystem();
    }

    @Test
    public void newSystemShouldReturnEmptyListOfBookedHours() {
        assertTrue(bookingSystem.getBookedHours().isEmpty(), "List of booked hours should be empty after initialization");
    }

    @Test
    public void afterBookingListOfBookedHoursShouldBeNonEmpty() {
        bookingSystem.book(DayOfWeek.MONDAY, 1);

        assertFalse(bookingSystem.getBookedHours().isEmpty(), "List of booked hours should not be empty after booking");
    }

    @DataProvider
    private static final Object[][] booked() {
        return new Object[][]{
                {DayOfWeek.MONDAY, 1},
                {DayOfWeek.MONDAY, 2},
                {DayOfWeek.MONDAY, 3}
        };
    }

    @Test(dataProvider = "booked")
    public void afterBookingListOfBookedHoursShouldContainBookedHours(DayOfWeek day, int hour) {
        bookingSystem.book(day, hour);

        assertTrue(bookingSystem.getBookedHours().contains(new MyPair<>(day, hour)));
    }

    @Test(dataProvider = "booked", expectedExceptions = HourAlreadyBookedException.class)
    public void nonHourCanBeDoubleBooked(DayOfWeek day, int hour) {
        bookingSystem.book(day, hour);
        bookingSystem.book(day, hour);
    }

    @DataProvider
    private static final Object[][] invalidHours() {
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
}
