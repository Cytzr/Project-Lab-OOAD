package factory;

import hybrid_model.OfferTableModel;

public class OfferTableFactory {
    public static OfferTableModel createOfferTableModel(
        String offerId,
        String userId,
        String itemId,
        String itemName,
        String itemSize,
        int itemPrice,
        int offeredPrice,
        String itemCategory,
        String status,
        String username
    ) {
        return new OfferTableModel(
            offerId,
            userId,
            itemId,
            itemName,
            itemSize,
            itemPrice,
            offeredPrice,
            itemCategory,
            status,
            username
        );
    }
}
