import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Write_comment")
public class WriteComment extends Notification{

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
