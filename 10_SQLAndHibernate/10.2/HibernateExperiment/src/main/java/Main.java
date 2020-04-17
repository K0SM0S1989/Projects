import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()) {
            Session session = sessionFactory.openSession();

            Course course = session.get(Course.class, 3);
            Teacher teacher = session.get(Teacher.class, course.getTeacherId());

            System.out.println("Название курса - " + course.getName() + "\n" +
                    "Продолжительность курса - " + course.getDuration() + "\n" +
                    "Тип курса - " + course.getType() + "\n" +
                    "Описание курса - " + course.getDescription() + "\n" +
                    "Имя преподавателя - " + teacher.getName() + "\n" +
                    "Количество студентов на курсе - " + course.getStudentsCount() + "\n" +
                    "Стоимость курса - " + course.getPrice() + "\n" +
                    "Стоимость курса в час - " + course.getPricePerHour());
        }

    }
}

