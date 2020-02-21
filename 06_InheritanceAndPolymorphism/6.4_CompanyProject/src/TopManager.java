

public class TopManager implements Employee {
    private double monthSalary;
    private static final double FIX_PRICE = 74000;

    public TopManager(Company company) {
        setMonthSalary(company);
    }

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(Company company) {

        if (company.getIncome() > 10000000) {
            this.monthSalary = FIX_PRICE + FIX_PRICE * 1.5;
        } else this.monthSalary = FIX_PRICE;

    }
}
