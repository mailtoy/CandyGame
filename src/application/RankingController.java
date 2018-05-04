package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import backend.UserConnectData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RankingController {

    @FXML
    private Button backButton;
    
    @FXML
    private VBox vBoxScore;
    private UserConnectData data = UserConnectData.getInstance();
    
    @FXML
    public void initialize(){
    	showRank();
    }
    
    @FXML
    public void handleBackPage(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml")); 
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent)loader.load()));
        stage.show();
        Stage rankingStage = (Stage) backButton.getScene().getWindow();
		rankingStage.close();
    
    }
    
    @FXML
    public void showRank(){
    	System.out.println(data.pullAllUserdata().toString());

    	
//    	sort score from database
    	
    	VBox vBox = new VBox(5);
//		Collections.sort(score);
//		 for (Integer user: score) {
//			 String scoreString = user+"";
            Label userLabel = new Label(data.pullAllUserdata().toString());
//            System.out.println(scoreString);
//            vBoxScore.getChildren().setAll(userLabel);
            vBox.getChildren().add(userLabel);
            vBoxScore.getChildren().add(vBox);
           
//        }
		
    }
    
    public static void main(String[] args) {
    	UserConnectData data = UserConnectData.getInstance();
    	System.out.println(data.pullAllUserdata().toString());
	}

}
