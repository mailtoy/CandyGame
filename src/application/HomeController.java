package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HomeController {
	
	@FXML
	private Button loginButton;
	@FXML
	private Button rankButton;
	@FXML
	private Button signUpButton;
	
	public void login(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage loginStage = (Stage) loginButton.getScene().getWindow();
			loginStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void signUp(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage loginStage = (Stage) signUpButton.getScene().getWindow();
			loginStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void ranking(ActionEvent event){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RankingUI.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage loginStage = (Stage) rankButton.getScene().getWindow();
			loginStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}
