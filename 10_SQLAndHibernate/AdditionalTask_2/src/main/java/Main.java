import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Set;


public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()
        ) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Notification firstType = new SendsWork();
            firstType.setTeacher(session.get(Teacher.class, 10));
            firstType.setHeader("Работа студента "+session.get(Student.class, 10).getName());
            firstType.setText("Направляется работа на проверку");
            ((SendsWork) firstType).setStudentTrigger(session.get(Student.class, 10));
            session.save(firstType);

            transaction.commit();

        }

    }
}
