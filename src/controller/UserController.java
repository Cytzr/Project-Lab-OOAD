package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import utilities.Connect;

public class UserController {

    private final Connect db;
    private final Connection con;

    public UserController() {
        this.db = Connect.getInstance();
        this.con = db.getConnection();
    }
// Register / Create User
    public boolean createUser(String username, String password, String phone_number, String address, String role) {
       
        String checkQuery = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (PreparedStatement checkPst = con.prepareStatement(checkQuery)) {
            checkPst.setString(1, username);
            ResultSet rs = checkPst.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
              
                return false;
            }
            
          
            String insertQuery = "INSERT INTO user(username, password, phone_number, address, role) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(insertQuery)) {
                pst.setString(1, username);
                pst.setString(2, password);
                pst.setString(3, phone_number);
                pst.setString(4, address);
                pst.setString(5, role);
                pst.executeUpdate();
              
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // login and get user data
    public Map<String, String> login(String username, String password) {
        String query = "SELECT *  FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                	Map<String, String> result = new HashMap<>();
                    result.put("userId", rs.getString("user_id"));
                    result.put("role", rs.getString("role"));
                    return result;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}