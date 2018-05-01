package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReplayController {

    @FXML
    private Label showScore;

    @FXML
    private Button homeButton;

    @FXML
    private Button replayButton;

    @FXML
    private Button rankingButton;
    
    @FXML
    private void backHome(ActionEvent event){
    	try {
    		System.out.println("qwertyui");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage homeStage = (Stage) homeButton.getScene().getWindow();
			homeStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void playAgain(ActionEvent event){
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Loading.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage replayStage = (Stage) replayButton.getScene().getWindow();
			replayStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void showRanking(ActionEvent event){
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RankingUI.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage rankingStage = (Stage) rankingButton.getScene().getWindow();
			rankingStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}