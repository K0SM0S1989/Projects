import bills.CardAccount;
import bills.DepositAccount;
public class Main {

    public static void main(String[] args) {
        CardAccount cardAccount = new CardAccount(1000);
        cardAccount.replenishment(550);
        System.out.println("Баланс карты - "+cardAccount.getBalance());
        cardAccount.withdrawal(1000);
        System.out.println("Баланс карты - "+cardAccount.getBalance());


        DepositAccount depositAccount = new DepositAccount(1000);
        depositAccount.replenishment(100);
        depositAccount.withdrawal(350);
        System.out.println("Баланс депозита - "+depositAccount.getBalance());
    }
}
