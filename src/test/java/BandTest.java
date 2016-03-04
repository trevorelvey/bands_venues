import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
    public void all_emptyAtFirst() {
      assertEquals(Band.all().size(), 0);
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame() {
      Band firstBand = new Band("Mogwai");
      Band secondBand = new Band("Mogwai");
      assertTrue(firstBand.equals(secondBand));
    }

    @Test
    public void save_savesIntoDatabase_true() {
      Band myBand = new Band("Mogwai");
      myBand.save();
      assertTrue(Band.all().get(0).equals(myBand));
    }

    @Test
    public void find_findBandInDatabase_true() {
      Band myBand = new Band("Mogwai");
      myBand.save();
      Band savedBand = Band.find(myBand.getId());
      assertTrue(myBand.equals(savedBand));
    }

    @Test
    public void all_returnsAllInstancesOfBand_true() {
      Band firstBand = new Band("Mogwai");
      Band secondBand = new Band("Diiv");
      firstBand.save();
      secondBand.save();
      assertTrue(Band.all().contains(firstBand));
      assertTrue(Band.all().contains(secondBand));
    }

    @Test
  public void addVenue_addsVenueToBand() {
    Band myBand = new Band("Mogwai");
    myBand.save();

    Venue myVenue = new Venue("Wonder Ballroom");
    myVenue.save();

    myBand.addVenue(myVenue);
    Venue savedVenue = myBand.getVenues().get(0);
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void delete_deletesAllBandsAndListsAssociations() {
    Band myBand = new Band("Mogwai");
    myBand.save();

    Venue myVenue = new Venue("Wonder Ballroom");
    myVenue.save();

    myBand.addVenue(myVenue);
    myBand.delete();
    assertEquals(myVenue.getBands().size(), 0);
  }
}
