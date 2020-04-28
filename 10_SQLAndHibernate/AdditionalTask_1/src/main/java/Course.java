import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int duration;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('DESIGN','PROGRAMMING','MARKETING','MANAGEMENT','BUSINESS')")
    private CourseType type;

    private String description;


    @Column(name = "students_count")
    private Integer studentsCount;

    private Integer price;

    @Column(name = "price_per_hour")
    private float pricePerHour;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Courses_teachers", joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    @OrderColumn
    private Set<Teacher> teachers = new HashSet<>();

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.getCourses().add(this);
    }

    public void removeTag(Teacher teacher) {
        teachers.remove(teacher);
        teacher.getCourses().remove(this);
    }


    @OneToMany(fetch = FetchType.LAZY, targetEntity = Subscription.class, mappedBy = "id.course")
    private List<Subscription> subscriptions = new ArrayList<>();

    public void addSubscriptions(Subscription subscription) {
        subscriptions.add(subscription);
        subscription.getId().setCourse(this);
    }

    public void removeSubscription(Subscription subscription) {
        subscriptions.remove(subscription);
        subscription.getId().setCourse(null);
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }


    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return duration == course.duration &&
                Float.compare(course.pricePerHour, pricePerHour) == 0 &&
                Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                type == course.type &&
                Objects.equals(description, course.description) &&
                Objects.equals(studentsCount, course.studentsCount) &&
                Objects.equals(price, course.price) &&
                Objects.equals(subscriptions, course.subscriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, type, description, studentsCount, price, pricePerHour, subscriptions);
    }
}
