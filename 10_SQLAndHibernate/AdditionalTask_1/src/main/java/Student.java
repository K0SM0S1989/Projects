import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;


    @OneToMany(fetch = FetchType.LAZY,targetEntity = Subscription.class, mappedBy = "id.student")
    private List<Subscription> subscriptions = new ArrayList<>();

    public void addSubscriptions(Subscription subscription) {
        subscriptions.add(subscription);
        subscription.getId().setStudent(this);
    }

    public void removeSubscription(Subscription subscription) {
        subscriptions.remove(subscription);
        subscription.getId().setStudent(null);
    }
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }




}
