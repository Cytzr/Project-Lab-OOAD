package admin;

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
import model.Item;
import utilities.AlertUtil;

public class RejectItemPage implements EventHandler<ActionEvent>{

	Stage stage;
	Scene scene;
	GridPane gridPane;
	BorderPane borderPane;
	Label titleLabel, roleLabel, nameLabel, categoryLabel, sizeLabel, priceLabel, reasonLabel;
	Label nameTf, categoryTf, sizeTf, priceTf;
	TextField  reasonTf;
	Button rejectButton, backButton;
	HBox titleBox, roleBox, uploadBox, backBox;
	VBox headerBox, footerBox;
	
	Item item;
	ItemController itemController = new ItemController();
	public RejectItemPage(Stage stage, Item item) {
		this.stage = stage;
		
		this.item = item;
		
		init(item);
		handleEvent();
		
		stage.setScene(scene);
		stage.setTitle("Reject Item");
		stage.show();
	}
	
	private void init(Item item) {
		titleLabel = new Label("Reject Item");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		titleBox = new HBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		
		roleLabel = new Label("Role: Admin");
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
		
		reasonLabel = new Label("Reject Reason: ");
		reasonTf = new TextField();
		reasonTf.setPromptText("Must be filled");
		
		rejectButton = new Button("Reject Item");
		backButton = new Button("Back");
		
		uploadBox = new HBox(rejectButton);
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
		
		gridPane.add(reasonLabel, 0, 4);
		gridPane.add(reasonTf, 1, 4);
		
		scene = new Scene(borderPane, 400, 300);
	}
	
	public void handleEvent() {
		rejectButton.setOnAction(this);
		backButton.setOnAction(this);
	}

	@Override
	public void handle(ActionEvent event) {
		String reason = reasonTf.getText();
		if(event.getSource() == rejectButton) {
			if(reason.length() <= 0) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Reject Error", "Reason must be filled");
				return;
			}
			boolean status = itemController.declineItem(item.getItem_id(), reason);
			 if (status) {
          	   AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Decline Successful", "Item has been declined");
          	   new ReviewItemPage(stage);
             } else {
          	   AlertUtil.showAlert(Alert.AlertType.ERROR, "Decline Failed", "Something Went Wrong!");
             }
		}
		if(event.getSource() == backButton) {
			new ReviewItemPage(stage);
		}
	}
	
}
