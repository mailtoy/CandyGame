package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

	@FXML
	private Button signUpButton;

	@FXML
	private TextField idSignUp;

	@FXML
	private TextField passwordSignUp;

	@FXML
	private Label status;

	private boolean isUsed = false;
	private LoginVerifyUser login;

	@FXML
	public void enterGame(ActionEvent event) {
		login = new LoginVerifyUser(idSignUp.getText(), passwordSignUp.getText());
		try {
			if (idSignUp.getText().isEmpty() && passwordSignUp.getText().isEmpty()) {
				status.setText("Please input your ID and password!");
			}else if (passwordSignUp.getText().isEmpty()) { // has just id, no password
				status.setText("Please input your password!");
			} else if (idSignUp.getText().isEmpty()) {
				status.setText("Please input your ID!");
			} else {
				if (login.verifyUser() == 0) { // new user
					// has id and password
					addUser();
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
				if (login.verifyUser() == 1) { // repeat sign up
					status.setText("This ID has been used already");

				}
			}

		} catch (IllegalArgumentException e) {
			status.setText("ERROR : " + e.getMessage());
		}
	}

	public boolean isUsed() {
		if (login.idList.contains(idSignUp.getText())) {
			isUsed = true;
			return isUsed;
		} else {
			isUsed = false;
			return isUsed;
		}
	}

	public void addUser() {
		if (!isUsed()) {
			login.idList.add(idSignUp.getText());
			login.passwordList.add(passwordSignUp.getText());
			System.out.println("added user");
		}
	}

}
