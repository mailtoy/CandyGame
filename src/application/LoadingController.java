package application;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class LoadingController {
	@FXML
	private ProgressBar loadingBar;
	@FXML
	private Button tButton;
	@FXML
	private Button backHomeButton;
	
	private Task<Boolean> copyWorker;

	@FXML
	public void initialize() {
		finishedLoad();
	}

	public void nextScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage loginStage = (Stage) loadingBar.getScene().getWindow();
			loginStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void finishedLoad() {
		loadingBar.setProgress(0);
		copyWorker = createWorker();
		copyWorker.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				nextScene();
			}
		});
		loadingBar.progressProperty().unbind();
		loadingBar.progressProperty().bind(copyWorker.progressProperty());
		new Thread(copyWorker).start();
	}

	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 0; i < 1000; i++) {
					Thread.sleep(2);
					updateProgress(i + 1, 1000);
				}
				return true;
			}
		};
	}
}
