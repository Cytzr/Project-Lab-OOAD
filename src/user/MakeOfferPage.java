package user;

import controller.OfferController;
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
import model.Offer;
import seller.ShowAllSellerItemPage;
import utilities.AlertUtil;

public class MakeOfferPage implements EventHandler<ActionEvent> {
	//declare required components
	Stage stage;
	Scene scene;
	GridPane gridPane;
	BorderPane borderPane;
	Label titleLabel, roleLabel, nameLabel, categoryLabel, sizeLabel, priceLabel, offerLabel;
	Label nameTf, categoryTf, sizeTf, priceTf;
	TextField offerTf;
	Button offerButton, backButton;
	HBox titleBox, roleBox, uploadBox, backBox;
	VBox headerBox, footerBox;

	Item item;
	private String userId;
	OfferController offerController = new OfferController();

	public MakeOfferPage(Stage stage, Item item, String userId) {
		this.stage = stage;
		this.userId = userId;
		this.item = item;

		init(item);
		handleEvent();

		stage.setScene(scene);
		stage.setTitle("Make Offer");
		stage.show();
	}

	//component initialize and placement
	private void init(Item item) {
		titleLabel = new Label("Make Offer");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		titleBox = new HBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);

		roleLabel = new Label("Role: User");
		roleBox = new HBox(roleLabel);
		roleBox.setAlignment(Pos.CENTER);

		headerBox = new VBox(titleBox, roleBox);

		nameLabel = new Label("Item Name: ");
		nameTf = new Label(item.getItem_name());

		categoryLabel = new Label("Item Category: ");
		categoryTf = new Label(item.getItem_category());

		sizeLabel = new Label("Item Size: ");
		sizeTf = new Label();
		sizeTf.setText(item.getItem_size());

		priceLabel = new Label("Item Price: ");
		priceTf = new Label(item.getItem_price());

		offerLabel = new Label("Offer Price: ");
		offerTf = new TextField();
		offerTf.setPromptText("Must be more than: " + item.getItem_price());

		offerButton = new Button("Offer");
		backButton = new Button("Back");

		uploadBox = new HBox(offerButton);
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
		
		gridPane.add(offerLabel, 0, 4);
		gridPane.add(offerTf, 1, 4);

		scene = new Scene(borderPane, 400, 300);
	}

	//event handling
	public void handleEvent() {
		offerButton.setOnAction(this);
		backButton.setOnAction(this);
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == offerButton) {
			String offerPrice = offerTf.getText();
			try {
				//offer validation
				int finalPrice = Integer.parseInt(offerPrice);
				Offer highestOffer = offerController.getHighestOffer(item.getItem_id());
				if (highestOffer != null && finalPrice <= highestOffer.getOffer_price()) {
				    AlertUtil.showAlert(Alert.AlertType.ERROR, "Offer Failed",
				            "Offer price must be larger than the current offer price, which is " + highestOffer.getOffer_price());
				    return;
				}
				// Call offerController to make an price offer of the item
				boolean status = offerController.offerPrice(item.getItem_id(), finalPrice, userId);

				//alert based on controller response
				if (status) {
					AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Offer Success", "Offer has been made");
					new ShowAllBuyerItemPage(stage, userId);
				} else {
					AlertUtil.showAlert(Alert.AlertType.ERROR, "Offer Failed", "Something Went Wrong");
				}
				

			} catch (Exception ex) {
				AlertUtil.showAlert(Alert.AlertType.ERROR, "Offer Failed", "Offer price must be in number");
				return;
			}
		}
		//navigates back to the buyer item back to the all buyer item page
		if (event.getSource() == backButton) {
			new ShowAllBuyerItemPage(stage, userId);
		}
	}
}
