package bills;

import java.time.LocalDate;

public class DepositAccount extends PaymentAccount{
    private LocalDate dayOfReplenishment;
    private LocalDate dayOfWithdrawal;
    public DepositAccount(int balance) {
        super(balance);
    }


    /**
     * Снятие со счета суммы равной параметру
     * @param withdrawal
     */
    @Override
    public void withdrawal(double withdrawal) {
        dayOfWithdrawal = LocalDate.now();
        if (dayOfReplenishment != null && dayOfWithdrawal.getDayOfYear() <= dayOfReplenishment.plusMonths(1).getDayOfYear()){
                System.out.println("С момента пополнения не прошло месяца");
            }else super.withdrawal(withdrawal);
    }

    /**
     * Пополнение на сумму параметра
     * @param replenishment
     */
    @Override
    public void replenishment(double replenishment) {
        dayOfReplenishment = LocalDate.now();
        super.replenishment(replenishment);

    }
}

