import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/Aaditya"; // replace with your DB
    private static final String USER = "root"; // DB username
    private static final String PASSWORD = "Aaditya@3004"; // DB password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false); // Transaction management

            while (true) {
                System.out.println("\n1. Create Product\n2. Read Products\n3. Update Product\n4. Delete Product\n5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1: // Create
                        System.out.print("Product Name: ");
                        String name = sc.nextLine();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        String insertSQL = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
                        try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                            ps.setString(1, name);
                            ps.setDouble(2, price);
                            ps.setInt(3, qty);
                            ps.executeUpdate();
                            conn.commit();
                            System.out.println("Product added.");
                        } catch (SQLException e) {
                            conn.rollback();
                            e.printStackTrace();
                        }
                        break;

                    case 2: // Read
                        String selectSQL = "SELECT * FROM Product";
                        try (Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery(selectSQL)) {
                            System.out.println("ProductID | ProductName | Price | Quantity");
                            while (rs.next()) {
                                System.out.println(rs.getInt("ProductID") + " | " +
                                        rs.getString("ProductName") + " | " +
                                        rs.getDouble("Price") + " | " +
                                        rs.getInt("Quantity"));
                            }
                        }
                        break;

                    case 3: // Update
                        System.out.print("Enter ProductID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("New Product Name: ");
                        String newName = sc.nextLine();
                        System.out.print("New Price: ");
                        double newPrice = sc.nextDouble();
                        System.out.print("New Quantity: ");
                        int newQty = sc.nextInt();
                        String updateSQL = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
                        try (PreparedStatement ps = conn.prepareStatement(updateSQL)) {
                            ps.setString(1, newName);
                            ps.setDouble(2, newPrice);
                            ps.setInt(3, newQty);
                            ps.setInt(4, updateId);
                            int rows = ps.executeUpdate();
                            if (rows > 0) conn.commit();
                            else System.out.println("No product found with given ID.");
                        } catch (SQLException e) {
                            conn.rollback();
                            e.printStackTrace();
                        }
                        break;

                    case 4: // Delete
                        System.out.print("Enter ProductID to delete: ");
                        int deleteId = sc.nextInt();
                        String deleteSQL = "DELETE FROM Product WHERE ProductID=?";
                        try (PreparedStatement ps = conn.prepareStatement(deleteSQL)) {
                            ps.setInt(1, deleteId);
                            int rows = ps.executeUpdate();
                            if (rows > 0) conn.commit();
                            else System.out.println("No product found with given ID.");
                        } catch (SQLException e) {
                            conn.rollback();
                            e.printStackTrace();
                        }
                        break;

                    case 5:
                        conn.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
