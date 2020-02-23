import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {

    private double income;
    private List<Employee> employees = new ArrayList<>();


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
        Collections.sort(employees, Comparator.comparingDouble(Employee::getMonthSalary).reversed());
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
        Collections.sort(employees, Comparator.comparingDouble(Employee::getMonthSalary));

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
