package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilities.Connect;

public class UserController {

    private final Connect db;
    private final Connection con;

    public UserController() {
        this.db = Connect.getInstance();
        this.con = db.getConnection();
    }

    public void createUser(String username, String password , String phone_number, String address, String role) {
        String query = "INSERT INTO user(username, password, phone_number, address, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, phone_number);
            pst.setString(4, address);
            pst.setString(5, role);
            pst.executeUpdate();
            System.out.println("User created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String login(String username, String password) {
        String query = "SELECT username FROM orang WHERE username = ? AND password = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return null if no matching record is found
        return null;
    }
}