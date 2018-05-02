package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import backend.DataTable;
import backend.UserConnectData;
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
	private Button backHomeButton;
	@FXML
	private Label status;
	private List<DataTable> list;
	private UserConnectData data = UserConnectData.getInstance();

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

	@FXML
	public void enterGame(ActionEvent event) throws IOException {
		String userLogin = id.getText();
		System.out.println(userLogin);
		String passL = password.getText();
		boolean b = data.isUserExist(userLogin);
		System.out.println(b);
		if (!data.isUserExist(userLogin)) {
			status.setText("Please sign up!");
		}
		// can login (have old id and pass)
		if (data.isUserExist(userLogin)) {
			list = data.pullAllUserdata();
			boolean find = false;
			for (DataTable d : list) {
				System.out.println(d.getUsername() + " " + d.getPassword());
				if (d.getPassword().equals(passL)) {
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

					find = true;
				}

			}
			// incorrect id and/or password
			if (!find) {
				status.setText("Incorrect id and/or password");
				// login again
			}
		}

		if (userLogin.isEmpty() && !passL.isEmpty()) {
			status.setText("Please input your ID!");
		} else if (passL.isEmpty() && !userLogin.isEmpty()) {
			status.setText("Please input your password!");
		} else if (userLogin.isEmpty() && passL.isEmpty()) {
			status.setText("Please input your ID and password!");
		}
	}
}
