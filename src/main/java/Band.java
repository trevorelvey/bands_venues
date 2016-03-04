import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Band {
  private int id;
  private String band_name;

  //CONSTRUCTOR//
  public Band(String band_name) {
    this.band_name = band_name;
  }

  //GETTERS//
  public String getBandName() {
    return band_name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherBand){
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getBandName().equals(newBand.getBandName());
    }
  }

  //CREATE//
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO bands(band_name) VALUES (:band_name)";
    this.id = (int) con.createQuery(sql,true)
    .addParameter("band_name", this.band_name)
    .executeUpdate()
    .getKey();
    }
  }

  //READ//
  public static List<Band> all() {
    String sql = "SELECT id, band_name FROM bands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Band.class);
    }
  }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Bands where id=:id";
      Band band = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Band.class);
      return band;
    }
  }

  public List<Venue> getVenues() {
    String sql = "SELECT venues.* FROM bands JOIN bands_venues ON (bands.id = bands_venues.band_id) JOIN venues ON (bands_venues.venue_id = venues.id) WHERE bands.id = :band_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("band_id", id)
      .executeAndFetch(Venue.class);
    }
  }

  //UPDATE//
  public void addVenue(Venue venue) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id);";
        con.createQuery(sql)

          .addParameter("band_id", this.getId())
          .addParameter("venue_id", venue.getId())
          .executeUpdate();
      }
    }

  //DESTROY//
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM bands WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();

      String bands_venuesQuery = "DELETE FROM bands_venues WHERE band_id = :bandId";
      con.createQuery(bands_venuesQuery)
      .addParameter("bandId", this.getId())
      .executeUpdate();
    }
  }
}
