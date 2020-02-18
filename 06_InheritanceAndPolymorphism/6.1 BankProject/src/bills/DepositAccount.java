package bills;

import java.time.LocalDate;

public class DepositAccount extends PaymentAccount{
    private LocalDate dayOfReplenishment;
    private LocalDate dayOfWithdrawal;
    public DepositAccount(int balance) {
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
        dayOfWithdrawal = LocalDate.now();
        if (dayOfReplenishment != null) {
            if (dayOfWithdrawal.getDayOfYear() <= dayOfReplenishment.plusMonths(1).getDayOfYear()) {
                System.out.println("С момента пополнения не прошло месяца");
            } else {
                this.balance -= withdrawal;
            }
        }else this.balance -= withdrawal;
    }

    /**
     * Пополнение на сумму параметра
     * @param replenishment
     */
    public void replenishment(double replenishment) {
        dayOfReplenishment = LocalDate.now();
        this.balance +=replenishment;
    }
}

