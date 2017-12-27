package bookingSystem;

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
}
