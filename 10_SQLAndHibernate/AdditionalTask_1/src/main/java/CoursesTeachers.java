import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Courses_teachers")
public class CoursesTeachers {

    @Embeddable
    public static class Id implements Serializable {

        @ManyToOne
        @JoinColumn(name = "course_id", insertable = false, updatable = false)
        private Course course;

        @ManyToOne
        @JoinColumn(name = "teacher_id", updatable = false, insertable = false)
        private Teacher teacher;

        public Id() {
        }

        public Id(Course course, Teacher teacher) {
            this.course = course;
            this.teacher = teacher;
        }

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(course, id.course) &&
                    Objects.equals(teacher, id.teacher);
        }

        @Override
        public int hashCode() {

            return Objects.hash(course, teacher);
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
}
