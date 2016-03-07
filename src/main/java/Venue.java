import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {
  private int id;
  private String venue_name;

  //CONSTRUCTOR//
  public Venue(String venue_name) {
    this.venue_name = venue_name;
  }

  //GETTERS//
  public String getVenueName() {
    return venue_name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherVenue){
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getVenueName().equals(newVenue.getVenueName());
    }
  }

  //CREATE//
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO venues(venue_name) VALUES (:venue_name)";
      this.id = (int) con.createQuery(sql,true)
      .addParameter("venue_name", this.venue_name)
      .executeUpdate()
      .getKey();
    }
  }

  //READ//
  public static List<Venue> all() {
    String sql = "SELECT id, venue_name FROM venues";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Venue.class);
    }
  }

  public static Venue find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Venues where id=:id";
      Venue venue = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Venue.class);
      return venue;
    }
  }

  public List<Band> getBands() {
    String sql = "SELECT bands.* FROM venues JOIN bands_venues ON (venues.id = bands_venues.venue_id) JOIN bands ON (bands_venues.band_id = bands.id) WHERE venues.id = :venue_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("venue_id", id)
      .executeAndFetch(Band.class);
    }
  }

  //UPDATE//
  public void update(String newVenueName) {
    this.venue_name = newVenueName;
    String sql = "UPDATE venues SET venue_name = :newVenueName WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("newVenueName", newVenueName)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public void addBand(Band band) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
      .addParameter("band_id", band.getId())
      .addParameter("venue_id", this.getId())
      .executeUpdate();
    }
  }

  public void assignBand(int band_id) {
    band_id = band_id;
    String sql = "UPDATE bands_venues SET band_id = :band_id WHERE venue_id=:venue_id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
         .addParameter("band_id", band_id)
         .addParameter("id", this.id)
         .executeUpdate();
    }
  }

  //DESTROY//
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM venues WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();

      String bands_venuesQuery = "DELETE FROM bands_venues WHERE venue_id = :venueId";
      con.createQuery(bands_venuesQuery)
      .addParameter("venueId", this.getId())
      .executeUpdate();
    }
  }
}
