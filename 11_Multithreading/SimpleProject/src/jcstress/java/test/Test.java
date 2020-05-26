package test;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;
import org.openjdk.jcstress.infra.results.J_Result;

import java.util.HashMap;

@JCStressTest


@Outcome(id = "20000", expect = Expect.ACCEPTABLE)
@Outcome(id = "60000", expect = Expect.ACCEPTABLE)
@Outcome(id = "80000", expect = Expect.ACCEPTABLE)

@State
public class Test {
    private final State state = new State();
    public static class State {
        final Bank bank;

        public State() {
            bank = new Bank();

            HashMap<String, Account> accountMap = new HashMap<>();

            Account account1 = new Account(60000, "111", false);
            Account account2 = new Account(80000, "222", false);
            Account account3 = new Account(50000, "333", false);

            accountMap.put(account1.getAccNumber(), account1);
            accountMap.put(account2.getAccNumber(), account2);
            accountMap.put(account3.getAccNumber(), account3);

            bank.setAccounts(accountMap);


        }

    }




    @Actor
    public void thread1(J_Result r) {

        if (state.bank != null && state.bank.getAccounts() != null) {
            state.bank.transfer("111", "222", 40000);

        } else r.r1 = -1;

    }

    @Actor
    public void thread2(J_Result r) {
        if (state.bank != null && state.bank.getAccounts() != null) {
            state.bank.transfer("222", "111", 60000);

        } else r.r1 = -1;

    }


    @Arbiter
    public void arbiter(J_Result r) {
        if (state.bank != null && state.bank.getAccounts() != null) {
            r.r1 = state.bank.getBalance("111");
        } else r.r1 = -1;

    }


}
