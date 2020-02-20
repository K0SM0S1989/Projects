package bank;

public abstract class Client {

    private double paymentAccount;

    public double getPaymentAccount() {
        return paymentAccount;
    }

    void setPaymentAccount(double paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public void withdrawal(double withdrawal) {
        if (withdrawal > paymentAccount) {
            System.out.println("Недостаточно средств для снятия!");
        } else paymentAccount -= withdrawal;
    }

    public void replenishment(double replenishment) {
        paymentAccount += replenishment;
    }

}
