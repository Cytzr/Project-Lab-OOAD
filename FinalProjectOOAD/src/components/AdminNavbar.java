package components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import seller.ShowAllSellerItemPage;

public class AdminNavbar {

	private static MenuBar menuBar;
	private static Menu navigation;
	private static MenuItem reviewItems;
	
	public static MenuBar getInstance(Stage stage) {
		menuBar = new MenuBar();
		navigation = new Menu("Navigation");
		
		reviewItems = new MenuItem("Review Items");
		reviewItems.setOnAction(e -> new ShowAllSellerItemPage(stage));
		
		navigation.getItems().add(reviewItems);
		
		menuBar.getMenus().add(navigation);

		return menuBar;
	}
}
