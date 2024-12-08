package user;

import java.util.List;

import components.UserNavbar;
import controller.TransactionController;
import hybrid_model.OfferTableModel;
import hybrid_model.TransactionHistoryModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Item;

public class ShowAllTransactionHistoryPage {
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane1, borderPane2;
	private Label titleLabel, roleLabel;
	private HBox titleBox, roleBox;
	private VBox headerBox;
	
	TableView<TransactionHistoryModel> itemTable;
	private String userId;
	TransactionController transactionController = new TransactionController();
	public ShowAllTransactionHistoryPage(Stage stage, String userId) {
		this.stage = stage;
		this.userId = userId;
		init();
		initTable();
		
		stage.setScene(scene);
		stage.setTitle("Transaction History");
		stage.show();
	}
	
	private void init() {
		titleLabel = new Label("Transaction History");
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
		
		itemTable = new TableView<TransactionHistoryModel>();
		borderPane2.setCenter(itemTable);
		
		borderPane1 = new BorderPane();
		borderPane1.setTop(UserNavbar.getInstance(stage, userId));
		borderPane1.setCenter(borderPane2);
		
		scene = new Scene(borderPane1, 800, 600);
	}
	
	private void initTable() {
		
		TableColumn<TransactionHistoryModel, String> idCol = new TableColumn<TransactionHistoryModel, String>("Name");
		idCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryModel, String>("transaction_id"));
		idCol.setMinWidth(borderPane1.getWidth()/6);
		
		TableColumn<TransactionHistoryModel, String> nameCol = new TableColumn<TransactionHistoryModel, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryModel, String>("item_name"));
		nameCol.setMinWidth(borderPane1.getWidth()/4);
		
		TableColumn<TransactionHistoryModel, String> categoryCol = new TableColumn<TransactionHistoryModel, String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryModel, String>("item_category"));
		categoryCol.setMinWidth(borderPane1.getWidth()/4);
		
		TableColumn<TransactionHistoryModel, String> sizeCol = new TableColumn<TransactionHistoryModel, String>("Size");
		sizeCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryModel, String>("item_size"));
		sizeCol.setMinWidth(borderPane1.getWidth()/4);
		
		TableColumn<TransactionHistoryModel, String> priceCol = new TableColumn<TransactionHistoryModel, String>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<TransactionHistoryModel, String>("item_price"));
		priceCol.setMinWidth(borderPane1.getWidth()/4);
		
		itemTable.getColumns().addAll(idCol, nameCol, categoryCol, sizeCol, priceCol);
		
		List<TransactionHistoryModel> transactions = transactionController.ViewHistory(userId);
		for (TransactionHistoryModel item : transactions) { 
		    itemTable.getItems().add(item);
		}
	}
}
