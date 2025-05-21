    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginandsignup;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

//import com.sun.jdi.connect.spi.Connection;

/**
 *
 * @author Asus
 */
public class DatabaseAuthenticator implements Authenticator{
       
    // Database connection details (replace placeholders)
    private final String url = "jdbc:mysql://localhost:3307/bmi_data";
    private final String user = "your_username";
    private final String pass = "your_password";

    @Override
    public boolean authenticate(String email, String password) { 
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT password FROM users WHERE email = ?")) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    return storedPassword.equals(password); // Direct comparison (NOT secure)
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
