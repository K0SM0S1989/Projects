import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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



    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;

    private Integer price;

    @Column(name = "price_per_hour")
    private float pricePerHour;



    @OneToMany(fetch = FetchType.LAZY, targetEntity = CoursesTeachers.class,mappedBy = "id.course")
    private List<CoursesTeachers> coursesTeachersList = new ArrayList<>();

    public List<CoursesTeachers> getCoursesTeachersList() {
        return coursesTeachersList;
    }

    public void addcoursesTeachers(CoursesTeachers coursesTeachers) {
        coursesTeachersList.add(coursesTeachers);
        coursesTeachers.getId().setCourse(this);
    }

    public void removecoursesTeachersList(CoursesTeachers coursesTeachers) {
        coursesTeachersList.remove(coursesTeachers);
        coursesTeachers.getId().setCourse(null);
    }



    @OneToMany(fetch = FetchType.LAZY,targetEntity = Subscription.class, mappedBy = "id.course")
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




}
