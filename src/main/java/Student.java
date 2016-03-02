import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import org.sql2o.*;

public class Student {
  private int id;
  private String name;
  private String enrollment_date;

  //CONSTRUCTOR//
  public Student(String name) {
    this.name = name;
    enrollment_date = LocalDateTime.now().toString();
  }

  //GETTER METHODS//
  public String getName() {
    return name;
  }

  public String getEnrollmentDate() {
    return enrollment_date;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherStudent){
    if (!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent = (Student) otherStudent;
      return this.getName().equals(newStudent.getName());
    }
  }

  //CREATE//
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO students(name, enrollment_date) VALUES (:name, :enrollment_date)";
    this.id = (int) con.createQuery(sql,true)
    .addParameter("name", this.name)
    .addParameter("enrollment_date", this.enrollment_date)
    .executeUpdate()
    .getKey();
    }
  }

  //READ//

  public static List<Student> all() {
    String sql = "SELECT id, name, enrollment_date FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Student.class);
    }
  }

  public static Student find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Students where id=:id;";
      Student student = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Student.class);
      return student;
    }
  }

  public List<Course> getCourses() {
    String sql = "SELECT courses.* FROM students JOIN enrollments ON (students.id = enrollments.student_id) JOIN courses ON (enrollments.course_id = courses.id) WHERE students.id = :student_id";
    try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql)
          .addParameter("student_id", id)
          .executeAndFetch(Course.class);
    }
  }

  //UPDATE//
  public void addCourse(Course course) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO enrollments (course_id, student_id) VALUES (:course_id, :student_id)";
      con.createQuery(sql)
        .addParameter("course_id", course.getId())
        .addParameter("student_id", this.getId())
        .executeUpdate();
    }
  }

  //DESTROY//
  public void delete() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM students WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();

    String enrollmentsQuery = "DELETE FROM enrollments WHERE student_id = :studentId";
    con.createQuery(enrollmentsQuery)
      .addParameter("studentId", this.getId())
      .executeUpdate();
    }
  }
}
