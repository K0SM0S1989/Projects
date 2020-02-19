package bills;

public class CardAccount extends PaymentAccount {

    public CardAccount(int balance) {
        super(balance);
    }

    /**
     * Снятие со счета суммы равной параметру
     * @param withdrawal
     */
    @Override
    public void withdrawal(double withdrawal) {
        double commission = 0.01;
        double allWithdrawal = withdrawal+withdrawal*commission;
        System.out.println("Комиссия за снятие средств "+withdrawal*commission);
        super.withdrawal(allWithdrawal);
    }


}

