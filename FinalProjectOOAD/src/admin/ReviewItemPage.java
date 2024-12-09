package admin;

import java.util.List;

import components.AdminNavbar;
import components.UserNavbar;
import controller.ItemController;
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
import user.ShowAllWishlistPage;
import utilities.AlertUtil;

public class ReviewItemPage {
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane1, borderPane2;
	private Label titleLabel, roleLabel;
	private HBox titleBox, roleBox;
	private VBox headerBox;
	ItemController itemController = new ItemController();
	TableView<Item> itemTable;
	
	public ReviewItemPage(Stage stage) {
		this.stage = stage;
		
		init();
		initTable();
		
		stage.setScene(scene);
		stage.setTitle("Review Items");
		stage.show();
	}
	
	private void init() {
		titleLabel = new Label("Review Items");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        
		roleLabel = new Label("Role: Admin");
		roleBox = new HBox(roleLabel);
		roleBox.setAlignment(Pos.CENTER);
		roleBox.setPadding(new Insets(0, 0, 10, 10));
		
		headerBox = new VBox(titleBox, roleBox);
		headerBox.setSpacing(10);
		
		borderPane2 = new BorderPane();
		borderPane2.setTop(headerBox);
		
		itemTable = new TableView<Item>();
		borderPane2.setCenter(itemTable);
		
		new AdminNavbar();
		borderPane1 = new BorderPane();
		borderPane1.setTop(AdminNavbar.getInstance(stage));
		borderPane1.setCenter(borderPane2);
		
		scene = new Scene(borderPane1, 800, 600);
	}
	
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
		priceCol.setMinWidth(borderPane1.getWidth()/5);
		
		TableColumn<Item, Void> buttonCol = new TableColumn<Item, Void>("Actions");
		buttonCol.setCellFactory(new Callback<>() {
		    @Override
		    public TableCell<Item, Void> call(TableColumn<Item, Void> param) {
		        return new TableCell<>() {
		            private final Button buttonAccept = new Button("Accept");
		            private final Button buttonReject = new Button("Reject");
		            private final HBox buttonContainer = new HBox(10, buttonAccept, buttonReject);
		            {
		                buttonAccept.setOnAction(event -> {
		                	Item currentItem = getTableView().getItems().get(getIndex());
		                    boolean status = itemController.approveItem(currentItem.getItem_id());
		                    if (status) {
			                	   AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Approve Successful", "Item has been approved");
			                	   new ReviewItemPage(stage);
			                   } else {
			                	   AlertUtil.showAlert(Alert.AlertType.ERROR, "Approve Failed", "Something Went Wrong!");
			                   }
		                   
		                });
		                buttonReject.setOnAction(event -> {
		                
		                	Item currentItem = getTableView().getItems().get(getIndex());
		                	new RejectItemPage(stage, currentItem);
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

		buttonCol.setMinWidth(borderPane1.getWidth()/5);
		
		itemTable.getColumns().addAll(nameCol, categoryCol, sizeCol, priceCol, buttonCol);
		
		List<Item> approvalItems = itemController.ViewRequestedItem();
		for (Item item : approvalItems) { 
		    itemTable.getItems().add(item);
		}
	}
}
