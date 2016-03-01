import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
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

  // @Override
  // public boolean equals(Course ot){
  //   if (!(otherCategory instanceof Category)) {
  //     return false;
  //   } else {
  //     Category newCategory = (Category) otherCategory;
  //     return this.getName().equals(newCategory.getName());
  //   }
  // }

  //CREATE//

  //READ//

  //UPDATE//

  //DESTROY//

}
