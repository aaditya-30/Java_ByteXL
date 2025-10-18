import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcReadExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Aaditya";
        String user = "root";
        String password = "Aaditya@3004"; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver
            Connection conn = DriverManager.getConnection(url, user, password); // Establish connection
            Statement stmt = conn.createStatement();
            String query = "SELECT EmpID, Name, Salary FROM Employee"; // SQL query
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int empId = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println("EmpID: " + empId + ", Name: " + name + ", Salary: " + salary);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
