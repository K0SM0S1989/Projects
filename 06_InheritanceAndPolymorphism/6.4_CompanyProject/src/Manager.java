

public class Manager implements Employee{
    private   double monthSalary;
    private static final double FIX_PRICE = 65000;
    private int earnedMoney = (int)(Math.random()*2500000);

    public Manager(Company company){
        company.setIncome(earnedMoney);

    }
    @Override
    public double getMonthSalary() {
        double procent = earnedMoney * 0.05;
        this.monthSalary = FIX_PRICE + procent;
        return monthSalary;
    }
}
