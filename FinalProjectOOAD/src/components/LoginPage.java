package components;

import java.util.Map;

import admin.ReviewItemPage;
import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seller.ShowAllSellerItemPage;
import user.ShowAllBuyerItemPage;
import utilities.AlertUtil;

public class LoginPage implements EventHandler<ActionEvent>{

    private Scene scene;
    private GridPane gridPane;
    private BorderPane borderPane;
    private Label titleLabel, usernameLabel, passLabel;
    private TextField usernameTf;
    private PasswordField passTf;
    private Button loginButton, registerButton;
    private Stage stage;
    private HBox titleBox, loginBox, registerBox;
    private VBox footerBox;
    
    public LoginPage(Stage stage) {
        this.stage = stage;

        init();
        handleEvent();

        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    private void init() {
        titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        
        usernameLabel = new Label("Username: ");
        usernameTf = new TextField();
        usernameTf.setPromptText("Enter your username");
        
        passLabel = new Label("pass: ");
        passTf = new PasswordField();
        passTf.setPromptText("Enter your pass");
        
        loginButton = new Button("Login");
        registerButton = new Button("Register Account");
        
        loginBox = new HBox(loginButton);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(0, 0, 10, 0));
        
        registerBox = new HBox(registerButton);
        registerBox.setAlignment(Pos.CENTER);
        registerBox.setPadding(new Insets(0, 0, 10, 0));
        
        footerBox = new VBox(loginBox, registerBox);
        
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameTf, 1, 0);
        
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passTf, 1, 1);
        
        borderPane = new BorderPane();
        borderPane.setTop(titleBox);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(footerBox);
        
        scene = new Scene(borderPane, 400, 200);
    }

    private void handleEvent() {
       loginButton.setOnAction(this);
       registerButton.setOnAction(this);
    }

	@Override
	public void handle(ActionEvent event) {
		UserController userController = new UserController();
		if(event.getSource() == loginButton) {
			String username = usernameTf.getText();
			String pass = passTf.getText();
			 if (username.isEmpty() || pass.isEmpty()) {
		            AlertUtil.showAlert(Alert.AlertType.ERROR, "Login Failed", "Please enter both username and pass.");
		            return;
		        } else {
		            if (username.equals("admin") && pass.equals("pass")) {
		            	AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
		            	new ReviewItemPage(stage);
		                //navigates
		            } else {
		            	Map<String, String> user = userController.login(username, pass);
		            	if (user != null) {
		            	    String userId = user.get("userId");
		            	    String role = user.get("role");
		            	    
		            		AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
		            		
		            		if(role.equals("Seller")) {

		            			new ShowAllSellerItemPage(stage, userId);
		            		
		            		} else {
		            			new ShowAllBuyerItemPage(stage, userId);
		            		}
		            	} else {
		            		AlertUtil.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or pass.");
		            	}
		                
		                return;
		            }
		        }
		}
		if(event.getSource() == registerButton) {
			new RegisterPage(stage);
		}
	}
    
}
