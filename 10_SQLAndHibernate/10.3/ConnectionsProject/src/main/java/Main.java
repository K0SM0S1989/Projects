import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()
        ) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

//            Teacher teacher = new Teacher();
//            teacher.setName("Северус Снейп");
//            teacher.setSalary(150000);
//            teacher.setAge(35);
//            session.save(teacher);

//            Course course = new Course();
//            course.setName("Зельеварение");
//            course.setType(CourseType.PROGRAMMING);
//            course.setTeacher(teacher);
//            course.setDuration(30);
//            course.setPrice(236000);
//            course.setPricePerHour(course.getPrice()/course.getDuration());
//            session.save(course);

//            Student student = new Student();
//            student.setName("Невилл Долгопупс");
//            student.setAge(18);
//            student.setRegistrationDate(new Date());
//            session.save(student);

//            Course course = session.get(Course.class, 48);
//            Student student = session.get(Student.class, 101);
//
//            Subscription subscription = new Subscription();
//            subscription.setId(new Subscription.Id(course, student));
//            subscription.setSubscriptionDate(new Date());
//            session.save(subscription);
            Course course = session.get(Course.class, 48);
            Student student = session.get(Student.class, 101);
            Teacher teacher = session.get(Teacher.class, 51);

            System.out.println("------------------------");
            List<Course> courses = teacher.getCourses();
            courses.forEach(s-> System.out.println(s.getName()+" - "+s.getTeacher().getName()));
            System.out.println("------------------------");
            List<Subscription> subscriptions = student.getSubscriptions();
            subscriptions.forEach(s-> System.out.println(student.getName()+" - "+s.getId().getCourse().getName()+" - "+s.getSubscriptionDate()));
            System.out.println("------------------------");
            List<Subscription> subscriptions1 = course.getSubscriptions();
            subscriptions.forEach(s-> System.out.println(course.getName()+" - "+s.getId().getStudent().getName()+" - "+s.getSubscriptionDate()));


            transaction.commit();
        }


    }
}
