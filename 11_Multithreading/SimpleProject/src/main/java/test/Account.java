package test;

public class Account implements Comparable<Account>{
    private long money;
    private String accNumber;
    private boolean blockStatus;

    public Account(long money, String accNumber, boolean blockStatus) {
        this.money = money;
        this.accNumber = accNumber;
        this.blockStatus = blockStatus;
    }

    public Account() {
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(boolean blockStatus) {
        this.blockStatus = blockStatus;
    }

    @Override
    public int compareTo(Account o) {
        return this.getAccNumber().compareTo(o.getAccNumber());
    }
}
