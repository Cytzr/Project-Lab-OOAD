package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Item;
import utilities.Connect;

public class ItemController {

    private final Connect db;
    private final Connection con;

    public ItemController() {
        this.db = Connect.getInstance();
        this.con = db.getConnection();
    }

    public boolean uploadItem(String itemName, String itemSize , int itemPrice, String itemCategory, String userId) {
        String query = "INSERT INTO item(item_name, item_size, item_category, item_price, user_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, itemName);
            pst.setString(2, itemSize);
            pst.setString(3, itemCategory);
            pst.setInt(4, itemPrice);
            pst.setString(5, userId);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Item> getSellerItem(String userId) {
        String query = "SELECT * FROM item WHERE user_id = ?";
        List<Item> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userId);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                   
                    Item item = new Item(
                        rs.getString("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_size"),
                        rs.getString("item_price"),
                        rs.getString("item_category"),
                        (rs.getInt("item_status") == 1 ? "Yes" : "No"),
                        rs.getString("item_wishlist"),
                       rs.getString("item_offer_status"),
                        rs.getString("user_id")
                       
                    );
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    public boolean editItem(String itemId, String itemName, String itemSize, int itemPrice, String itemCategory) {
        String query = "UPDATE item SET item_name = ?, item_size = ?, item_category = ?, item_price = ? WHERE item_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, itemName);
            pst.setString(2, itemSize); 
            pst.setString(3, itemCategory); 
            pst.setInt(4, itemPrice); 
            pst.setString(5, itemId);
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) { 
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean approveItem(String itemId) {
        String query = "UPDATE item SET item_status = 1 WHERE item_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, itemId);
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) { 
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean declineItem(String itemId, String reason) {
        String query = "UPDATE item SET item_status = 0, reason = ? WHERE item_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, reason);
            pst.setString(2, itemId);
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) { 
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteItem(String itemId) {
        String query = "DELETE FROM item WHERE item_id = ? AND item_status = 1";
        
        try (PreparedStatement pst = con.prepareStatement(query)) {
           
            pst.setString(1, itemId);
            
            int affectedRows = pst.executeUpdate();
        
            if (affectedRows > 0) {
                return true;
            } else {
                
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Item> ViewItem() {
        String query = "SELECT * FROM item WHERE item_status = 1";
        List<Item> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {    
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                   
                    Item item = new Item(
                        rs.getString("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_size"),
                        rs.getString("item_price"),
                        rs.getString("item_category"),
                       rs.getString("item_status"),
                        rs.getString("item_wishlist"),
                       rs.getString("item_offer_status"),
                        rs.getString("user_id")
                    );
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    
    public List<Item> ViewRequestedItem() {
        String query = "SELECT * FROM item WHERE item_status IS NULL";
        List<Item> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {    
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                   
                    Item item = new Item(
                        rs.getString("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_size"),
                        rs.getString("item_price"),
                        rs.getString("item_category"),
                       rs.getString("item_status"),
                        rs.getString("item_wishlist"),
                       rs.getString("item_offer_status"),
                        rs.getString("user_id")
                    );
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    
    
    
}