package bills;

public class CardAccount extends PaymentAccount {

    public CardAccount(int balance) {
        super(balance);
    }


    /**
     * Информация о балансе
     * @return
     */
    public double getBalance() {
        return balance;
    }
    /**
     * Снятие со счета суммы равной параметру
     * @param withdrawal
     */

    public void withdrawal(double withdrawal) {
        double commission = 0.01;
        double allWithdrawal = withdrawal+withdrawal*commission;
        System.out.println("Комиссия за снятие средств "+withdrawal*commission);
        this.balance -=allWithdrawal;
    }

    /**
     * Пополнение на сумму параметра
     * @param replenishment
     */
    public void replenishment(double replenishment) {
        this.balance +=replenishment;
    }
}

