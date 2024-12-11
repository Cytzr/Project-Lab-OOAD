package seller;

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

public class ViewSellerItemPage implements EventHandler<ActionEvent>{
	//declare required ccomponents
	Stage stage;
	Scene scene;
	GridPane gridPane;
	BorderPane borderPane;
	Label titleLabel, roleLabel, nameLabel, categoryLabel, sizeLabel, priceLabel;
	Label nameTf, categoryTf, sizeTf, priceTf;
	Button editButton, backButton;
	HBox titleBox, roleBox, uploadBox, backBox;
	VBox headerBox, footerBox;
	
	Item item;
	private String userId;
	
	public ViewSellerItemPage(Stage stage, Item item, String userId) {
		this.stage = stage;
		this.userId = userId;
		this.item = item;
		
		init(item);
		handleEvent();
		
		stage.setScene(scene);
		stage.setTitle("Edit Item");
		stage.show();
	}
	
	//component initialize and placement
	private void init(Item item) {
		titleLabel = new Label("Edit Item");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		titleBox = new HBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		
		roleLabel = new Label("Role: Seller");
		roleBox = new HBox(roleLabel);
		roleBox.setAlignment(Pos.CENTER);
		
		headerBox = new VBox(titleBox, roleBox);
		
		nameLabel = new Label ("Item Name: ");
		nameTf = new Label(item.getItem_name());
		
		categoryLabel = new Label("Item Category: ");
		categoryTf = new Label(item.getItem_category());
		
		sizeLabel = new Label("Item Size: ");
		sizeTf = new Label();
		sizeTf.setText(item.getItem_size());

		priceLabel = new Label("Item Price: ");
		priceTf = new Label(item.getItem_price());
		
		editButton = new Button("Edit Item");
		backButton = new Button("Back");
		
		uploadBox = new HBox(editButton);
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
		editButton.setOnAction(this);
		backButton.setOnAction(this);
	}

	@Override
	public void handle(ActionEvent event) {
		//navigate based on event source
		if(event.getSource() == editButton) {
			new EditItemPage(stage, item, userId);
		}
		if(event.getSource() == backButton) {
			new ShowAllSellerItemPage(stage, userId);
		}
	}
}
