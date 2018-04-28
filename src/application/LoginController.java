package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField id;
	@FXML
	private TextField password;
	@FXML
	private Button enterButton;
	@FXML
	private Label status;
	
	@FXML
	public void login(ActionEvent event) {
		LoginVerifyUser login = new LoginVerifyUser(id.getText(), password.getText());
		try {
			// can login (have old id and pass)
			if (login.verifyUser() == 1) {
				// can play
			} 
			// incorrect
			if (login.verifyUser() == 2){
				status.setText("Incorrect username and/or password");
				// login again
			}
			// new user
			if (login.verifyUser() == 0) {
				//can play
			}
		} catch (IllegalArgumentException e) {
			status.setText("ERROR : " + e.getMessage());
		} finally {
			
		}
	}
		

}
