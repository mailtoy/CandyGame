package application;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameFX {
	 private final Parent rootPane ; // or any other kind of pane, or  Group...

	    public GameFX() {

	        rootPane = new BorderPane();

	        // build UI, register event handlers, etc etc

	    }

	    public Pane getRootPane() {
	        return (Pane) rootPane ;
	    }

	    // other methods you may need to access, etc...


}
