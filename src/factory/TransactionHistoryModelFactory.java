package factory;

import hybrid_model.TransactionHistoryModel;

public class TransactionHistoryModelFactory {
    public static TransactionHistoryModel createTransactionHistoryModel(
        String transactionId,
        String userId,
        String itemId,
        String itemName,
        String itemCategory,
        String itemSize,
        String itemPrice
    ) {
        return new TransactionHistoryModel(
            transactionId,
            userId,
            itemId,
            itemName,
            itemCategory,
            itemSize,
            itemPrice
        );
    }
}
