import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String pass = "";//Пароль не хотелось бы светить ибо он у меня много где используется

        String sqlCode = "select course_name as name, " +
                "count(student_name)/(timestampdiff(day," +
                "(select subscription_date from Purchaselist order by subscription_date limit 1), " +
                "(select subscription_date from Purchaselist order by subscription_date desc limit 1))/30)" +
                " as average_per_month from Purchaselist group by course_name";
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
