import java.util.HashMap;

public class BankISFraudTrue extends Bank {

   private  HashMap<String, Account> blockAccounts = new HashMap<>();


    @Override
    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        return true;
    }



}
