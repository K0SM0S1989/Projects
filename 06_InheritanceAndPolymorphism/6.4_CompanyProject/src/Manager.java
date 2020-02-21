

public class Manager implements Employee{
    private   double monthSalary;
    private static final double FIX_PRICE = 65000;
    private int earnedMoney = (int)(Math.random()*2500000);

    public Manager(Company company){
        company.setIncome(earnedMoney);
        setMonthSalary();
    }
    @Override
    public double getMonthSalary() {
       return monthSalary;
    }

    public void setMonthSalary() {

        double procent = earnedMoney*0.05;
        this.monthSalary = FIX_PRICE+procent;
    }
}
