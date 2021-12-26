import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        SqliteDB.connectDB();
        SqliteDB.createSchoolTable();
        Parser.parseCsvFile().forEach(s -> {
            try {
                SqliteDB.addDataToSchoolTable(s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        SqliteDB.solveTasks();
    }
}
