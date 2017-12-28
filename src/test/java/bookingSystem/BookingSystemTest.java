package bookingSystem;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
        bookingSystem.book(1);

        assertFalse(bookingSystem.getBookedHours().isEmpty(), "List of booked hours should not be empty after booking");
    }

    @DataProvider
    private static final Object[][] booked() {
        return new Object[][]{
                {1},
                {2},
                {3}
        };
    }

    @Test(dataProvider = "booked")
    public void afterBookingListOfBookedHoursShouldContainBookedHours(int hour) {
        bookingSystem.book(hour);

        assertTrue(bookingSystem.getBookedHours().contains(hour));
    }

    @Test(dataProvider = "booked", expectedExceptions = HourAlreadyBookedException.class)
    public void nonHourCanBeDoubleBooked(int hour) {
        bookingSystem.book(hour);
        bookingSystem.book(hour);
    }

    @DataProvider
    private static final Object[][] invalidInput() {
        return new Object[][]{
                {-1},
                {25},
                {24}
        };
    }

    @Test(dataProvider = "invalidInput", expectedExceptions = IllegalArgumentException.class)
    public void name(int invalid) {
        bookingSystem.book(invalid);
    }
}
