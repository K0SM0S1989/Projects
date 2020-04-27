import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.management.Query;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()
        ) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();


            Teacher teacher = session.get(Teacher.class, 10);
            List<CoursesTeachers> CTFromTeacherClass = teacher.getCoursesTeachersList();
            Course course = session.get(Course.class, 10);
            List<CoursesTeachers> CTFromCourseClass = course.getCoursesTeachersList();
            CTFromCourseClass.forEach(s -> System.out.println(s.getId().getCourse().getName() + " - " + s.getId().getTeacher().getName()));

            System.out.println("--------------------------");

            CTFromTeacherClass.forEach(s-> System.out.println(s.getId().getCourse().getName()+" - "+s.getId().getTeacher().getName()));

            transaction.commit();

        }

    }
}
