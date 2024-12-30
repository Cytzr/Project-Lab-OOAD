package factory;

import model.Item;

public class ItemFactory {
    public static Item createItem(
        String itemId, 
        String itemName, 
        String itemSize, 
        String itemPrice, 
        String itemCategory, 
        int itemStatus, 
        String itemWishlist, 
        String itemOfferStatus, 
        String userId
    ) {
        // Convert itemStatus to the appropriate string
        String status = itemStatus == 1 ? "Accepted" : (itemStatus != 0 ? "Rejected" : "Pending");
        
        // Create and return the Item object
        return new Item(
            itemId, 
            itemName, 
            itemSize, 
            itemPrice, 
            itemCategory, 
            status, 
            itemWishlist, 
            itemOfferStatus, 
            userId
        );
    }
}
