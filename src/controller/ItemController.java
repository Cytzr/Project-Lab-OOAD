package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.ItemFactory;
import model.Item;
import utilities.Connect;

public class ItemController {

    private final Connect db;
    private final Connection con;

    public ItemController() {
        this.db = Connect.getInstance();
        this.con = db.getConnection();
    }

    // Upload Item to table item
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
    
    // Get all the seller item based on the userId of the seller
    public List<Item> getSellerItem(String userId) {
        String query = "SELECT * FROM item WHERE user_id = ?";
        List<Item> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userId);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                   
                	String itemId = rs.getString("item_id");
                    String itemName = rs.getString("item_name");
                    String itemSize = rs.getString("item_size");
                    String itemPrice = rs.getString("item_price");
                    String itemCategory = rs.getString("item_category");
                    int itemStatus = rs.getInt("item_status");
                    String itemWishlist = rs.getString("item_wishlist");
                    String itemOfferStatus = rs.getString("item_offer_status");
                    String sellerUserId = rs.getString("user_id");

                    Item item = ItemFactory.createItem(
                        itemId, itemName, itemSize, itemPrice, itemCategory, 
                        itemStatus, itemWishlist, itemOfferStatus, sellerUserId
                    );

                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    //Edit item based on the itemId and get all the updated data
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
    // Admin approve the seller's item based on the itemId
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
    // Admin decline the seller's item based on the itemId and delete from database
    public boolean declineItem(String itemId, String reason) {
        String query = "DELETE FROM item WHERE item_id = ?";
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
    
    // Delete the item based on itemId
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
    //View all item for buyer page where all the item_status is true or approved by admin
    public List<Item> ViewItem() {
        String query = "SELECT * FROM item WHERE item_status = 1";
        List<Item> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {    
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                   
            
                    String itemId = rs.getString("item_id");
                    String itemName = rs.getString("item_name");
                    String itemSize = rs.getString("item_size");
                    String itemPrice = rs.getString("item_price");
                    String itemCategory = rs.getString("item_category");
                    int itemStatus = rs.getInt("item_status");
                    String itemWishlist = rs.getString("item_wishlist");
                    String itemOfferStatus = rs.getString("item_offer_status");
                    String sellerUserId = rs.getString("user_id");

                    Item item = ItemFactory.createItem(
                        itemId, itemName, itemSize, itemPrice, itemCategory, 
                        itemStatus, itemWishlist, itemOfferStatus, sellerUserId
                    );

                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    
    //Get all the item that is await to be approved
    public List<Item> ViewRequestedItem() {
        String query = "SELECT * FROM item WHERE item_status IS NULL";
        List<Item> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {    
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                   
                	String itemId = rs.getString("item_id");
                    String itemName = rs.getString("item_name");
                    String itemSize = rs.getString("item_size");
                    String itemPrice = rs.getString("item_price");
                    String itemCategory = rs.getString("item_category");
                    int itemStatus = rs.getInt("item_status");
                    String itemWishlist = rs.getString("item_wishlist");
                    String itemOfferStatus = rs.getString("item_offer_status");
                    String sellerUserId = rs.getString("user_id");

                 
                    Item item = ItemFactory.createItem(
                        itemId, itemName, itemSize, itemPrice, itemCategory, 
                        itemStatus, itemWishlist, itemOfferStatus, sellerUserId
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