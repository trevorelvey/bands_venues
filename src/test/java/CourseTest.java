import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class CourseTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
   assertEquals(Course.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfCoursesAretheSame() {
    Course firstCourse = new Course("Intro to Brawling", "BRWL101");
    Course secondCourse = new Course("Intro to Brawling", "BRWL101");
    assertTrue(firstCourse.equals(secondCourse));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Course myCourse = new Course("Intro to Brawling", "BRWL101");
    myCourse.save();
    assertTrue(Course.all().get(0).equals(myCourse));
  }

  @Test
  public void find_findCourseInDatabase_true() {
    Course myCourse = new Course("Intro to Brawling", "BRWL101");
    myCourse.save();
    Course savedCourse = Course.find(myCourse.getId());
    assertTrue(myCourse.equals(savedCourse));
  }

  @Test
  public void all_returnsAllInstancesOfCourse_true() {
    Course firstCourse = new Course("Intro to Brawling", "BRWL101");
    Course secondCourse = new Course("Intro to Pickpocketing", "PCKP101");
    firstCourse.save();
    secondCourse.save();
    assertTrue(Course.all().contains(firstCourse));
    assertTrue(Course.all().contains(secondCourse));
  }

  @Test
  public void addStudent_addsStudentToCourse() {
    Course myCourse = new Course("Intro to Brawling", "BRWL101");
    myCourse.save();

    Student myStudent = new Student("Shifty FiveFingers");
    myStudent.save();

    myCourse.addStudent(myStudent);
    Student savedStudent = myCourse.getStudents().get(0);
    assertTrue(myStudent.equals(savedStudent));
  }

  @Test
  public void delete_deletesAllCoursesAndListsAssociations() {
    Course myCourse = new Course("Intro to Brawling", "BRWL101");
    myCourse.save();

    Student myStudent = new Student("Shifty FiveFingers");
    myStudent.save();

    myCourse.addStudent(myStudent);
    myCourse.delete();
    assertEquals(myStudent.getCourses().size(), 0);
  }
}
