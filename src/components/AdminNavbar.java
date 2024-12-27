package components;

import admin.ReviewItemPage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import seller.ShowAllSellerItemPage;

public class AdminNavbar {

	private static MenuBar menuBar;
	private static Menu navigation;
	private static MenuItem reviewItems;
	private static MenuItem logout;
	
	//returns menu bar for admin
	public static MenuBar getInstance(Stage stage) {
		String userId = "0";
		menuBar = new MenuBar();
		navigation = new Menu("Navigation");
		
		reviewItems = new MenuItem("Review Items");
		reviewItems.setOnAction(e -> new ReviewItemPage(stage));
		
		logout = new MenuItem("Logout");
		logout.setOnAction(e -> new LoginPage(stage));
		
		navigation.getItems().add(reviewItems);
		navigation.getItems().add(logout);
		
		menuBar.getMenus().add(navigation);

		return menuBar;
	}
}
