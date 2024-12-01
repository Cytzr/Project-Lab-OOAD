package components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import seller.ShowAllSellerItemPage;
import seller.ShowOfferedItemPage;

public class SellerNavbar {
    private static MenuBar menuBar;
    private static Menu navigation;
    private static MenuItem viewAllItems, viewOfferedItems;

    public static MenuBar getInstance(Stage stage) {
        menuBar = new MenuBar();
        
        navigation = new Menu("Navigation");
        
        viewAllItems = new MenuItem("My Items");
        viewAllItems.setOnAction(e -> new ShowAllSellerItemPage(stage));
        
        viewOfferedItems = new MenuItem("View Offered Items");
        viewOfferedItems.setOnAction(e -> {
        	new ShowOfferedItemPage(stage);
        });
        
        navigation.getItems().add(viewAllItems);
        navigation.getItems().add(viewOfferedItems);
        menuBar.getMenus().add(navigation);
        
        return menuBar;
    }
}
