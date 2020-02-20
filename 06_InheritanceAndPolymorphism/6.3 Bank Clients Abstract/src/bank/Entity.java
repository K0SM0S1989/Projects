package bank;

public class Entity extends Client {

    public Entity(double balance) {
        setPaymentAccount(balance);
    }

    @Override
    public void withdrawal(double withdrawal) {
        double commission = 0.01;
        double allWithdrawal = withdrawal + withdrawal * commission;
        System.out.println("Комиссия за снятие средств " + withdrawal * commission);
        if (allWithdrawal > getPaymentAccount()) {
            System.out.println("Недостаточно средств для снятия!");
        } else setPaymentAccount(getPaymentAccount() - allWithdrawal);//paymentAccount -=allWithdrawal;

    }
}
