package seller;

import components.SellerNavbar;
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

public class ShowAllSellerItemPage implements EventHandler<ActionEvent> {

	private Stage stage;
	private Scene scene;
	private BorderPane borderPane1, borderPane2;
	private Label titleLabel, roleLabel;
	private Button uploadButton;
	private HBox titleBox, roleBox, buttonBox;
	private VBox headerBox;
	
	TableView<Item> itemTable;
	
	public ShowAllSellerItemPage(Stage stage) {
		this.stage = stage;
		
		init();
		initTable();
		handleEvent();
		
		stage.setScene(scene);
		stage.setTitle("My Items");
		stage.show();
	}
	
	private void init() {
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
		borderPane1.setTop(SellerNavbar.getInstance(stage));
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
		            private final Button buttonEdit = new Button("Detail");
		            private final Button buttonDelete = new Button("Delete");
		            private final HBox buttonContainer = new HBox(10, buttonEdit, buttonDelete);
		            {
		                buttonEdit.setOnAction(event -> {
		                    Item currentItem = getTableView().getItems().get(getIndex());
		                    new ViewSellerItemPage(stage, currentItem);
		                });

		                buttonDelete.setOnAction(event -> {
		                    Item currentItem = getTableView().getItems().get(getIndex());
		                    getTableView().getItems().remove(currentItem);
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
		
		//dummy data
		itemTable.getItems().add(new Item("1", "Name", "10", "100", "CLothing", "", "", ""));
	}

	public void handleEvent() {
		uploadButton.setOnAction(this);
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == uploadButton) {
			new CreateItemPage(stage);
		}
	}
}
