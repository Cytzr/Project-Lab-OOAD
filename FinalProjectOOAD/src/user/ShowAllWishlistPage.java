package user;

import java.util.List;

import components.UserNavbar;
import controller.WishlistController;
import hybrid_model.WishlistItemModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import utilities.AlertUtil;

public class ShowAllWishlistPage {
	
	//declare required components
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane1, borderPane2;
	private Label titleLabel, roleLabel;
	private HBox titleBox, roleBox;
	private VBox headerBox;
	
	TableView<WishlistItemModel> itemTable;
	WishlistController wishlistController = new WishlistController();
	private String userId;
	
	public ShowAllWishlistPage(Stage stage, String userId) {
		this.stage = stage;
		this.userId = userId;
		init();
		initTable();
		
		stage.setScene(scene);
		stage.setTitle("Wishlist");
		stage.show();
	}
	
	private void init() {
		//component initialize and placement
		titleLabel = new Label("Wishlist");
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
		
		itemTable = new TableView<WishlistItemModel>();
		borderPane2.setCenter(itemTable);
		
		borderPane1 = new BorderPane();
		borderPane1.setTop(UserNavbar.getInstance(stage, userId));
		borderPane1.setCenter(borderPane2);
		
		scene = new Scene(borderPane1, 800, 600);
	}
	
	//initialize table
	private void initTable() {
		TableColumn<WishlistItemModel, String> nameCol = new TableColumn<WishlistItemModel, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<WishlistItemModel, String>("item_name"));
		nameCol.setMinWidth(borderPane1.getWidth()/5.3);
		
		TableColumn<WishlistItemModel, String> categoryCol = new TableColumn<WishlistItemModel, String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<WishlistItemModel, String>("item_category"));
		categoryCol.setMinWidth(borderPane1.getWidth()/5.3);
		
		TableColumn<WishlistItemModel, String> sizeCol = new TableColumn<WishlistItemModel, String>("Size");
		sizeCol.setCellValueFactory(new PropertyValueFactory<WishlistItemModel, String>("item_size"));
		sizeCol.setMinWidth(borderPane1.getWidth()/5.3);
		
		TableColumn<WishlistItemModel, String> priceCol = new TableColumn<WishlistItemModel, String>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<WishlistItemModel, String>("item_price"));
		priceCol.setMinWidth(borderPane1.getWidth()/5.3);
		
		//declare column with multiple components
		TableColumn<WishlistItemModel, Void> buttonCol = new TableColumn<WishlistItemModel, Void>("Actions");
		buttonCol.setCellFactory(new Callback<>() {
		    @Override
		    public TableCell<WishlistItemModel, Void> call(TableColumn<WishlistItemModel, Void> param) {
		        return new TableCell<>() {
		        	//declare component
		            private final Button buttonDelete = new Button("Delete");
		            
		            //declare event handling for the button
		            {
		                buttonDelete.setOnAction(event -> {
		                	
		                	WishlistItemModel currentItem = getTableView().getItems().get(getIndex());
		                   boolean status = wishlistController.deleteWishList(currentItem.getWishlist_id());
		                   if (status) {
	        					AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Delete wishlist Success", "Wishlist has been deleted");
	        					new ShowAllWishlistPage(stage, userId);
	        				} else {
	        					AlertUtil.showAlert(Alert.AlertType.ERROR, "Delete wishlist Failed", "Something Went Wrong");
	        				}
	             	
		                });
		            }

		            //decides when will the button show up
		            @Override
		            protected void updateItem(Void item, boolean empty) {
		                super.updateItem(item, empty);
		                if (empty) {
		                    setGraphic(null);
		                } else {
		                    setGraphic(buttonDelete);
		                }
		            }
		        };
		    }
		});

		buttonCol.setMinWidth(borderPane1.getWidth()/4);
		
		itemTable.getColumns().addAll(nameCol, categoryCol, sizeCol, priceCol, buttonCol);
		
		//add item to the table based on the controller result
		List<WishlistItemModel> wishlistItems = wishlistController.ViewWishlist(userId);
		for (WishlistItemModel item : wishlistItems) { 
		    itemTable.getItems().add(item);
		}
	}

}
