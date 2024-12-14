package components;

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
import utilities.AlertUtil;
import utilities.Connect;

public class RegisterPage implements EventHandler<ActionEvent>{

	//declare required components
	
	private Stage stage;
	private Scene scene;
	private GridPane gridPane;
	private BorderPane borderPane;
	private Label usernameLabel, passLabel, phoneLabel, addressLabel, roleLabel, titleLabel;
	private TextField usernameTf, phoneTf, addressTf;
	private PasswordField passTf;
	private RadioButton sellerRadio, buyerRadio, adminRadio;
	private Button registerButton, loginButton;
	private HBox roleBox, registerBox, loginBox;
	private VBox footerBox;
	private ToggleGroup roleGroup;
	private Connect db;
	
    public RegisterPage(Stage stage) {
        this.stage = stage;

        init();
        handleEvent();

        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }

    private void init() {
    	//component initialize and placement
        titleLabel = new Label("Register");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        usernameLabel = new Label("Username: ");
        usernameTf = new TextField();
        usernameTf.setPromptText("Must be unique");

        passLabel = new Label("Password: ");
        passTf = new PasswordField();
        passTf.setPromptText("Must have special character(s)");

        phoneLabel = new Label("Phone Number: ");
        phoneTf = new TextField();
        phoneTf.setPromptText("Starts with +62");

        addressLabel = new Label("Address: ");
        addressTf = new TextField();
        addressTf.setPromptText("Must not be empty");

        roleLabel = new Label("Role: ");
        roleGroup = new ToggleGroup();
        sellerRadio = new RadioButton("Seller");
        buyerRadio = new RadioButton("Buyer");
        adminRadio = new RadioButton("Admin");
        sellerRadio.setToggleGroup(roleGroup);
        buyerRadio.setToggleGroup(roleGroup);
        adminRadio.setToggleGroup(roleGroup);

        roleBox = new HBox(7, sellerRadio, buyerRadio);
        roleBox.setAlignment(Pos.CENTER_LEFT);

        registerButton = new Button("Register");
        loginButton = new Button("Back to Login");
        
        registerBox = new HBox(registerButton);
        registerBox.setAlignment(Pos.CENTER);
        registerBox.setPadding(new Insets(0, 0, 10, 0));
        
        loginBox = new HBox(loginButton);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(0, 0, 10, 0));
        
        footerBox = new VBox(registerBox, loginBox);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameTf, 1, 0);

        gridPane.add(passLabel, 0, 1);
        gridPane.add(passTf, 1, 1);

        gridPane.add(phoneLabel, 0, 2);
        gridPane.add(phoneTf, 1, 2);

        gridPane.add(addressLabel, 0, 3);
        gridPane.add(addressTf, 1, 3);

        gridPane.add(roleLabel, 0, 4);
        gridPane.add(roleBox, 1, 4);
        
        borderPane = new BorderPane();
        borderPane.setTop(titleBox);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(footerBox);

        scene = new Scene(borderPane, 400, 300);
    }
    
    //event handling
    public void handleEvent() {
    	registerButton.setOnAction(this);
    	loginButton.setOnAction(this);
    }

    //actual event handling based on source
	@Override
	public void handle(ActionEvent event) {
		 UserController userController = new UserController();
		if(event.getSource() == registerButton) {
            String username = usernameTf.getText();
            String pass = passTf.getText();
            String phone= phoneTf.getText();
            String address = addressTf.getText();
            
            //fields validation
	        if (username.isEmpty()) {
	            AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Username cannot be empty.");
	            return;
	        }
	        if (username.length() < 3) {
	            AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Username must be at least 3 characters long.");
	            return;
	        }

	        if (pass.isEmpty()) {
	            AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Password cannot be empty.");
	            return;
	        }
	        if (pass.length() < 8 || !pass.contains("!") && !pass.contains("@") && !pass.contains("#") && 
	        	    !pass.contains("$") && !pass.contains("%") && !pass.contains("^") && 
	        	    !pass.contains("&") && !pass.contains("*")) {
	            AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed",
	                "Password must be at least 8 characters long and include special characters such as (!, @, #, $, %, ^, &, *).");
	            return;
	        }

	        if (!(phone.length() >= 10) || !phone.startsWith("+62")) {
	            AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Phone number must start with +62 and at least 10 numbers long.");
	            return;
	        }

	        if (address.isEmpty()) {
	            AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Address cannot be empty.");
	            return;
	        }

	        if (roleGroup.getSelectedToggle() == null) {
	            AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "You must select a role.");
	            return;
	        }
	        if (username.equalsIgnoreCase("admin")) {
	        	 AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Please choose another username");
		            return;
	        }
	        RadioButton selectedRole = (RadioButton) roleGroup.getSelectedToggle();
	        String role = selectedRole.getText();
	        
	        //registers user to db
	        boolean status = userController.createUser(username, pass, phone, address, role);
	        //show alert based on the response
	        if (status == true) {
	        	 AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Welcome, " + username + "! Your role is " + role + ".");
	        } else {
	        	 AlertUtil.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Something Went Wrong or User Already Exist");
	        }
	       
		}
		
		//navigate to login page
		if(event.getSource() == loginButton) {
			 new LoginPage(stage);
		}
	}
}
