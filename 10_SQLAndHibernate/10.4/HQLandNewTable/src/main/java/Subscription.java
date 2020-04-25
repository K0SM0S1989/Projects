import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "Subscriptions")
public class Subscription {


    @Embeddable
    public static class Id implements Serializable {
        @ManyToOne
        @JoinColumn(name = "course_id", insertable = false, updatable = false)
        private Course course;

        @ManyToOne
        @JoinColumn(name = "student_id", insertable = false, updatable = false)
        private Student student;


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

        public Id() {
        }

        public Id(Course course, Student student) {
            this.course = course;
            this.student = student;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(course, id.course) &&
                    Objects.equals(student, id.student);
        }

        @Override
        public int hashCode() {

            return Objects.hash(course, student);
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
