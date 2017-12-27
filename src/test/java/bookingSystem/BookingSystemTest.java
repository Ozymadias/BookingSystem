package bookingSystem;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BookingSystemTest {

    @Test
    public void newSystemShouldReturnEmptyListOfBookedHours() {
        BookingSystem bookingSystem = new BookingSystem();

        assertTrue(bookingSystem.getBookedHours().isEmpty());
    }
}
