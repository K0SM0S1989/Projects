import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()
        ) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Course course = session.get(Course.class, 10);
            Student student = session.get(Student.class, 1);
            Teacher teacher = session.get(Teacher.class, 10);

            System.out.println("==========================");
            PurchaseList purchaseList = session.get(PurchaseList.class, new PurchaseList(student.getName(), course.getName()).getId());

            try {
                System.out.println(purchaseList.getPrice() + " - " + purchaseList.getSubscriptionDate() + " - " + student.getName());

            }catch (NullPointerException ex){
                System.out.println("Студент не подписан на выбранный курс");
            }

            System.out.println("==========================");

            List<Course> courses = teacher.getCourses();
            courses.forEach(s -> System.out.println(s.getName() + " - " + teacher.getName()));

            System.out.println("==========================");

            List<Subscription> subsStud = student.getSubscriptions();
            subsStud.forEach(s -> System.out.println(s.getCourse().getName() + " - " + student.getName() + " - " + s.getSubscriptionDate()));

            System.out.println("==========================");

            List<Subscription> subsCour = course.getSubscriptions();
            subsCour.forEach(s -> System.out.println(s.getStudent().getName() + " - " + course.getName() + " - " + s.getSubscriptionDate()));


            transaction.commit();
        }


    }
}
