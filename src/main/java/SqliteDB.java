import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SqliteDB {
    private static Connection connection;
    private static Statement statement;

    public static void connectDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        statement = connection.createStatement();
    }

    public static void closeDB() throws SQLException {
        statement.close();
        connection.close();
    }
    
    public static void createSchoolTable() throws SQLException {
        statement.execute(
                "CREATE TABLE IF NOT EXISTS School (" +
                    "school TEXT, " +
                    "students INTEGER, " +
                    "country TEXT, " +
                    "expenditure FLOAT, " +
                    "math FLOAT);"
        );
    }
    
    public static void addDataToSchoolTable(School school) throws SQLException {
        statement.execute(String.format(
                "INSERT INTO School " +
                "VALUES ('%s', '%s', '%s', '%s', '%s')",
                school.getSchool(), school.getStudents(), school.getCountry(), school.getExpenditure(), school.getMath())
        );
    }

    public static void solveTasks() throws SQLException {
        firstTask();
        System.out.println("\nСреднее количество расходов:\n" + secondTask() + "\n");
        System.out.println(thirdTask());
    }

    private static void firstTask() throws SQLException {
        String firstSql = "" +
                "SELECT country, AVG(students) " +
                "FROM School " +
                "WHERE country IN " +
                "      ('Tulare', 'Fresno', 'Merced', 'Sacramento', " +
                "       'Kern', 'San Joaquin', 'Monterey', 'San Diego', " +
                "       'Los Angeles', 'Ventura') " +
                "GROUP BY country;";

        Map<String, Double> result = new HashMap<>();
        ResultSet resSql = statement.executeQuery(firstSql);
        while (resSql.next()) {
            result.put(
                    resSql.getString("country"),
                    Double.parseDouble(resSql.getString("avg(students)")));
        }

        EventQueue.invokeLater(() -> {
            var graph = new Graphic(result);
            graph.setVisible(true);
        });
    }

    private static String secondTask() throws SQLException {
        String secondSql = "" +
                "SELECT AVG(expenditure) " +
                "FROM School " +
                "WHERE country IN ('Fresno', 'Contra Costa', 'El Dorado', 'Glenn') " +
                "  AND expenditure > 10;";

        return statement.executeQuery(secondSql).getString("AVG(expenditure)");
    }

    private static String thirdTask() throws SQLException {
        return String.format("Учебные заведения с самым высоким показателем по математике:\n%s\n%s", firstSchool(), secondSchool());
    }

    private static String firstSchool() throws SQLException {
        return statement.executeQuery(
                "SELECT school, MAX(math) " +
                "FROM School " +
                "WHERE students >= 5000 AND students <= 7500;"
        ).getString("school");
    }

    private static String secondSchool() throws SQLException {
        return statement.executeQuery(
                "SELECT school, MAX(math) " +
                "FROM School " +
                "WHERE students >= 10000 AND students <= 11000;"
        ).getString("school");
    }
}