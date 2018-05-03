package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RankingMain extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("RankingUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Override public void start(Stage stage) {
//        String[] users = { "Huey", "Dewey", "Louie", "Septima", "Soft", "Sandy" };
//        
//        VBox layout = new VBox(10);
//
//        // ALTERNATIVE 1: add labels in a loop.
//        for (String user: users) {
//            Label userLabel = new Label(user);
//            layout.getChildren().add(userLabel);
//        }
//
//        // ALTERNATIVE 2: use the built-in ListView component.
//        ListView<String> listView = new ListView<>(
//                FXCollections.observableArrayList(users)
//        );
//        layout.getChildren().add(listView);
//
////        layout.setPadding(new javafx.geometry.Insets(10));
//        layout.setPrefSize(100,200);
//
//        stage.setScene(new Scene(layout));
//        stage.show();
//    }
	
//	@Override public void start(Stage stage){
//		List<Integer> score = new ArrayList<>();
//    	score.add(100);
//    	score.add(500);
//    	score.add(300);
//    	score.add(70);
//    	VBox vBoxScore = new VBox(score.size());
//		Collections.sort(score);
//		 for (Integer user: score) {
//			 String scoreString = user+"";
//            Label userLabel = new Label(scoreString);
//            System.out.println(scoreString);
//            vBoxScore.getChildren().add(userLabel);
//        }
//        stage.setScene(new Scene(vBoxScore));
//        stage.show();
//    
//	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

}
