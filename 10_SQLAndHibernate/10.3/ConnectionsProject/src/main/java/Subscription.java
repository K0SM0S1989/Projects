import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "Subscriptions")
public class Subscription {


    @Embeddable
    public static class Id implements Serializable {
        @ManyToOne
        @JoinColumn(name = "course_id")
        private Course course;

        @ManyToOne
        @JoinColumn(name = "student_id")
        private Student student;

//        @Column(name = "student_id")
//        private Integer studentId;
//
//        @Column(name = "course_id")
//        private Integer courseId;


        public Id() {
        }

        public Id(Course course, Student student) {
            this.course = course;
            this.student = student;
        }

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }


    }

    @EmbeddedId
    private Id id = new Id();

    @Column(name = "subscription_date")
    @NotNull
    private Date subscriptionDate;




    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }


}
