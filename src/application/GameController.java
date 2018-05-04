package application;

import java.io.IOException;

import backend.DataTable;
import backend.UserConnectData;
import game.LogicGame;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameController {
	@FXML
	private ProgressBar timeBar;
	
	@FXML
	private Label scoreLabel;
	
	@FXML
	private Label wordLable;
	
	private Task<Boolean> timeWorker;
	
	@FXML
	private TextField answer;
	
	private int score;
	private LogicGame game;
	private ReplayController replay;
	private String currentWord;
	private String scoreText = "";
	private UserConnectData data = UserConnectData.getInstance();
	private DataTable table = new DataTable() ;
	
	@FXML
	public void initialize() {
		setGame(new LogicGame());
		time();
		game();
		checkWord();
	}

	public void setGame(LogicGame game) {
		this.game = game;
	}
	
	public LogicGame getGame() {
		return game;
	}
	
	public void game(){
		currentWord = game.getWord();
		while (currentWord.length() != 7) {
			currentWord = game.getWord();
		}
		wordLable.setText(currentWord);
		System.out.println("current word : " +currentWord);		
	}
	
	// pop-up show score, highsore, replaybutton
	public void nextScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Replay.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			ReplayController rController = loader.getController();
			rController.setScore(score);
			stage.show();
			Stage signUpStage = (Stage) timeBar.getScene().getWindow();
			signUpStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void time() {
		timeBar.setProgress(0);
		timeWorker = createWorker();
		
		// show replay when it success
		timeWorker.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("success");

				System.out.println(score);
				nextScene();
			}
		});
		timeBar.progressProperty().unbind();
		timeBar.progressProperty().bind(timeWorker.progressProperty());
		new Thread(timeWorker).start();
	}
	
	public void checkWord(){
		if(wordLable.getText().equals(answer.getText())){
			score += 100;
			scoreText = score + "";
			String scoreWord = score + "";
			scoreLabel.setText(scoreWord);
			System.out.println("score : "+ score);
			answer.clear();
			game();
		}
	}

	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 0; i <= 1000; i++) {
					Thread.sleep(10);
					updateProgress(i + 1, 1000);
				}
				return true;
			}
		};
	}
}
