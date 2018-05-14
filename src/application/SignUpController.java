package application;

import java.io.IOException;

import backend.DataTable;
import backend.UserConnectData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

	@FXML
	private Button signUpButton;

	@FXML
	private Button backHomeButton;

	@FXML
	private TextField idSignUp;

	@FXML
	private PasswordField passwordSignUp;

	@FXML
	private Label status;

	private boolean isUsed = false;
	private LoginVerifyUser signUp;
	private UserConnectData data = UserConnectData.getInstance();

	@FXML
	public void enterGame(ActionEvent event) {
		signUp = LoginVerifyUser.getInstance();
		String username = idSignUp.getText();
		String passW = passwordSignUp.getText();
		try {
			if(username.length() > 8 || passW.length() > 8){
				status.setText("ID and password must less than 8 characters.");
			}else {
				if (username.isEmpty() && passW.isEmpty()) {
					status.setText("Please input your ID and password!");
				} else if (passW.isEmpty()) { // has just id, no password
					status.setText("Please input your password!");
				} else if (username.isEmpty()) {
					status.setText("Please input your ID!");
				} else {
					if (!data.isUserExist(username)) { // new user
						// has id and password
						data.createUser(new DataTable(username, passW));
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
							Stage stage = new Stage();
							stage.setScene(new Scene((Parent) loader.load()));
							stage.show();
							Stage signUpStage = (Stage) signUpButton.getScene().getWindow();
							signUpStage.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else { // repeat sign up
						status.setText("This ID has been used already");
					}
				}	
			}
		} catch (IllegalArgumentException e) {
			status.setText("ERROR : " + e.getMessage());
		}
	}

	public boolean isUsed() {
		if (signUp.idList.contains(idSignUp.getText())) {
			isUsed = true;
			return isUsed;
		} else {
			isUsed = false;
			return isUsed;
		}
	}
	
	public void back(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage signUpStage = (Stage) backHomeButton.getScene().getWindow();
			signUpStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
