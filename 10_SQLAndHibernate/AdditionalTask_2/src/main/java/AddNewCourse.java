import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Add_new_course")
public class AddNewCourse extends Notification {

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courseTrigger;

    public Course getCourseTrigger() {
        return courseTrigger;
    }

    public void setCourseTrigger(Course courseTrigger) {
        this.courseTrigger = courseTrigger;
    }
}
