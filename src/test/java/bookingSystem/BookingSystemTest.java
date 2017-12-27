package bookingSystem;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BookingSystemTest {

    @Test
    public void newSystemShouldReturnEmptyListOfBookedHours() {
        BookingSystem bookingSystem = new BookingSystem();

        assertTrue(bookingSystem.getBookedHours().isEmpty(), "List of booked hours should be empty after initialization");
    }

    @Test
    public void afterBookingListOfBookedHoursShouldBeNonEmpty() {
        BookingSystem bookingSystem = new BookingSystem();

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
        BookingSystem bookingSystem = new BookingSystem();
        
        bookingSystem.book(hour);

        assertTrue(bookingSystem.getBookedHours().contains(hour));
    }
}
