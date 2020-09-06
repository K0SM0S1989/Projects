import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Testcontainers
class HomeWorkTest {
    List<Student> expected = new ArrayList<>();

    private HomeWork homeWork;
    private final String filePath = "src/test/resources/stud.csv";


    @Container
    public GenericContainer mongoDBContainer = new GenericContainer("mongo:4.0.10")
            .withExposedPorts(27017);


    @BeforeEach
    void setup() {
        homeWork = new HomeWork(filePath);
        homeWork.init();
        homeWork.addDataToMongoDB();
    }


    @Test
    void parseStudentMongoCsv() {
        List<Student> studentListActual = homeWork.parseStudentMongoCsv(filePath);

        List<String> courses1 = new ArrayList<>();
        List<String> courses2 = new ArrayList<>();
        List<String> courses3 = new ArrayList<>();
        courses1.add("Web");
        courses2.add("Web");
        courses3.add("Web");
        courses1.add("Python");
        courses3.add("Python");
        courses1.add("iOS");

        courses2.add("Android");

        expected.add(new Student("Noor Mckinney", 21, courses1));
        expected.add(new Student("Joshua Dudley", 20, courses1));
        expected.add(new Student("Elaine Welsh", 42, courses2));
        expected.add(new Student("Aman Ryder", 43, courses3));

        assertEquals(expected.get(2).getName(), studentListActual.get(2).getName());


    }

    @Test
    void addDataToMongoDBTest() {
        homeWork.removeAll();
        long actualCount =  homeWork.addDataToMongoDB();
        long expected = 4;
        assertEquals(expected, actualCount);
    }


    @Test
    void studentsOld40YearsCountTest() {
        long actual = homeWork.studentsOld40YearsCount();
        long expected = 2;
        assertEquals(expected, actual);

    }

    @Test
    void youngStudentNameTest() {
        String actual = homeWork.youngStudentName();
        String expected = "Joshua Dudley";
        assertEquals(expected, actual);
    }

    @Test
    void oldStudentCoursesTest() {
        List<String> actual = homeWork.oldStudentCourses();
        List<String> courses = new ArrayList<>();
        courses.add("Web");
        courses.add("Python");
        boolean expected = false;
        for (int i = 0; i < actual.size(); i++) {
            if (!actual.get(i).equals(courses.get(i))) {
                expected = false;
                break;
            } else expected = true;
        }
        assertTrue(expected);
    }
}
