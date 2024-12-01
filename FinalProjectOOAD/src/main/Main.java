package main;

import admin.ReviewItemPage;
import components.LoginPage;
import components.RegisterPage;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Item;
import seller.CreateItemPage;
import seller.EditItemPage;
import seller.ShowAllSellerItemPage;
import user.ShowAllBuyerItemPage;
import user.ShowAllTransactionHistoryPage;
import user.ShowAllWishlistPage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		new RegisterPage(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
