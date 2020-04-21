import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "Subscriptions")
public class Subscription {


    @Embeddable
    private static class Id implements Serializable {

        @Column(name = "student_id")
        private Integer studentId;

        @Column(name = "course_id")
        private Integer courseId;

        Id() {
        }

        public Id(Integer studentId, Integer courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        public boolean equals(Object o) {
            if (o != null && o instanceof Id) {
                Id that = (Id) o;
                return this.studentId.equals(that.studentId)
                        && this.courseId.equals(that.courseId);
            }
            return false;
        }

        public int hashCode() {
            return studentId.hashCode() + courseId.hashCode();

        }
    }

    @EmbeddedId
    private Id id = new Id();

    @Column(name = "subscription_date")
    @NotNull
    private Date subscriptionDate;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;


    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
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
