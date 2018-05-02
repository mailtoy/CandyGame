package application;

import java.io.IOException;

import game.LogicGame;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameController {
	@FXML
	private ProgressBar timeBar;
	
	@FXML
	private Label scoreLabel;
	
	@FXML
	private Label wordLable;
	
	private Task<Boolean> timeWorker;
	
	@FXML
	private Button pause;
	
	@FXML
	private TextField answer;
	
	public int score;
	public int oldScore;
	private boolean isStop = false;
	private LogicGame game;
	private String currentWord;
	
	@FXML
	public void initialize() {
		setGame(new LogicGame());
		time();
//		score();
	}

	public void setGame(LogicGame game) {
		this.game = game;
	}
	
	public void game(){
		currentWord = game.getWord();
		wordLable.setText(currentWord);
		System.out.println("current word : " +currentWord);
		answer.setText("");
//		if(score == oldScore && score != 0 && oldScore != 0){
//			currentWord = game.getWord();
//			System.out.println(currentWord);
//			wordLable.setText(currentWord);
//		}
		
	}
	
	// pop-up show score, highsore, replaybutton
	public void nextScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Replay.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("Replay.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();			
			stage.setScene(scene);
			stage.show();
			 Stage replayStage = (Stage) timeBar.getScene().getWindow();
			 replayStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void stopAction() {
		if (isStop) {
			isStop = false;
		} else 
			isStop = true;
		// when cancel button is clicked it will end the task created and
		timeWorker.cancel(true);
		// unbinds progress indicator from that task
		timeBar.progressProperty().unbind();
	}

	public void time() {
		game();
		// setDisable() is used to enable or disable the elements
//		pause.setDisable(false);
		timeBar.setProgress(0);
		timeWorker = createWorker();
		// show replay when it success
		checkWord();
		timeWorker.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("success");
				System.out.println(score+"");
//				nextScene();
			}
		});
		timeBar.progressProperty().unbind();
		timeBar.progressProperty().bind(timeWorker.progressProperty());
		new Thread(timeWorker).start();
	}
	
	public void checkWord(){
		if(wordLable.getText().equals(answer.getText())){
			System.out.println("init score : "+ score);
			score += 100;
			String scoreWord = score+"";
			scoreLabel.setText(scoreWord);
			game();
			System.out.println("current score : " + scoreLabel.getText());getClass();
			System.out.println("next word : " + currentWord);
			System.out.println(wordLable.getText());
		}
	}
	
	public void score(){
//		checkWord();
//		scoreLabel.setText("0");
	}

	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 0; i <= 1000; i++) {
					if (isStop) {
						break;
					}
					Thread.sleep(10);
					updateProgress(i + 1, 1000);
					checkWord();
				}
				return true;
			}

		};
	}

}
