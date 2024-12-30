package factory;

import model.Offer;

public class OfferFactory {
    public static Offer createOffer(
        String offerId,
        String userId,
        String itemId,
        int offeredPrice,
        String status,
        int itemPrice,
        String itemName
    ) {
        return new Offer(
            offerId,
            userId,
            itemId,
            offeredPrice,
            status,
            itemPrice,
            itemName
        );
    }
}
