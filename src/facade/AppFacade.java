package facade;

import java.util.List;
import java.util.Map;

import admin.ReviewItemPage;
import controller.ItemController;
import controller.OfferController;
import controller.TransactionController;
import controller.UserController;
import controller.WishlistController;
import hybrid_model.OfferTableModel;
import hybrid_model.TransactionHistoryModel;
import hybrid_model.WishlistItemModel;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Item;
import model.Offer;
import seller.ShowAllSellerItemPage;
import seller.ShowOfferedItemPage;
import user.ShowAllBuyerItemPage;
import user.ShowAllWishlistPage;
import utilities.AlertUtil;

public class AppFacade {
	private ItemController itemController = new ItemController();
	private OfferController offerController = new OfferController();
	private TransactionController transactionController = new TransactionController();
	private UserController userController = new UserController();
	private WishlistController wishlistController = new WishlistController();
	private Stage stage;
	public AppFacade(Stage stage) {
		this.stage = stage;
	}
	
	
	public void adminDeclineItem(String itemId, String reason) {
		try {
			boolean status = itemController.declineItem(itemId, reason);
			 if (status) {
         	   AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Decline Successful", "Item has been declined");
         	   new ReviewItemPage(stage);
            } else {
            	AlertUtil.showAlert(Alert.AlertType.ERROR, "Decline Failed", "Something Went Wrong!");
         	}
		} catch (Exception e) {
			AlertUtil.showAlert(Alert.AlertType.ERROR, "Decline Failed", "Something Went Wrong!");
		}
		return;
	}
	
	public void adminApproveItem(String itemId) {
		try {
			boolean status = itemController.approveItem(itemId);
           
              if (status) {
              	   AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Approve Successful", "Item has been approved");
              	   new ReviewItemPage(stage);
                 } else {
              	   AlertUtil.showAlert(Alert.AlertType.ERROR, "Approve Failed", "Something Went Wrong!");
                 }
		} catch (Exception e) {
			  AlertUtil.showAlert(Alert.AlertType.ERROR, "Approve Failed", "Something Went Wrong!");
		}
	}
	
