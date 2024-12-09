package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hybrid_model.OfferTableModel;
import hybrid_model.TransactionHistoryModel;
import model.Item;
import model.Offer;
import model.Transaction;
import utilities.Connect;

public class TransactionController {

    private final Connect db;
    private final Connection con;
    WishlistController wishlistController = new WishlistController();
    public TransactionController() {
        this.db = Connect.getInstance();
        this.con = db.getConnection();
    }

    public boolean PurchaseItems(String user_id, String item_id) {
        String query = "INSERT INTO transaction(user_id, item_id) VALUES (?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, user_id);
            pst.setString(2, item_id);
            pst.executeUpdate();
            
            wishlistController.deleteWishlistOnPurchase(user_id, item_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<TransactionHistoryModel> ViewHistory(String userId) {
    	System.out.println(userId);
        String query = "SELECT * FROM transaction JOIN item on item.item_id = transaction.item_id WHERE transaction.user_id = ?";
        List<TransactionHistoryModel> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userId);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                   TransactionHistoryModel item = new TransactionHistoryModel (	   
        		   rs.getString("transaction.transaction_id"),
         		   rs.getString("transaction.user_id"),
         		   rs.getString("transaction.item_id"),
         		  rs.getString("item.item_name"),
         		  rs.getString("item.item_category"),
         		 rs.getString("item.item_size"),
         		   rs.getString("item.item_price")
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