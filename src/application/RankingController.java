package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import backend.DataTable;
import backend.UserConnectData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RankingController {

	@FXML
	private Button backButton;
	@FXML
	private ScrollPane scroll;

	@FXML
	private VBox vBoxScore;
	private UserConnectData data = UserConnectData.getInstance();

	@FXML
	public void initialize() {
		showRank();
	}

	@FXML
	public void handleBackPage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene((Parent) loader.load()));
		stage.show();
		Stage rankingStage = (Stage) backButton.getScene().getWindow();
		rankingStage.close();

	}

	public static void sortScore(List<? extends DataTable> money) {
		Collections.sort(money, new Comparator<DataTable>() {

			@Override
			public int compare(DataTable o1, DataTable o2) {
				if (o1.getHighScore() < o2.getHighScore())
					return 1;
				else if (o1.getHighScore() > o2.getHighScore())
					return -1;
				return 0;
			}
		});
	}

	@FXML
	public void showRank() {
		System.out.println(data.pullAllUserdata().toString());
		List<DataTable> listPlayer = new ArrayList<DataTable>(data.pullAllUserdata());
		VBox vBox = new VBox(listPlayer.size());
		sortScore(listPlayer);
		Label userLabel = null;
		int index = 1;
		for (DataTable dataTable : listPlayer) {
			userLabel = new Label(String.format(" %2d. %-14s  %4d", index,dataTable.getUsername(),dataTable.getHighScore()));
			userLabel.setFont(Font.font("Monospace",FontWeight.BOLD,20));
			Background background = new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY));
			userLabel.setBackground(background);
			userLabel.setAlignment(Pos.TOP_CENTER);
			vBox.setBackground(background);
			vBox.getChildren().addAll(userLabel);
//			vBox.setStyle("-fx-background-color:red");
			
			index++;
		}
		Background background = new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY));
		scroll.setBackground(background);
//		scroll.setStyle("-fx-background-color:transparent");
//		scroll.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent; -fx-border-width: 0; -fx-border-insets: 0;");
//		scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
	    scroll.setFocusTraversable(false);
		vBoxScore.getChildren().addAll(vBox);
//		vBoxScore.setStyle("-fx-background-color:red");
		vBoxScore.setBackground(background);
		scroll.setContent(vBox); 
//		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//	    scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
	}

	public static void main(String[] args) {
		UserConnectData data = UserConnectData.getInstance();
		System.out.println(data.pullAllUserdata().toString());
		List<DataTable> listPlayer = new ArrayList<DataTable>(data.pullAllUserdata());
		sortScore(listPlayer);
		System.out.println(listPlayer);
	}

}
