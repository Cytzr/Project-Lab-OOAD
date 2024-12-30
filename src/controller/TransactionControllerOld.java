package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.TransactionHistoryModelFactory;
import hybrid_model.TransactionHistoryModel;
import utilities.ConnectS;

public class TransactionControllerOld {

    private final ConnectS db;
    private final Connection con;
    private final WishlistController wishlistController = new WishlistController();

    // Constructor initializes a new instance of ConnectS
    public TransactionControllerOld() {
        this.db = new ConnectS();
        this.con = db.getConnection();
    }

    // Buyer makes a purchase of the item and creates a transaction
    public boolean PurchaseItems(String userId, String itemId) {
        String query = "INSERT INTO transaction(user_id, item_id) VALUES (?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userId);
            pst.setString(2, itemId);
            pst.executeUpdate();

            wishlistController.deleteWishlistOnPurchase(itemId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all the transactions that the buyer made
    public List<TransactionHistoryModel> ViewHistory(String userId) {
        System.out.println(userId);
        String query = "SELECT * FROM transaction JOIN item ON item.item_id = transaction.item_id WHERE transaction.user_id = ?";
        List<TransactionHistoryModel> items = new ArrayList<>();

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String transactionId = rs.getString("transaction.transaction_id");
                    String transactionUserId = rs.getString("transaction.user_id");
                    String itemId = rs.getString("transaction.item_id");
                    String itemName = rs.getString("item.item_name");
                    String itemCategory = rs.getString("item.item_category");
                    String itemSize = rs.getString("item.item_size");
                    String itemPrice = rs.getString("item.item_price");

                    TransactionHistoryModel item = TransactionHistoryModelFactory.createTransactionHistoryModel(
                        transactionId,
                        transactionUserId,
                        itemId,
                        itemName,
                        itemCategory,
                        itemSize,
                        itemPrice
                    );

                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    // Closing the database connection explicitly (optional but recommended)
    public void closeConnection() {
        db.closeConnection();
    }
}