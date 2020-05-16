import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;



import static org.junit.jupiter.api.Assertions.*;


class BankTest {


    private Account account1, account2, account3;
    private Bank bank;
    private long amountBig;
    private long amountSmall;
    private BankIsFraudFalse bankIsFraudFalse;
    private BankISFraudTrue bankISFraudTrue;


    @BeforeEach
    void setUp() {
        bankIsFraudFalse = new BankIsFraudFalse();
        bankISFraudTrue = new BankISFraudTrue();
        amountBig = 52000;
        amountSmall = 40000;
        bank = new Bank();
        HashMap<String, Account> accountMap = new HashMap<>();
        account1 = new Account(50000, "111");
        account2 = new Account(75000, "222");
        account3 = new Account(45000, "333");
        accountMap.put(account1.getAccNumber(), account1);
        accountMap.put(account2.getAccNumber(), account2);
        accountMap.put(account3.getAccNumber(), account3);

        bank.setAccounts(accountMap);
        bankISFraudTrue.setAccounts(accountMap);
        bankIsFraudFalse.setAccounts(accountMap);


    }

    @Test
    void transferTestMoreAmountIsFraudTrue() {

        String fromAccountNum = bankISFraudTrue.getAccounts().get(account2.getAccNumber()).getAccNumber();
        String toAccountNum = bankISFraudTrue.getAccounts().get(account1.getAccNumber()).getAccNumber();

        bankISFraudTrue.transfer(fromAccountNum, toAccountNum, amountBig);

        boolean actualAcc1 = bankISFraudTrue.getAccounts().containsKey(toAccountNum);
        boolean actualAcc2 = bankISFraudTrue.getAccounts().containsKey(fromAccountNum);

        assertFalse(actualAcc1);
        assertFalse(actualAcc2);

        boolean actualBlockAcc1 = bankISFraudTrue.getBlockAccounts().containsKey(toAccountNum);
        boolean actualBlockAcc2 = bankISFraudTrue.getBlockAccounts().containsKey(fromAccountNum);

        assertTrue(actualBlockAcc1);
        assertTrue(actualBlockAcc2);

        long actualAmountAcc2 = bankISFraudTrue.getBlockAccounts().get(fromAccountNum).getMoney();
        long actualAmountAcc1 = bankISFraudTrue.getBlockAccounts().get(toAccountNum).getMoney();
        long expectedAmountAcc2 = 75000;
        long expectedAmountAcc1 = 50000;
        assertEquals(expectedAmountAcc2, actualAmountAcc2);
        assertEquals(expectedAmountAcc1, actualAmountAcc1);

        bankISFraudTrue.transfer(fromAccountNum, toAccountNum, amountBig);

        boolean actualAcc1NotTr = bankISFraudTrue.getAccounts().containsKey(toAccountNum);
        boolean actualAcc2NotTr = bankISFraudTrue.getAccounts().containsKey(fromAccountNum);

        assertFalse(actualAcc1NotTr);
        assertFalse(actualAcc2NotTr);

        boolean actualBlockAcc1NotTr = bankISFraudTrue.getBlockAccounts().containsKey(toAccountNum);
        boolean actualBlockAcc2NotTr = bankISFraudTrue.getBlockAccounts().containsKey(fromAccountNum);

        assertTrue(actualBlockAcc1NotTr);
        assertTrue(actualBlockAcc2NotTr);

        long actualAmountAcc2NotTr = bankISFraudTrue.getBlockAccounts().get(fromAccountNum).getMoney();
        long actualAmountAcc1NotTr = bankISFraudTrue.getBlockAccounts().get(toAccountNum).getMoney();
        long expectedAmountAcc2NotTr = 75000;
        long expectedAmountAcc1NotTr = 50000;
        assertEquals(expectedAmountAcc2NotTr, actualAmountAcc2NotTr);
        assertEquals(expectedAmountAcc1NotTr, actualAmountAcc1NotTr);
    }

    @Test
    void transferTestMoreAmountIsFraudFalse() {
        String fromAccountNum = bankIsFraudFalse.getAccounts().get(account2.getAccNumber()).getAccNumber();
        String toAccountNum = bankIsFraudFalse.getAccounts().get(account1.getAccNumber()).getAccNumber();
        bankIsFraudFalse.transfer(fromAccountNum, toAccountNum, amountBig);
        boolean actualAcc1 = bankIsFraudFalse.getAccounts().containsKey(toAccountNum);
        boolean actualAcc2 = bankIsFraudFalse.getAccounts().containsKey(fromAccountNum);

        assertTrue(actualAcc1);
        assertTrue(actualAcc2);

        boolean actualBlockAcc1 = bankIsFraudFalse.getBlockAccounts().containsKey(toAccountNum);
        boolean actualBlockAcc2 = bankIsFraudFalse.getBlockAccounts().containsKey(fromAccountNum);

        assertFalse(actualBlockAcc1);
        assertFalse(actualBlockAcc2);

        long actualAmountAcc2 = bankIsFraudFalse.getAccounts().get(fromAccountNum).getMoney();
        long actualAmountAcc1 = bankIsFraudFalse.getAccounts().get(toAccountNum).getMoney();
        long expectedAmountAcc2 = 23000;
        long expectedAmountAcc1 = 102000;
        assertEquals(expectedAmountAcc2, actualAmountAcc2);
        assertEquals(expectedAmountAcc1, actualAmountAcc1);
    }

    @Test
    void transferTestSmallAmount() {
        String fromAccountNum = bank.getAccounts().get(account2.getAccNumber()).getAccNumber();
        String toAccountNum = bank.getAccounts().get(account1.getAccNumber()).getAccNumber();

        bank.transfer(fromAccountNum, toAccountNum, amountSmall);

        long actualAmountAcc2 = bank.getAccounts().get(fromAccountNum).getMoney();
        long actualAmountAcc1 = bank.getAccounts().get(toAccountNum).getMoney();
        long expectedAmountAcc2 = 35000;
        long expectedAmountAcc1 = 90000;
        assertEquals(expectedAmountAcc2, actualAmountAcc2);
        assertEquals(expectedAmountAcc1, actualAmountAcc1);

    }

    @Test
    void getBalanceTest() {
        long actual = bank.getBalance(account1.getAccNumber());
        long expected = 50000;
        assertEquals(expected, actual);

    }


}