	public List<Item> getRequestedItemList(){
		try {
			List<Item> approvalItems = itemController.ViewRequestedItem();
			
			return approvalItems;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void login(String username, String password){
			try {
			  Map<String, String> user = userController.login(username, password);
		        if (user != null) {
		            String userId = user.get("userId");
		            String role = user.get("role");
		            AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
		            
		            if(role.equals("Seller")) {

		    			new ShowAllSellerItemPage(stage, userId);
		    		
		    		} else {
		    			new ShowAllBuyerItemPage(stage, userId);
		    		}
		            return;
		        } else {
		            AlertUtil.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials.");
		            return;
		        }
			}catch (Exception e) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Login Failed", "Something went wrong");
	            return;
			}
	}
	
	public void register(String username, String pass, String phone, String address, String role) {
		try {
			
	        boolean status = userController.createUser(username, pass, phone, address, role);
	      
	        if (status == true) {
	        	 AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Welcome, " + username + "! Your role is " + role + ".");
	        } else {
	        	 AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Something Went Wrong or User Already Exist");
	        }
		} catch (Exception e) {
			AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Something Went Wrong or User Already Exist");
		}
		return;
	}
	
	public void createItem(String name, String size, int price, String category, String userId) {
		try {
			
			boolean status = itemController.uploadItem(name, size, price, category, userId);
		
			if (status == true) {
				AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Upload Successful", "Item has been uploaded");
				
				new ShowAllSellerItemPage(stage, userId);
			} else {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Upload Failed", "Something Went Wrong!");
				return;
			}
		} catch (Exception e) {
			AlertUtil.showAlert(Alert.AlertType.ERROR, "Upload Failed", "Something Went Wrong!");
		}
		return;
	}
	
	public void editItem(String itemId, String name, String size, int price, String category, String userId) {
		try {
		
			boolean status = itemController.editItem(itemId, name, size, price, category);
		
			if (status) {
				AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Edit Successful", "Item has been edited");
				new ShowAllSellerItemPage(stage, userId);
			} else {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Edit Failed", "Something Went Wrong!");
				return;
			}
			
		} catch (Exception e) {
			AlertUtil.showAlert(Alert.AlertType.ERROR, "Edit Failed", "Something Went Wrong!");
		}
		return;
	}
	
	public void rejectOffer(String offerId, String userId) {
		try {
			boolean status = offerController.DeclineOffer(offerId);
			if (status == true) {
				AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Offered Declined", "Offer has been declined");
				new ShowOfferedItemPage(stage, userId);
			} else {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Offered Declined Failed", "Something Went Wrong!");
				return;
			}
		} catch (Exception e) {
			AlertUtil.showAlert(Alert.AlertType.ERROR, "Offered Declined Failed", "Something Went Wrong!");
		}
		return;
	}
	
	public void deleteItem(String itemId, String userId) {
		try {
			boolean status = itemController.deleteItem(itemId);
            if (status) {
         	   AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Delete Successful", "Item has been deleted");
         	   new ShowAllSellerItemPage(stage, userId);
            } else {
         	   AlertUtil.showAlert(Alert.AlertType.ERROR, "Delete Failed", "Something Went Wrong, Check if the item is approved by admin or not");
         	   return;
            }
           
		} catch (Exception e) {
			 AlertUtil.showAlert(Alert.AlertType.ERROR, "Delete Failed", "Something Went Wrong, Check if the item is approved by admin or not");
		}
		return;
	}
	
	public List<Item> getSellerItem(String userId){
		try {
			List<Item> sellerItems = itemController.getSellerItem(userId);
			return sellerItems;
		} catch (Exception e) {
			return null;
		}
	}
	public List<OfferTableModel> getOfferedItemList(String userId){
		try {
			List<OfferTableModel> offerItems = offerController.ViewOfferedItemSeller(userId);
			return offerItems;
		} catch (Exception e) {
			return null;
		}
	}
	public void acceptOffer(String offerId, String itemId, String userId, String offerUser) {
		try {
			boolean status = offerController.AcceptOffer(offerId, itemId);
        	if (status == true) {
				AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Offered Accepted", "Offer has been accepted");
				this.purchaseItem(offerUser, itemId);
				new ShowOfferedItemPage(stage, userId);
			} else {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Offered Accepted Failed", "Something Went Wrong!");
				return;
			}
		} catch (Exception e) {
			
		}
	}
	
	public void purchaseItem(String userId, String itemId) {
		try {
			boolean status = transactionController.PurchaseItems(userId, itemId);
    		if (status) {
				AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Purchased Success", "Purchase has been made");
				new ShowAllBuyerItemPage(stage, userId);
			} else {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Purchase Failed", "Something Went Wrong!");
			}
		} catch (Exception e) {
			AlertUtil.showAlert(Alert.AlertType.ERROR, "Purchase Failed", "Something Went Wrong!");
		}
		return;
	}

	
	public void makeOffer(String itemId, int finalPrice,String userId) {
		try {
			Offer highestOffer = offerController.getHighestOffer(itemId);
		
			if (highestOffer != null && finalPrice <= highestOffer.getOffer_price()) {
			    AlertUtil.showAlert(Alert.AlertType.ERROR, "Offer Failed",
			            "Offer price must be larger than the current offer price, which is " + highestOffer.getOffer_price());
			    return;
			}
			boolean status = offerController.offerPrice(itemId, finalPrice, userId);
			if (status) {
				AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Offer Success", "Offer has been made");
				new ShowAllBuyerItemPage(stage, userId);
			} else {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Offer Failed", "Something Went Wrong");
				return;
			}
		} catch (Exception e) {
			AlertUtil.showAlert(Alert.AlertType.ERROR, "Offer Failed", "Something Went Wrong");
		}
		return;
	}
	
	public List<Item> viewItem() {
		try {
			List<Item> buyerItems = itemController.ViewItem();
			return buyerItems;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void addToWishList(String itemId, String userId) {
		try {
			boolean status = wishlistController.addWishListItem(itemId, userId);
        	if (status) {
				AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Wishlist Add Success", "Item has been added to wishlist");
				new ShowAllBuyerItemPage(stage, userId);
			} else {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Wishlist Add Failed", "Something Went Wrong!");
			}
		} catch (Exception e) {
			AlertUtil.showAlert(Alert.AlertType.ERROR, "Wishlist Add Failed", "Something Went Wrong!");
		}
		return;
	}
	
	public List<TransactionHistoryModel> viewTransactionHistory(String userId) {
		try {
			List<TransactionHistoryModel> transactions = transactionController.ViewHistory(userId);
			return transactions;
		}catch (Exception e) {
			return null;
		}
	}
	
	public List<WishlistItemModel> viewWishList(String userId){
		try {
			List<WishlistItemModel> wishlistItems = wishlistController.ViewWishlist(userId);
			return wishlistItems;
		} catch (Exception e) {	
			return null;
		}
	}
	
	public void deleteWishlist(String wishlistId, String userId) {
		try {
			 boolean status = wishlistController.deleteWishList(wishlistId);
             if (status) {
					AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Delete wishlist Success", "Wishlist has been deleted");
					new ShowAllWishlistPage(stage, userId);
				} else {
					AlertUtil.showAlert(Alert.AlertType.ERROR, "Delete wishlist Failed", "Something Went Wrong");
				}
		} catch (Exception e) {
			AlertUtil.showAlert(Alert.AlertType.ERROR, "Delete wishlist Failed", "Something Went Wrong");
		}
		return;
	}
}
