import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int salary;

    private int age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teachers")
    private Set<Course> courses = new HashSet<>();

    public Set<Course> getCourses() {
        return courses;
    }


    public void addTeacher(Course course) {
        courses.add(course);
        course.getTeachers().add(this);
    }

    public void removeTag(Course course) {
        courses.remove(course);
        course.getTeachers().remove(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return salary == teacher.salary &&
                age == teacher.age &&
                Objects.equals(name, teacher.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, salary, age);
    }
}
