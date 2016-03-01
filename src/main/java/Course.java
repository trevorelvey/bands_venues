import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Course {
  private int id;
  private String course_name;
  private String course_number;


  //CONSTRUCTOR//
  public Course(String course_name, String course_number) {
    this.course_name = course_name;
    this.course_number = course_number;
  }

  //GETTERS//
  public String getCourseName() {
    return course_name;
  }

  public String getCourseNumber() {
    return course_number;
  }

  //CREATE//

  //READ//

  //UPDATE//

  //DESTROY//
}
