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

  public int getId() {
    return id;
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
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO courses(course_name, course_number) VALUES (:course_name, :course_number)";
    this.id = (int) con.createQuery(sql,true)
    .addParameter("course_name", this.course_name)
    .addParameter("course_number", this.course_number)
    .executeUpdate()
    .getKey();
    }
  }

  //READ//
  public static List<Course> all() {
    String sql = "SELECT id, course_name, course_number FROM courses";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(Course.class);
    }
  }

  public static Course find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Courses where id=:id";
      Course course = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Course.class);
      return course;
    }
  }

  public ArrayList<Student> getStudents() {
  try(Connection con = DB.sql2o.open()){
    String sql = "SELECT student_id FROM enrollments WHERE course_id = :course_id";
    List<Integer> studentIds = con.createQuery(sql)
      .addParameter("course_id", this.getId())
      .executeAndFetch(Integer.class);

    ArrayList<Student> students = new ArrayList<Student>();

    for (Integer studentId : studentIds) {
      String studentQuery = "SELECT * FROM students WHERE id = :studentId";
      Student student = con.createQuery(studentQuery)
        .addParameter("studentId", studentId)
        .executeAndFetchFirst(Student.class);
        students.add(student);
    }
    return students;
  }
}

  //UPDATE//
  public void addStudent(Student student) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO enrollments (course_id, student_id) VALUES (:course_id, :student_id);";
        con.createQuery(sql)

          .addParameter("course_id", this.getId())
          .addParameter("student_id", student.getId())
          .executeUpdate();
      }
    }

  //DESTROY//
  public void delete() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM courses WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();

    String enrollmentsQuery = "DELETE FROM enrollments WHERE course_id = :courseId";
    con.createQuery(enrollmentsQuery)
      .addParameter("courseId", this.getId())
      .executeUpdate();
    }
  }
}
