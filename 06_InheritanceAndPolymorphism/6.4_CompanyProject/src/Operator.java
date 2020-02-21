import java.util.ArrayList;

public class Operator implements Employee {
    private static final double FIX_PRICE = 35000;
    static ArrayList<Integer> list = new ArrayList<>();

    private double monthSalary;

    public Operator() {
        setMonthSalary();
    }

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary() {

        int delta = 10000;
        int category = (int) (Math.random() * 6 + 1);
        list.add(category);

        switch (category) {
            case 1:
                monthSalary = FIX_PRICE;//операционист 1ого разряда
                break;
            case 2:
                monthSalary = FIX_PRICE + delta;//операционист 2ого разряда
                break;
            case 3:
                monthSalary = FIX_PRICE + 2 * delta;//операционист 3его разряда
                break;
            case 4:
                monthSalary = FIX_PRICE + 3 * delta;//операционист 4ого разряда
                break;
            case 5:
                monthSalary = FIX_PRICE + 4 * delta;//операционист 5ого разряда
                break;
            case 6:
                monthSalary = FIX_PRICE + 5 * delta;//операционист 6ого разряда
                break;
        }
    }

}
