import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.management.Query;
import java.util.List;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()
        ) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();


            Teacher teacher = session.get(Teacher.class, 10);
            Set<Course> coursesList = teacher.getCourses();
            Course course = session.get(Course.class, 10);
            Set<Teacher> teachersList = course.getTeachers();


            teachersList.forEach(s -> System.out.println(s.getName() + " - " + course.getName()));

            System.out.println("--------------------------");

            coursesList.forEach(s -> System.out.println(s.getName() + " - " + teacher.getName()));

            transaction.commit();

        }

    }
}
