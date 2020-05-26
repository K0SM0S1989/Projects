package test;

import java.util.HashMap;
import java.util.Random;


public class Bank {

    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        synchronized (accounts.get(fromAccountNum).compareTo(accounts.get(toAccountNum)) < 0 ? accounts.get(fromAccountNum) : accounts.get(toAccountNum)) {
            synchronized (accounts.get(toAccountNum).compareTo(accounts.get(fromAccountNum)) < 0 ? accounts.get(toAccountNum) : accounts.get(fromAccountNum)) {
                if (!accounts.get(fromAccountNum).isBlockStatus() && !accounts.get(toAccountNum).isBlockStatus() && accounts.get(fromAccountNum).getMoney() >= amount) {

                    if (amount > 50000) {
                        try {
                            if (isFraud(fromAccountNum, toAccountNum, amount)) {

                                accounts.get(fromAccountNum).setBlockStatus(true);
                                accounts.get(toAccountNum).setBlockStatus(true);
                                System.out.println("Счета заблокированы!");

                            } else {
                                accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                                accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                        accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
                    }

                } else {

                    System.out.println("Транзакция невозможна");
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        synchronized (accountNum) {
            return accounts.get(accountNum).getMoney();
        }
    }


    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, Account> accounts) {

        this.accounts = accounts;
    }
}
