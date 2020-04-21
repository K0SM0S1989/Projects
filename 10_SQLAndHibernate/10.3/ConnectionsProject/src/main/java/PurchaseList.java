import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Purchaselist")
public class PurchaseList {
    @Embeddable
    private static class Id implements Serializable {

        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;

        public Id() {
        }

        public Id(String studentName, String courseName) {
            this.studentName = studentName;
            this.courseName = courseName;
        }

        public boolean equals(Object o) {
            if (o != null && o instanceof Id) {
                Id that = (Id) o;
                return this.studentName.equals(that.studentName)
                        && this.courseName.equals(that.courseName);
            }
            return false;
        }

        public int hashCode() {
            return studentName.hashCode() + courseName.hashCode();

        }

    }


    @EmbeddedId
    private Id id;

    private int price;

    @Column(name = "subscription_date")

    private Date subscriptionDate;

    public PurchaseList(String studentName, String courseName) {
        id = new Id(studentName, courseName);
    }

    public PurchaseList() {
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
