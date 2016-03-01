import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Student {
  private int id;
  private String name;
  private LocalDate enrollment_date;

  //CONSTRUCTOR//
  public Student(String name, LocalDate enrollment_date) {
    this.name = name;
    this.enrollment_date = enrollment_date;
  }

  //GETTER METHODS//
  public String getName() {
    return name;
  }

  public LocalDate getEnrollmentDate() {
    return enrollment_date;
  }

  //CREATE//

  //READ//

  //UPDATE//

  //DESTROY//

}
