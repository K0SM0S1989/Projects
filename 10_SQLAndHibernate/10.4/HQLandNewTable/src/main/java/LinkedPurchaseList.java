import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Linkedpurchaselist")
public class LinkedPurchaseList {


    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "course_id")
        private Integer courseId;


        @Column(name = "student_id")
        private Integer studentId;

        public Id() {
        }

        public Id(Integer courseId, Integer studentId) {
            this.courseId = courseId;
            this.studentId = studentId;
        }

        public Integer getCourseId() {
            return courseId;
        }

        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        public Integer getStudentId() {
            return studentId;
        }

        public void setStudentId(Integer studentId) {
            this.studentId = studentId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(courseId, id.courseId) &&
                    Objects.equals(studentId, id.studentId);
        }

        @Override
        public int hashCode() {

            return Objects.hash(courseId, studentId);
        }
    }

    @EmbeddedId
    private Id id = new Id();

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public LinkedPurchaseList(Id id) {
        this.id = id;
    }

    public LinkedPurchaseList() {
    }
}
