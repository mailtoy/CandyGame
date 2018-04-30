package application;

import java.io.IOException;

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
	private Button play;
	
	@FXML
	public void initialize() {
		time();
	}

	// pop-up show score, highsore, replaybutton
	public void nextScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Replay.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			Stage loginStage = (Stage) timeBar.getScene().getWindow();
			loginStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    private void stopAction()
    {
       //when cancel button is clicked it will end the task created and
        timeWorker.cancel(true);
      //unbinds progress indicator from that task
         timeBar.progressProperty().unbind();
     //set it to 0
//         timeBar.setProgress(0);
//        start.setDisable(false);
    }
	

	public void time() {
		//setDisable() is used to enable or disable the elements
        pause.setDisable(false);
//        start.setDisable(true);
        
		timeBar.setProgress(0);
		timeWorker = createWorker();
		timeWorker.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				nextScene();
			}
		});
		timeBar.progressProperty().unbind();
		timeBar.progressProperty().bind(timeWorker.progressProperty());
		new Thread(timeWorker).start();
	}

	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 0; i < 480; i++) {
					Thread.sleep(125);
					updateProgress(i + 1,480 );
				}
				timeWorker.cancel(true);
				return true;
			}
		};
	}
}
