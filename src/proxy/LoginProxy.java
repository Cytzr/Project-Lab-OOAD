package proxy;


import facade.AppFacade;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import utilities.AlertUtil;
import admin.ReviewItemPage;

public class LoginProxy {
	private AppFacade facade;
    private Stage stage;
    public LoginProxy(Stage stage) {
    	this.stage = stage;
    	this.facade = new AppFacade(stage);
    }

    public void login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Login Failed", "Please enter both username and password.");
            return;
        }
        if (username.equals("admin") && password.equals("admin")) {
        	AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
        	new ReviewItemPage(stage);
        	return;
        }
        facade.login(username, password);
    }
}