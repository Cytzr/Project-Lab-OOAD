package seller;

import java.util.List;

import admin.RejectItemPage;
import components.SellerNavbar;
import controller.OfferController;
import hybrid_model.OfferTableModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.Offer;

public class ShowOfferedItemPage implements EventHandler<ActionEvent> {
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane1, borderPane2;
	private Label titleLabel, roleLabel;
	private HBox titleBox, roleBox;
	private VBox headerBox;
	OfferController offerController = new OfferController();
	TableView<OfferTableModel> itemTable;
	private String userId;
	public ShowOfferedItemPage(Stage stage, String userId) {
		this.stage = stage;
		this.userId = userId;
		init();
		initTable();
		handleEvent();
		
		stage.setScene(scene);
		stage.setTitle("Offered Items");
		stage.show();
	}
	
	private void init() {
		titleLabel = new Label("Offered Items");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        
		roleLabel = new Label("Role: Seller");
		roleBox = new HBox(roleLabel);
		roleBox.setAlignment(Pos.CENTER);
		roleBox.setPadding(new Insets(0, 0, 10, 10));
		
		headerBox = new VBox(titleBox, roleBox);
		headerBox.setSpacing(10);
		
		borderPane2 = new BorderPane();
		borderPane2.setTop(headerBox);
		
		itemTable = new TableView<OfferTableModel>();
		borderPane2.setCenter(itemTable);
		
		borderPane1 = new BorderPane();
		borderPane1.setTop(SellerNavbar.getInstance(stage, userId));
		borderPane1.setCenter(borderPane2);
		
		scene = new Scene(borderPane1, 800, 600);
	}
	
	private void initTable() {
		TableColumn<OfferTableModel, String> nameCol = new TableColumn<OfferTableModel, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<OfferTableModel, String>("item_name"));
		nameCol.setMinWidth(borderPane1.getWidth()/6);
		
		TableColumn<OfferTableModel, String> categoryCol = new TableColumn<OfferTableModel, String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<OfferTableModel, String>("item_category"));
		categoryCol.setMinWidth(borderPane1.getWidth()/6);
		
		TableColumn<OfferTableModel, String> sizeCol = new TableColumn<OfferTableModel, String>("Size");
		sizeCol.setCellValueFactory(new PropertyValueFactory<OfferTableModel, String>("item_size"));
		sizeCol.setMinWidth(borderPane1.getWidth()/6);
		
		TableColumn<OfferTableModel, String> priceCol = new TableColumn<OfferTableModel, String>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<OfferTableModel, String>("item_price"));
		priceCol.setMinWidth(borderPane1.getWidth()/6);
		
		TableColumn<OfferTableModel, String> offerCol = new TableColumn<OfferTableModel, String>("Offered Price");
		offerCol.setCellValueFactory(new PropertyValueFactory<>("offer_price"));
		offerCol.setMinWidth(borderPane1.getWidth()/6);
		
		TableColumn<OfferTableModel, Void> buttonCol = new TableColumn<OfferTableModel, Void>("Actions");
		buttonCol.setCellFactory(new Callback<>() {
		    @Override
		    public TableCell<OfferTableModel, Void> call(TableColumn<OfferTableModel, Void> param) {
		        return new TableCell<>() {
		            private final Button buttonAccept = new Button("Accept");
		            private final Button buttonReject = new Button("Reject");
		            private final HBox buttonContainer = new HBox(10, buttonAccept, buttonReject);
		            {
		                buttonAccept.setOnAction(event -> {
		                	OfferTableModel currentItem = getTableView().getItems().get(getIndex());
		                	//Accept Logic
		                });

		                buttonReject.setOnAction(event -> {
		                	OfferTableModel currentItem = getTableView().getItems().get(getIndex());
		                	new RejectItemSellerPage(stage, currentItem, userId);
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

		buttonCol.setMinWidth(borderPane1.getWidth()/6);
		
		itemTable.getColumns().addAll(nameCol, categoryCol, sizeCol, priceCol, offerCol, buttonCol);
		
		List<OfferTableModel> offerItems = offerController.ViewOfferedItemSeller(userId);
		for (OfferTableModel item : offerItems) { 
		    itemTable.getItems().add(item);
		}
	}

	public void handleEvent() {
	}
	
	@Override
	public void handle(ActionEvent event) {
	}
}
