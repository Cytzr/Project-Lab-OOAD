package components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import seller.ShowAllSellerItemPage;
import seller.ShowOfferedItemPage;

public class SellerNavbar {
	private static String userId;
    private static MenuBar menuBar;
    private static Menu navigation;
    private static MenuItem viewAllItems, viewOfferedItems;
    private static MenuItem logout;
    public SellerNavbar(String userId) {
    	SellerNavbar.userId = userId;
    }
    
    //returns navbar for seller
    public static MenuBar getInstance(Stage stage, String user_id) {
        menuBar = new MenuBar();
        
        navigation = new Menu("Navigation");
        
        viewAllItems = new MenuItem("My Items");
        viewAllItems.setOnAction(e -> new ShowAllSellerItemPage(stage, user_id));
        
        viewOfferedItems = new MenuItem("View Offered Items");
        viewOfferedItems.setOnAction(e -> {
        	new ShowOfferedItemPage(stage, user_id);
        });
        logout = new MenuItem("Logout");
		logout.setOnAction(e -> new LoginPage(stage));
        navigation.getItems().add(viewAllItems);
        navigation.getItems().add(viewOfferedItems);
        menuBar.getMenus().add(navigation);
        navigation.getItems().add(logout);
		
        return menuBar;
    }
}
