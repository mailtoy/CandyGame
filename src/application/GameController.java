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
import javafx.stage.Modality;
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
	public int score;
	private boolean isStop = false;
	private LogicGame game;
	private String currentWord;
	
	@FXML
	public void initialize() {
		setGame(new LogicGame());
		time();
		score();
	}

	public void setGame(LogicGame game) {
		this.game = game;
	}
	
	public void game(){
		currentWord = game.getWord();
		System.out.println(currentWord);
		wordLable.setText(currentWord.toString());
	}
	
	// pop-up show score, highsore, replaybutton
	public void nextScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Replay.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.initModality(Modality.APPLICATION_MODAL);
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
		pause.setDisable(false);
		timeBar.setProgress(0);
		timeWorker = createWorker();
		// print "running" when it is not success but it is running
		timeWorker.setOnRunning(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("running");
			}
		});
		// print "failed" when it is not success but it is failed
		timeWorker.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("failed");
			}
		});
		// show replay when it success
		timeWorker.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				System.out.println("success");
				nextScene();
			}
		});
		timeBar.progressProperty().unbind();
		timeBar.progressProperty().bind(timeWorker.progressProperty());
		new Thread(timeWorker).start();
	}
	
	public void score(){
		scoreLabel.setText("0");
	}

	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 0; i <= 480; i++) {
					if (isStop) {
						break;
					}
					Thread.sleep(10);
					updateProgress(i + 1, 480);
					scoreLabel.setText("1");
				}
				return true;
			}

		};
	}

}
