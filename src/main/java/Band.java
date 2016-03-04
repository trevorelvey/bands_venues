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

  //UPDATE//


  //DESTROY//

}
