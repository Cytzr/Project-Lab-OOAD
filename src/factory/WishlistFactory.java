package factory;

import hybrid_model.WishlistItemModel;

public class WishlistFactory {
    public static WishlistItemModel createWishlistItemModel(
        String wishlistId,
        String itemId,
        String userId,
        String itemName,
        String itemCategory,
        String itemSize,
        String itemPrice
    ) {
        return new WishlistItemModel(
            wishlistId,
            itemId,
            userId,
            itemName,
            itemCategory,
            itemSize,
            itemPrice
        );
    }
}

