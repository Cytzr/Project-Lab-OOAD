package seller;

import java.util.List;
import java.util.Map;

import components.SellerNavbar;
import controller.ItemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class ShowAllSellerItemPage implements EventHandler<ActionEvent> {
	//declare required components
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane1, borderPane2;
	private Label titleLabel, roleLabel;
	private Button uploadButton;
	private HBox titleBox, roleBox, buttonBox;
	private VBox headerBox;
	private String userId;
	TableView<Item> itemTable;
	ItemController itemController = new ItemController();
	
	public ShowAllSellerItemPage(Stage stage, String userId) {
		this.stage = stage;
		this.userId = userId;
		init();
		initTable();
		handleEvent();
		
		stage.setScene(scene);
		stage.setTitle("My Items");
		stage.show();
	}
	
	private void init() {
		//component initialize and placement
		titleLabel = new Label("My Items");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        
		roleLabel = new Label("Role: Seller");
		roleBox = new HBox(roleLabel);
		roleBox.setAlignment(Pos.CENTER);
		
		uploadButton = new Button("Upload Item");
		buttonBox = new HBox(uploadButton);
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.BASELINE_LEFT);
		buttonBox.setPadding(new Insets(0, 0, 10, 10));
		
		headerBox = new VBox(titleBox, roleBox, buttonBox);
		headerBox.setSpacing(10);
		
		borderPane2 = new BorderPane();
		borderPane2.setTop(headerBox);
		
		itemTable = new TableView<Item>();
		borderPane2.setCenter(itemTable);
		
		borderPane1 = new BorderPane();
		borderPane1.setTop(SellerNavbar.getInstance(stage, userId));
		borderPane1.setCenter(borderPane2);
		
		scene = new Scene(borderPane1, 1000, 600);
	}
	
	//create table based on model
	private void initTable() {
		TableColumn<Item, String> nameCol = new TableColumn<Item, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("item_name"));
		nameCol.setMinWidth(borderPane1.getWidth()/5);
		
		TableColumn<Item, String> categoryCol = new TableColumn<Item, String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<Item, String>("item_category"));
		categoryCol.setMinWidth(borderPane1.getWidth()/5);
		
		TableColumn<Item, String> sizeCol = new TableColumn<Item, String>("Size");
		sizeCol.setCellValueFactory(new PropertyValueFactory<Item, String>("item_size"));
		sizeCol.setMinWidth(borderPane1.getWidth()/5);
		
		TableColumn<Item, String> priceCol = new TableColumn<Item, String>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<Item, String>("item_price"));
		priceCol.setMinWidth(borderPane1.getWidth()/7);
		
		TableColumn<Item, String> approveCol = new TableColumn<Item, String>("Status");
		approveCol.setCellValueFactory(new PropertyValueFactory<Item, String>("item_status"));
		approveCol.setMinWidth(borderPane1.getWidth()/15);
		
		//create a table column to fit buttons
		TableColumn<Item, Void> buttonCol = new TableColumn<Item, Void>("Actions");
		buttonCol.setCellFactory(new Callback<>() {
		    @Override
		    public TableCell<Item, Void> call(TableColumn<Item, Void> param) {
		        return new TableCell<>() {
		        	//declare buttons and container
		            private final Button buttonEdit = new Button("Detail");
		            private final Button buttonDelete = new Button("Delete");
		            private final HBox buttonContainer = new HBox(10, buttonEdit, buttonDelete);
		            
		            //declare functions for the buttons
		            {
		                buttonEdit.setOnAction(event -> {
		                    Item currentItem = getTableView().getItems().get(getIndex());
		                    //Navigate to view page
		                    new ViewSellerItemPage(stage, currentItem, userId);
		                });

		                buttonDelete.setOnAction(event -> {
		                    Item currentItem = getTableView().getItems().get(getIndex());
		                    //Call itemController to delete the item from db
		                   boolean status = itemController.deleteItem(currentItem.getItem_id());
		                   if (status) {
		                	   AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Delete Successful", "Item has been deleted");
		                   } else {
		                	   AlertUtil.showAlert(Alert.AlertType.ERROR, "Delete Failed", "Something Went Wrong, Check if the item is approved by admin or not");
		                   }
		                   new ShowAllSellerItemPage(stage, userId);
		                });
		            }

		            //decides when to show or not show the buttons
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

		buttonCol.setMinWidth(borderPane1.getWidth()/5);
		
		
		itemTable.getColumns().addAll(nameCol, categoryCol, sizeCol, priceCol, approveCol, buttonCol);
		
		//Get all the item for the user (seller)
		List<Item> sellerItems = itemController.getSellerItem(userId);
		for (Item item : sellerItems) { 
		    itemTable.getItems().add(item);
		}
	}

	//event handling
	public void handleEvent() {
		uploadButton.setOnAction(this);
	}
	
	@Override
	public void handle(ActionEvent event) {
		//navigates seller to upload page
		if(event.getSource() == uploadButton) {
			new CreateItemPage(stage, userId);
		}
	}
}
