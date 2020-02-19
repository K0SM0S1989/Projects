import bills.CardAccount;
import bills.DepositAccount;
import bills.PaymentAccount;

public class Main {

    public static void main(String[] args) {
        PaymentAccount paymentAccount = new PaymentAccount(100);
        paymentAccount.withdrawal(200);
        System.out.println(paymentAccount.getBalance());

        CardAccount cardAccount = new CardAccount(1000);
        cardAccount.replenishment(550);
        System.out.println("Баланс карты - "+cardAccount.getBalance());
        cardAccount.withdrawal(1000);
        System.out.println("Баланс карты - "+cardAccount.getBalance());


        DepositAccount depositAccount = new DepositAccount(1000);
        depositAccount.replenishment(100);
        depositAccount.withdrawal(250);
        System.out.println("Баланс депозита - "+depositAccount.getBalance());
    }
}
