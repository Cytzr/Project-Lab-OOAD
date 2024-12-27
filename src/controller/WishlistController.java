package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hybrid_model.WishlistItemModel;
import model.Item;
import utilities.Connect;

public class WishlistController {

    private final Connect db;
    private final Connection con;

    public WishlistController() {
        this.db = Connect.getInstance();
        this.con = db.getConnection();
    }

    // Create wishlist based on item id and user id
    public boolean addWishListItem(String itemId, String userId) {
        String query = "INSERT INTO wishlist(item_id, user_id) VALUES (?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
           pst.setString(1,  itemId);
            pst.setString(2, userId);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get all the user's wishlist data
    public List<WishlistItemModel> ViewWishlist(String userId) {
        String query = "SELECT * FROM wishlist JOIN item on item.item_id = wishlist.item_id WHERE wishlist.user_id = ?";
        List<WishlistItemModel> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userId);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                   
                    WishlistItemModel item = new WishlistItemModel(
                        rs.getString("wishlist.wishlist_id"),
                        rs.getString("item.item_id"),
                        rs.getString("wishlist.user_id"),
                        rs.getString("item_name"),
                        rs.getString("item_category"),
                        rs.getString("item_size"),
                        rs.getString("item_price")
                    );
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
   
    // Delete or remove from wishlist
    public boolean deleteWishList(String wishlistId) {
        String query = "DELETE FROM wishlist WHERE wishlist_id = ?";
        
        try (PreparedStatement pst = con.prepareStatement(query)) {
           
            pst.setString(1, wishlistId);
            
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
    
    // on purchase item, delete the wishlist with the same item id
    public void deleteWishlistOnPurchase(String item_id) {
        String query = "DELETE FROM wishlist WHERE item_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            
      
            pst.setString(1, item_id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
}