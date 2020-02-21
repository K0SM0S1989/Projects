import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
     Company company  = new Company();
        ArrayList<Employee> arrayListManager = new ArrayList<>();
        ArrayList<Employee> arrayListTopManager = new ArrayList<>();
        ArrayList<Employee> arrayListOperator = new ArrayList<>();

        for (int i = 0; i<180;i++){
            arrayListOperator.add(new Operator());
        }
        for (int i = 0; i<80;i++){
            arrayListManager.add(new Manager(company));
        }
        for (int i = 0; i<10;i++){
            arrayListTopManager.add(new TopManager(company));
        }

        company.hireAll(arrayListTopManager);
        company.hireAll(arrayListManager);
        company.hireAll(arrayListOperator);

        company.getTopSalaryStaff(15);
        System.out.println("================");
        company.getLowestSalaryStaff(30);
        System.out.println("================");
        System.out.println("Количество работников в компании - " + company.getEmployeesCount());

        int dismissalList = company.getEmployeesCount() / 2;


        for (int i = 0; i < dismissalList; i++) {
            company.fire(i);
        }

        System.out.println("================");
        company.getTopSalaryStaff(15);
        System.out.println("================");
        company.getLowestSalaryStaff(30);

        System.out.println("Количество работников в компании - " + company.getEmployeesCount());
    }
}
