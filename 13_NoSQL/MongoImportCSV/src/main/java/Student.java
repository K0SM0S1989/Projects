import java.util.List;

/**
 * класс описывающий студента полями имя, возраст и список курсов
 * с соответствующими геттерами и конструкторами
 */
public class Student {
    private String name;
    private int age;
    private List<String> courses;


    public Student(String name, int age, List<String> courses) {
        this.name = name;
        this.age = age;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public List<String> getCourses() {
        return courses;
    }


}
