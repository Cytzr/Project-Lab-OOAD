package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.OfferFactory;
import factory.OfferTableFactory;
import hybrid_model.OfferTableModel;
import model.Item;
import model.Offer;
import utilities.Connect;

public class OfferController {

    private final Connect db;
    private final Connection con;
    ItemController itemController = new ItemController();
    public OfferController() {
        this.db = Connect.getInstance();
        this.con = db.getConnection();
    }
//    Buyer make price offer for the item
    public boolean offerPrice(String item_id, int offered_price, String userId) {
        String query = "INSERT INTO offer(item_id, user_id, offered_price) VALUES (?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, item_id);
            pst.setString(2, userId);
            pst.setInt(3, offered_price);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//    Get all seller's product that is offered by buyer
    public List<OfferTableModel> ViewOfferedItemSeller(String userId) {
    	System.out.println(userId);
        String query = "SELECT * FROM offer JOIN item on item.item_id = offer.item_id JOIN user on user.user_id = offer.user_id WHERE item.user_id = ? AND item.item_status = 1 AND offer.status IS NULL";
        List<OfferTableModel> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userId);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                	String offerId = rs.getString("offer_id");
                    String offerUserId = rs.getString("offer.user_id");
                    String itemId = rs.getString("item.item_id");
                    String itemName = rs.getString("item.item_name");
                    String itemSize = rs.getString("item.item_size");
                    int itemPrice = rs.getInt("item.item_price");
                    int offeredPrice = rs.getInt("offer.offered_price");
                    String itemCategory = rs.getString("item.item_category");
                    String status = rs.getString("offer.status");
                    String username = rs.getString("user.username");

                    OfferTableModel item = OfferTableFactory.createOfferTableModel(
                        offerId,
                        offerUserId,
                        itemId,
                        itemName,
                        itemSize,
                        itemPrice,
                        offeredPrice,
                        itemCategory,
                        status,
                        username
                    );

                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    //Seller accept offer and set status to 1
    public boolean AcceptOffer(String offerId, String itemId) {
        String query = "UPDATE offer SET status = 1 WHERE offer_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
          
        	pst.setString(1, offerId);
           
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
    //Seller reject offer and delete the offer from database
    public boolean DeclineOffer(String offerId) {
        String query = "DELETE FROM offer WHERE offer_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
          
        	pst.setString(1, offerId);
           
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
     // get all the offer that the user made by userId  
    public List<Offer> userOfferList(String userId) {
        String query = "SELECT * FROM offer JOIN item on item.item_id = offer.item_id WHERE user_id = 1";
        List<Offer> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {    
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) { 
                   
                	 String offerId = rs.getString("offer_id");
                     String offerUserId = rs.getString("offer.user_id");
                     String itemId = rs.getString("item.item_id");
                     int offeredPrice = rs.getInt("offer.offered_price");
                     String status = rs.getString("offer.status");
                     int itemPrice = rs.getInt("item.item_price");
                     String itemName = rs.getString("item.item_name");

                     Offer item = OfferFactory.createOffer(
                         offerId,
                         offerUserId,
                         itemId,
                         offeredPrice,
                         status,
                         itemPrice,
                         itemName
                     );

                     items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    
    //get the highest offer of the item
    public Offer getHighestOffer(String itemId) {
        String query = "SELECT offer.offer_id, offer.user_id, offer.item_id, offer.offered_price, offer.status, " +
                       "item.item_price, item.item_name " +
                       "FROM offer " +
                       "JOIN item ON item.item_id = offer.item_id " +
                       "WHERE offer.item_id = ? " +
                       "AND offer.offered_price = (SELECT MAX(offered_price) FROM offer WHERE item_id = ?)"
                       + "LIMIT 1";
    

        Offer highestOffer = null;

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, itemId);
            pst.setString(2, itemId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String offerId = rs.getString("offer_id");
                    String offerUserId = rs.getString("offer.user_id");
                    String itemIds = rs.getString("item.item_id");
                    int offeredPrice = rs.getInt("offer.offered_price");
                    String status = rs.getString("offer.status");
                    int itemPrice = rs.getInt("item.item_price");
                    String itemName = rs.getString("item.item_name");

                    highestOffer = OfferFactory.createOffer(
                        offerId,
                        offerUserId,
                        itemIds,
                        offeredPrice,
                        status,
                        itemPrice,
                        itemName
                    ); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return highestOffer;
    }
    
}