package test;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;
import org.openjdk.jcstress.infra.results.J_Result;

import java.util.HashMap;

@JCStressTest

@Outcome(expect = Expect.ACCEPTABLE)

@State
public class Test {


    Bank bank;
    private static HashMap<String, Account> accountMap = new HashMap<>();
    Account account1;
    Account account2;
    Account account3;
    private static final int probability = (int) (Math.random() * 101);
    private static long amount;


    @Actor
    public void thread1() {
        bank = new Bank();
        account1 = new Account(60000, "111");
        account2 = new Account(80000, "222");
        account3 = new Account(50000, "333");

        accountMap.put(account1.getAccNumber(), account1);
        accountMap.put(account2.getAccNumber(), account2);
        accountMap.put(account3.getAccNumber(), account3);

        bank.setAccounts(accountMap);
        if (probability <= 5) {
            amount = 60000;
        } else amount = 10000;
    }

    @Actor
    public void thread2(J_Result r) {
        if (bank != null && bank.getAccounts() != null) {
            bank.transfer("111", "222", amount);

        } else r.r1 = -1;

    }

    @Arbiter
    public void arbiter(J_Result r) {
        if (bank != null && bank.getAccounts() != null) {
            r.r1 = bank.getBalance("111");
        } else r.r1 = -1;

    }


}
