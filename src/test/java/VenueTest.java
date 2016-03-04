import org.junit.*;
import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.ArrayList;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Venue firstVenue = new Venue("Wonder Ballroom");
    Venue secondVenue = new Venue("Wonder Ballroom");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Venue myVenue = new Venue("Wonder Ballroom");
    myVenue.save();
    assertTrue(Venue.all().get(0).equals(myVenue));
  }
}
