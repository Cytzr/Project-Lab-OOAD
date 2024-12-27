package seller;

import controller.ItemController;
import facade.AppFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Item;
import utilities.AlertUtil;

public class EditItemPage implements EventHandler<ActionEvent> {
	//declare required components
	Stage stage;
	Scene scene;
	GridPane gridPane;
	BorderPane borderPane;
	Label titleLabel, roleLabel, nameLabel, categoryLabel, sizeLabel, priceLabel;
	TextField nameTf, categoryTf, sizeTf, priceTf;
	Button uploadButton, backButton;
	HBox titleBox, roleBox, uploadBox, backBox;
	VBox headerBox, footerBox;
	Item item;
	private String userId;
	private String itemId;
	private AppFacade facade;
	
	public EditItemPage(Stage stage, Item item, String userId) {
		this.stage = stage;
		this.userId = userId;
		this.item = item;
		this.facade = new AppFacade(stage);
		itemId = item.getItem_id();
		init(item);
		handleEvent();
		
		stage.setScene(scene);
		stage.setTitle("Edit Item");
		stage.show();
	}
	
	private void init(Item item) {
		
		//component initialize and placement
		titleLabel = new Label("Edit Item");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		titleBox = new HBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		
		roleLabel = new Label("Role: Seller");
		roleBox = new HBox(roleLabel);
		roleBox.setAlignment(Pos.CENTER);
		
		headerBox = new VBox(titleBox, roleBox);
		
		nameLabel = new Label ("Item Name: ");
		nameTf = new TextField();
		nameTf.setText(item.getItem_name());
		nameTf.setPromptText("Must at least be 3 character long");
		
		categoryLabel = new Label("Item Category: ");
		categoryTf = new TextField();
		categoryTf.setText(item.getItem_category());
		categoryTf.setPromptText("Must at least be 3 character long");
		
		sizeLabel = new Label("Item Size: ");
		sizeTf = new TextField();
		sizeTf.setText(item.getItem_size());
		sizeTf.setPromptText("Cannot be empty");
		
		priceLabel = new Label("Item Price: ");
		priceTf = new TextField();
		priceTf.setText(item.getItem_price());
		priceTf.setPromptText("Must be a number");
		
		uploadButton = new Button("Save");
		backButton = new Button("Back");
		
		uploadBox = new HBox(uploadButton);
		uploadBox.setAlignment(Pos.CENTER);
		uploadBox.setPadding(new Insets(0, 0, 10, 0));
		
		backBox = new HBox(backButton);
		backBox.setAlignment(Pos.CENTER);
		backBox.setPadding(new Insets(0, 0, 10, 0));
		
		footerBox = new VBox(uploadBox, backBox);
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		borderPane = new BorderPane();
		borderPane.setTop(headerBox);
		borderPane.setCenter(gridPane);
		borderPane.setBottom(footerBox);
		
		gridPane.add(nameLabel, 0, 0);
		gridPane.add(nameTf, 1, 0);
		
		gridPane.add(categoryLabel, 0, 1);
		gridPane.add(categoryTf, 1, 1);
		
		gridPane.add(sizeLabel, 0, 2);
		gridPane.add(sizeTf, 1, 2);
		
		gridPane.add(priceLabel, 0, 3);
		gridPane.add(priceTf, 1, 3);
		
		scene = new Scene(borderPane, 400, 300);
	}
	
	//event handling
	public void handleEvent() {
		uploadButton.setOnAction(this);
		backButton.setOnAction(this);
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == uploadButton) {
		
			String name = nameTf.getText();
			String category = categoryTf.getText();
			String size = sizeTf.getText();
			int price;
			
			//field validation
			if(name.isEmpty() || category.isEmpty() || size.isEmpty()) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Edit Failed", "All fields must be filled");
				return;
			}
			
			if(name.length() < 3) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Edit Failed", "Item name must be longer at least 3 characters long");
				return;
			}
			if(category.length() < 3) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Edit Failed", "Item category must be at least 3 characters long");
				return;
			}
			try {
				price = Integer.parseInt(priceTf.getText());
			}
			catch (Exception ex){
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Edit Failed", "Item price must be in number");
				return;
			}
			if(price <= 0) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Edit Failed", "Item price must be more than 0");
				return;
			}
			
			facade.editItem(size, name, size, price, category, userId);
		}
		//navigate to the page before if the back button is pressed
		if(event.getSource() == backButton) {
			new ViewSellerItemPage(stage, item, userId);
		}
	}
	
}
