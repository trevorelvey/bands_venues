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

  @Override
  public boolean equals(Object otherCourse){
    if (!(otherCourse instanceof Course)) {
      return false;
    } else {
      Course newCourse = (Course) otherCourse;
      return this.getCourseName().equals(newCourse.getCourseName()) &&
             this.getCourseNumber().equals(newCourse.getCourseNumber());
    }
  }

  //CREATE//

  //READ//
  public static List<Course> all() {
    String sql = "SELECT id, course_name, course_number FROM courses";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Course.class);
    }
  }

  //UPDATE//

  //DESTROY//
}
