import java.sql.*;

public class DBConnection {
    private static Connection connection;

   // private static final String dbName = "learn";
    private static final String dbUser = "root";
    private static final String dbPass = "cJcrjxtdfy34245";
    static String url = "jdbc:mysql://localhost:3306/learn?useUnicode=true&serverTimezone=UTC";

   // private static final StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {

                connection = DriverManager.getConnection(url, dbUser, dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +

                        "PRIMARY KEY(id))");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


    public static void executeMultiInsert(StringBuilder builder) throws SQLException {

        DBConnection.getConnection().createStatement()
                .execute("INSERT INTO voter_count(name, birthDate) VALUES" +
                        builder.toString());
    }

  //  public static void countVoter(String name, String birthDay) throws SQLException {
//        birthDay = birthDay.replace('.', '-');

//        insertQuery.append((insertQuery.length() == 0 ? "" : ",")
//                + "('" + name + "', '" + birthDay + "', 1)");
//        DBConnection.getConnection().createStatement()
//                .execute("INSERT INTO voter_count(name, birthDate, `count`) VALUES('" +
//                        name + "', '" + birthDay + "', 1)");
//        String sql = "SELECT id FROM voter_count WHERE birthDate='" + birthDay + "' AND name='" + name + "'";
//        try (ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
//        ) {
//            if (!rs.next()) {
//                DBConnection.getConnection().createStatement()
//                        .execute("INSERT INTO voter_count(name, birthDate, `count`) VALUES('" +
//                                name + "', '" + birthDay + "', 1)");
//            } else {
//                Integer id = rs.getInt("id");
//                DBConnection.getConnection().createStatement()
//                        .execute("UPDATE voter_count SET `count`=`count`+1 WHERE id=" + id);
//            }
//
//        }

//    }

    public static void printVoterCounts() throws SQLException {


        String sql = "SELECT name, birthDate, COUNT(*) AS `count` FROM voter_count GROUP BY name, birthDate HAVING `count` > 1";
        try (ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);) {
            while (rs.next()) {
                System.out.println("\t" + rs.getString("name") + " (" +
                        rs.getString("birthDate") + ") - " + rs.getInt("count"));
            }
        }

    }
}
