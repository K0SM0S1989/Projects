import java.util.ArrayList;
import java.util.Comparator;

public class Company {

    private double income;
    private ArrayList<Employee> employees = new ArrayList<>();


    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(ArrayList<Employee> employeeArrayList) {
        employees.addAll(employeeArrayList);
    }


    public void fire(int index) {
        employees.remove(index);
    }


    public ArrayList<Employee> getTopSalaryStaff(int count) {
        if (count > employees.size()) {
            count = employees.size();
            System.out.println("Запрашиваемое число превышает количество сотрудников");
        }
        employees.sort((Comparator<Employee>) (o1, o2) -> {
            if (o1.getMonthSalary() < o2.getMonthSalary()) {
                return 1;
            }
            if (o1.getMonthSalary() > o2.getMonthSalary()) {
                return -1;
            }
            return 0;
        });
        ArrayList<Employee> arrayLists = new ArrayList<>();


        for (int i = 0; i < count; i++) {
            arrayLists.add(employees.get(i));
            System.out.println((i + 1) + ") " + arrayLists.get(i).getMonthSalary());
        }

        return arrayLists;
    }

    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        if (count > employees.size()) {
            count = employees.size();
            System.out.println("Запрашиваемое число превышает количество сотрудников");
        }
        employees.sort((Comparator<Employee>) (o1, o2) -> {
            if (o1.getMonthSalary() < o2.getMonthSalary()) {
                return -1;
            }
            if (o1.getMonthSalary() > o2.getMonthSalary()) {
                return 1;
            }
            return 0;
        });
        ArrayList<Employee> arrayLists = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            arrayLists.add(employees.get(i));
            System.out.println((i + 1) + ") " + arrayLists.get(i).getMonthSalary());
        }
        return arrayLists;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income += income;
    }

    public int getEmployeesCount(){
        return employees.size();
    }
}
