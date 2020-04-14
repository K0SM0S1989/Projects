import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String pass = "";//Пароль не хотелось бы светить ибо он у меня много где используется

        String sqlCode = "select Courses.name, " +
                "count(Subscriptions.course_id)/(select ((select dayofyear(subscription_date)" +
                " from Subscriptions order by subscription_date desc limit 1)" +
                "-(select dayofyear(subscription_date) from Subscriptions order by subscription_date limit 1))/30) as average_per_month" +
                " from Courses" +
                " join Subscriptions on Courses.id = Subscriptions.course_id group by Courses.name;";
        try(Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCode)) {
                        while (resultSet.next()){
                String nameColumn = resultSet.getString("name");
                String averageColumn = resultSet.getString("average_per_month");
                System.out.println(nameColumn+" - "+averageColumn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
