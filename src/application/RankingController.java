package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RankingController {

    @FXML
    private Button backButton;
    
    @FXML
    public void handleBackPage(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml")); 
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent)loader.load()));
        stage.show();
        Stage rankingStage = (Stage) backButton.getScene().getWindow();
		rankingStage.close();
    
    }

}
