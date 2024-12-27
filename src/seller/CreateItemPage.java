package seller;

import controller.ItemController;
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
import utilities.AlertUtil;

public class CreateItemPage implements EventHandler<ActionEvent> {
	
	//declare required components
	private Stage stage;
	private Scene scene;
	private GridPane gridPane;
	private BorderPane borderPane;
	private Label titleLabel, roleLabel, nameLabel, categoryLabel, sizeLabel, priceLabel;
	private TextField nameTf, categoryTf, sizeTf, priceTf;
	private Button uploadButton, backButton;
	private HBox titleBox, roleBox, uploadBox, backBox;
	private VBox headerBox, footerBox;
	private String userId;
	
	public CreateItemPage(Stage stage, String userId) {
		this.stage = stage;
		this.userId = userId;
		init();
		handleEvent();
		
		stage.setScene(scene);
		stage.setTitle("Upload Item");
		stage.show();
	}
	
	private void init() {
		
		//component initialize and placement
		titleLabel = new Label("Upload Item");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		titleBox = new HBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		
		roleLabel = new Label("Role: Seller");
		roleBox = new HBox(roleLabel);
		roleBox.setAlignment(Pos.CENTER);
		
		headerBox = new VBox(titleBox, roleBox);
		
		nameLabel = new Label ("Item Name: ");
		nameTf = new TextField();
		nameTf.setPromptText("Must at least be 3 character long");
		
		categoryLabel = new Label("Item Category: ");
		categoryTf = new TextField();
		categoryTf.setPromptText("Must at least be 3 character long");
		
		sizeLabel = new Label("Item Size: ");
		sizeTf = new TextField();
		sizeTf.setPromptText("Cannot be empty");
		
		priceLabel = new Label("Item Price: ");
		priceTf = new TextField();
		priceTf.setPromptText("Must be a number");
		
		uploadButton = new Button("Upload Item");
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
		ItemController itemController = new ItemController();
		if(event.getSource() == uploadButton) {
			String name = nameTf.getText();
			String category = categoryTf.getText();
			String size = sizeTf.getText();
			int price;
			
			if(name.isEmpty() || category.isEmpty() || size.isEmpty()) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Upload Failed", "All fields must be filled");
				return;
			}
			
			if(name.length() < 3) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Upload Failed", "Item name must be longer at least 3 characters long");
				return;
			}
			if(category.length() < 3) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Upload Failed", "Item category must be at least 3 characters long");
				return;
			}
			try {
				price = Integer.parseInt(priceTf.getText());
			}
			catch (Exception ex){
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Upload Failed", "Item price must be in number");
				return;
			}
			if(price <= 0) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Upload Failed", "Item price must be more than 0");
				return;
			}
			// Call itemController to create the item
			boolean status = itemController.uploadItem(name, size, price, category, userId);
			//Check status of the item creation
			if (status == true) {
				AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Upload Successful", "Item has been uploaded");
				//Redirect to seller item page
				new ShowAllSellerItemPage(stage, userId);
			} else {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Upload Failed", "Something Went Wrong!");
				return;
			}
			
		}
		
		//navigate back when the back button is pressed
		if(event.getSource() == backButton) {
			new ShowAllSellerItemPage(stage, userId);
		}
	}
	
}
