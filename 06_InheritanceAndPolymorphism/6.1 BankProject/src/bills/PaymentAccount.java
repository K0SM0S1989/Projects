package bills;

public class PaymentAccount {

    public double balance;//баланс

    public PaymentAccount(int balance){
        this.balance=balance;
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
        if (withdrawal > balance){
            System.out.println("Недостаточно средств на счёте!");
        } else this.balance -=withdrawal;

    }

    /**
     * Пополнение на сумму параметра
     * @param replenishment
     */
    public void replenishment(double replenishment) {
        this.balance +=replenishment;
    }

}