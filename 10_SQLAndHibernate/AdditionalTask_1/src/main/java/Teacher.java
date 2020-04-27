import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int salary;

    private int age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.teacher")
    private List<CoursesTeachers> coursesTeachersList;

    public List<CoursesTeachers> getCoursesTeachersList() {
        return coursesTeachersList;
    }

    public void addcoursesTeachers(CoursesTeachers coursesTeachers) {
        coursesTeachersList.add(coursesTeachers);
        coursesTeachers.getId().setTeacher(this);
    }

    public void removecoursesTeachersList(CoursesTeachers coursesTeachers) {
        coursesTeachersList.remove(coursesTeachers);
        coursesTeachers.getId().setTeacher(null);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public List<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(List<Course> courses) {
//        this.courses = courses;
//    }


}
