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
	private Button pause;
	
	@FXML
	private TextField answer;
	
	private static int score;
	private boolean isStop = false;
	private LogicGame game;
	private String currentWord;
	
	@FXML
	public void initialize() {
		setGame(new LogicGame());
		time();
		game();
//		score();
	}

	public void setGame(LogicGame game) {
		this.game = game;
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
		// setDisable() is used to enable or disable the elements
//		pause.setDisable(false);
		timeBar.setProgress(0);
		timeWorker = createWorker();
		// show replay when it success
//		checkWord();
		timeWorker.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("success");
				System.out.println(game.getScore()+"");
//				nextScene();
			}
		});
		timeBar.progressProperty().unbind();
		timeBar.progressProperty().bind(timeWorker.progressProperty());
		new Thread(timeWorker).start();
	}
	
	public void checkWord(){
		if(wordLable.getText().equals(answer.getText())){
			score += 100;
			game.setScore(score);
			String scoreWord = game.getScore() + "";
			scoreLabel.setText(scoreWord);
			answer.clear();
			game();
		}
	}
	
	public void score(){
		
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
				}
				return true;
			}
		};
	}
}
