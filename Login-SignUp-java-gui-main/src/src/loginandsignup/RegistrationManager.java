/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginandsignup;

/**
 *
 * @author Asus
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// ... other imports

public class RegistrationManager {
    // Database connection details (replace placeholders)
    private final String url = "jdbc:mysql://localhost:3306/bmi_data";
    private final String user = "your_username";
    private final String pass = "your_password";

    public boolean registerUser(User user) {
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO users (fullname, email, password) VALUES (?, ?, ?)")) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword()); // Assuming password is already hashed
            return stmt.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
