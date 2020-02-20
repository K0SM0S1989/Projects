package bank;

public class IndividualEntrepreneur extends Client {

    public IndividualEntrepreneur(double balance) {
        setPaymentAccount(balance);
    }

    @Override
    public void replenishment(double replenishment) {
        if (replenishment < 1000) {
            double comission = 0.01;
            System.out.println("Комиссия за пополнение 1% = " + replenishment * comission);
            double allReplenishment = replenishment - replenishment * comission;
            setPaymentAccount(getPaymentAccount() + allReplenishment);
        }
        if (replenishment >= 1000) {
            double comission = 0.005;
            System.out.println("Комиссия за пополнение 0.5% = " + replenishment * comission);
            double allReplenishment = replenishment - replenishment * comission;
            setPaymentAccount(getPaymentAccount() + allReplenishment);
        }
    }
}
