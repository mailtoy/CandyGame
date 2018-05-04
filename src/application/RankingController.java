package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RankingController {

    @FXML
    private Button backButton;
    
    @FXML
    private VBox vBoxScore;
    
//    @FXML
//    public void initialize(){
//    	showRank();
//    }
    
   
    public void handleBackPage(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage homeStage = (Stage) backButton.getScene().getWindow();
			homeStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void showRank(){
    	//sort score from database
    	List<Integer> score = new ArrayList<>();
    	score.add(100);
    	score.add(500);
    	score.add(300);
    	score.add(70);
    	vBoxScore = new VBox(score.size());
		Collections.sort(score);
		 for (Integer user: score) {
			 String scoreString = user+"";
            Label userLabel = new Label(scoreString);
            System.out.println(scoreString);
            vBoxScore.getChildren().add(userLabel);
        }
		
    }

}
