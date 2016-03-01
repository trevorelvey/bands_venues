import org.junit.*;
import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.ArrayList;
public class StudentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void clear_emptiesAllFirst() {
    assertEquals(Student.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Student firstStudent = new Student("Shifty FiveFingers");
    Student secondStudent = new Student("Shifty FiveFingers");
    assertTrue(firstStudent.equals(secondStudent));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    Student myStudent = new Student("Shifty FiveFingers");
    myStudent.save();
    assertTrue(Student.all().get(0).equals(myStudent));
  }

  @Test
  public void save_assignsIdToObject() {
    Student myStudent = new Student("Shifty FiveFingers");
    myStudent.save();
    Student savedStudent = Student.all().get(0);
    assertEquals(myStudent.getId(), savedStudent.getId());
  }

  @Test
  public void all_savesIntoDatabase_true() {
    Student myStudent = new Student("Shifty FiveFingers");
    myStudent.save();
    assertEquals(Student.all().get(0).getName(), "Shifty FiveFingers");
  }

  @Test
  public void find_findsStudentInDatabase_true() {
    Student myStudent = new Student("Shifty FiveFingers");
    myStudent.save();
    Student savedStudent = Student.find(myStudent.getId());
    assertEquals(savedStudent.getName(), "Shifty FiveFingers");
  }

  @Test
  public void student_instantiatesCorrectly_true() {
    Student myStudent = new Student("Shifty FiveFingers");
    assertEquals(true, myStudent instanceof Student);
  }

  @Test
 public void student_instantiatesWithName_true() {
   Student myStudent = new Student("Shifty FiveFingers");
   assertEquals("Shifty FiveFingers", myStudent.getName());
 }

 @Test
 public void all_returnsAllInstancesOfStudent_true() {
    Student firstStudent = new Student("Shifty FiveFingers");
    Student secondStudent = new Student("The Brawler");
    firstStudent.save();
    secondStudent.save();
    assertTrue(Student.all().contains(firstStudent));
    assertTrue(Student.all().contains(secondStudent));
 }

 @Test
  public void newId_studentInstantiateWithAnID_true() {
    Student myStudent = new Student("Shifty FiveFingers");
    assertEquals(Student.all().size(), myStudent.getId());
  }

  @Test
  public void find_returnsStudentWithSameId_secondStudent() {
    Student firstStudent = new Student("Shifty FiveFingers");
    Student secondStudent = new Student("The Brawler");
    firstStudent.save();
    secondStudent.save();
    assertEquals(Student.find(secondStudent.getId()), secondStudent);
  }

  @Test
  public void find_returnsNullWhenNoStudentFound_null() {
    assertTrue(Student.find(999) == null);
  }

   @Test
   public void delete_deleteDeletesStudent() {
     Student myStudent = new Student("Shifty FiveFingers");
     myStudent.save();
     myStudent.delete();
     assertEquals(Student.all().size(), 0);
   }

  @Test
  public void addCourse_addsCourseForStudent() {
    Course myCourse = new Course("Intro to Brawling", "BRWL101");
    myCourse.save();

    Student myStudent = new Student("Shifty FiveFingers");
    myStudent.save();

    myStudent.addCourse(myCourse);
    Course savedCourse = myStudent.getCourses().get(0);
    assertTrue(myCourse.equals(savedCourse));
  }

  @Test
  public void getCourses_returnsAllCourses_ArrayList() {
    Course myCourse = new Course("Intro to Brawling", "BRWL101");
    myCourse.save();

    Student myStudent = new Student("Shifty FiveFingers");
    myStudent.save();

    myStudent.addCourse(myCourse);
    ArrayList<Course> savedCourse = myStudent.getCourses();
    assertEquals(savedCourse.size(), 1);
  }
}
