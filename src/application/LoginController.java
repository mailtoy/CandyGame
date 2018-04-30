package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField id;
	@FXML
	private TextField password;
	@FXML
	private Button enterButton;
	@FXML
	private Button sigUpButtonP;
	@FXML
	private Label status;
	public List<String> idList = new ArrayList<>();
	public List<String> passwordList = new ArrayList<>();
	private LoginVerifyUser loginUser;

	public void signUpButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage signUpStage = (Stage) sigUpButtonP.getScene().getWindow();
			signUpStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void enterGame(ActionEvent event) throws IOException {
		loginUser = LoginVerifyUser.getInstance();
		String userLogin = id.getText();
		String passL = password.getText();
		try {
			if (loginUser.verifyUser(userLogin, passL) == 0) {
				// can play
				if (userLogin.isEmpty() && !passL.isEmpty()) {
					status.setText("Please input your ID!");
				} else if (passL.isEmpty() && !userLogin.isEmpty()) { // has
																		// just
																		// id,
																		// no
																		// password
					status.setText("Please input your password!");
				} else if (userLogin.isEmpty() && passL.isEmpty()) {
					status.setText("Please input your ID and password!");
				} else {
					status.setText("Please sign up!");
				}
			}
			// can login (have old id and pass)
			if (loginUser.verifyUser(userLogin, passL) == 1) {

				// can play
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Loading.fxml"));
					Stage stage = new Stage();
					stage.setScene(new Scene((Parent) loader.load()));
					stage.show();
					Stage loginStage = (Stage) enterButton.getScene().getWindow();
					loginStage.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// incorrect id and/or password
			if (loginUser.verifyUser(userLogin, passL) == 2) {
				status.setText("Incorrect id and/or password");
				// login again
			}
		} catch (IllegalArgumentException e) {
			status.setText("ERROR : " + e.getMessage());
		} finally {

		}
	}

}
