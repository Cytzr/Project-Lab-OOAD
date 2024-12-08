package user;

import java.util.List;

import components.UserNavbar;
import controller.ItemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Item;

public class ShowAllBuyerItemPage{

	private Stage stage;
	private Scene scene;
	private BorderPane borderPane1, borderPane2;
	private Label titleLabel, roleLabel;
	private HBox titleBox, roleBox;
	private VBox headerBox;
	
	TableView<Item> itemTable;
	ItemController itemController = new ItemController();
	private String userId;
	public ShowAllBuyerItemPage(Stage stage, String userId) {
		this.stage = stage;
		this.userId = userId;
		init();
		initTable();
		
		stage.setScene(scene);
		stage.setTitle("Available Items");
		stage.show();
	}
	
	private void init() {
		titleLabel = new Label("Available Items");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        
		roleLabel = new Label("Role: Buyer");
		roleBox = new HBox(roleLabel);
		roleBox.setAlignment(Pos.CENTER);
		roleBox.setPadding(new Insets(0, 0, 10, 10));
		
		headerBox = new VBox(titleBox, roleBox);
		headerBox.setSpacing(10);
		
		borderPane2 = new BorderPane();
		borderPane2.setTop(headerBox);
		
		itemTable = new TableView<Item>();
		borderPane2.setCenter(itemTable);
		
		borderPane1 = new BorderPane();
		borderPane1.setTop(UserNavbar.getInstance(stage));
		borderPane1.setCenter(borderPane2);
		
		scene = new Scene(borderPane1, 800, 600);
	}
	
	private void initTable() {
		TableColumn<Item, String> nameCol = new TableColumn<Item, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("item_name"));
		nameCol.setMinWidth(borderPane1.getWidth()/5.3);
		
		TableColumn<Item, String> categoryCol = new TableColumn<Item, String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<Item, String>("item_category"));
		categoryCol.setMinWidth(borderPane1.getWidth()/5.3);
		
		TableColumn<Item, String> sizeCol = new TableColumn<Item, String>("Size");
		sizeCol.setCellValueFactory(new PropertyValueFactory<Item, String>("item_size"));
		sizeCol.setMinWidth(borderPane1.getWidth()/5.3);
		
		TableColumn<Item, String> priceCol = new TableColumn<Item, String>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<Item, String>("item_price"));
		priceCol.setMinWidth(borderPane1.getWidth()/5.3);
		
		TableColumn<Item, Void> buttonCol = new TableColumn<Item, Void>("Actions");
		buttonCol.setCellFactory(new Callback<>() {
		    @Override
		    public TableCell<Item, Void> call(TableColumn<Item, Void> param) {
		        return new TableCell<>() {
		            private final Button buttonPurchase = new Button("Purchase");
		            private final Button buttonOffer = new Button("Offer");
		            private final Button buttonWishlist = new Button("Wishlist");
		            private final HBox buttonContainer = new HBox(10, buttonPurchase, buttonOffer, buttonWishlist);
		            {
		                buttonPurchase.setOnAction(event -> {
		                    //Purchase logic
		                	
		                });
		                buttonOffer.setOnAction(event -> {
		                	//Make offer logic
		                	Item currentItem = getTableView().getItems().get(getIndex());
		                	new MakeOfferPage(stage, currentItem, userId);
		                });
		                buttonWishlist.setOnAction(event -> {
		                    //Wishlist logic

		                });
		            }

		            @Override
		            protected void updateItem(Void item, boolean empty) {
		                super.updateItem(item, empty);
		                if (empty) {
		                    setGraphic(null);
		                } else {
		                    setGraphic(buttonContainer);
		                }
		            }
		        };
		    }
		});

		buttonCol.setMinWidth(borderPane1.getWidth()/4);
		
		itemTable.getColumns().addAll(nameCol, categoryCol, sizeCol, priceCol, buttonCol);
		
		List<Item> sellerItems = itemController.getSellerItem(userId);
		for (Item item : sellerItems) { 
		    itemTable.getItems().add(item);
		}
	}
}
