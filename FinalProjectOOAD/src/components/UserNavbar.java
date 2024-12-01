package components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import user.ShowAllBuyerItemPage;
import user.ShowAllTransactionHistoryPage;
import user.ShowAllWishlistPage;

public class UserNavbar {
	private static MenuBar menuBar;
	private static Menu navigation;
	private static MenuItem availableItems, wishlistItems, transactionItems;
	
	public static MenuBar getInstance(Stage stage) {
		menuBar = new MenuBar();
		
		navigation = new Menu("Navigation");
		availableItems = new MenuItem("View Available Items");
		availableItems.setOnAction(e -> new ShowAllBuyerItemPage(stage));
		
		wishlistItems = new MenuItem("View Wishlist");
		wishlistItems.setOnAction(e -> new ShowAllWishlistPage(stage));
		
		transactionItems = new MenuItem("Transaction History");
		transactionItems.setOnAction(e -> new ShowAllTransactionHistoryPage(stage));
		
		navigation.getItems().add(availableItems);
		navigation.getItems().add(wishlistItems);
		navigation.getItems().add(transactionItems);
		
		menuBar.getMenus().add(navigation);
		
		return menuBar;
	}
}
