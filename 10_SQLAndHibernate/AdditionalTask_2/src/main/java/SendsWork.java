import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Sends_work")
public class SendsWork extends Notification {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentTrigger;

    public Student getStudentTrigger() {
        return studentTrigger;
    }

    public void setStudentTrigger(Student studentTrigger) {
        this.studentTrigger = studentTrigger;
    }
}
