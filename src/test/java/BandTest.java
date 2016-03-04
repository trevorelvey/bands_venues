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
}
