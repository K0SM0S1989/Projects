import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

            Query purch = session.createQuery("from PurchaseList");
            List<PurchaseList> purchaseLists = purch.getResultList();

            Query course = session.createQuery("from Course");
            List<Course> courseList = course.getResultList();

            Query student = session.createQuery("from Student");
            List<Student> studentList = student.getResultList();

            Query link = session.createQuery("from " + LinkedPurchaseList.class.getSimpleName());
            List<LinkedPurchaseList> linkedPurchaseLists = link.getResultList();

            purchaseLists.forEach(s -> {

                String stCourse = s.getId().getCourseName();
                String stStud = s.getId().getStudentName();
                int j = 0;
                int k = 0;
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getName().equals(stStud)) {
                        k = i + 1;
                        break;
                    }
                }
                for (int i = 0; i < courseList.size(); i++) {
                    if (courseList.get(i).getName().equals(stCourse)) {
                        j = i + 1;
                        break;
                    }
                }
                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(new LinkedPurchaseList.Id(j, k));
                session.save(linkedPurchaseList);
                linkedPurchaseLists.add(linkedPurchaseList);

            });

            transaction.commit();

        }


    }
}
