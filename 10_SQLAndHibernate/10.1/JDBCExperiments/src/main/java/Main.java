import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String pass = "";//Пароль не хотелось бы светить ибо он у меня много где используется

        String sqlCode = "SELECT  course_name as name,\n" +
                "(COUNT(student_name)/TIMESTAMPDIFF(MONTH, MIN(subscription_date), \n" +
                "MAX(subscription_date))\n" +
                ") as average_per_month\n" +
                "FROM PurchaseList GROUP BY course_name;";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlCode)) {
            while (resultSet.next()) {
                String nameColumn = resultSet.getString("name");
                String averageColumn = resultSet.getString("average_per_month");
                System.out.println(nameColumn + " - " + averageColumn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
