/**
 * Sample Skeleton for 'TaskView.fxml' Controller Class
 */

package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TaskViewController extends Parent {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="b_edittask"
	private Button b_edittask; // Value injected by FXMLLoader

	@FXML // fx:id="b_taketask"
	private Button b_taketask; // Value injected by FXMLLoader

	@FXML // fx:id="b_taskdone"
	private Button b_taskdone; // Value injected by FXMLLoader

	@FXML // fx:id="b_abbruch"
	private Button b_abbruch; // Value injected by FXMLLoader

	@FXML // fx:id="l_taskname"
	private Label l_taskname; // Value injected by FXMLLoader

	@FXML // fx:id="scrollpane"
	private ScrollPane scrollpane; // Value injected by FXMLLoader

	@FXML // fx:id="ancpane"
	private AnchorPane ancpane; // Value injected by FXMLLoader

	@FXML // fx:id="l_developer"
	private Label l_developer; // Value injected by FXMLLoader

	@FXML // fx:id="l_deadline"
	private Label l_deadline; // Value injected by FXMLLoader

	@FXML // fx:id="tf_taskdescription"
	private TextArea tf_taskdescription; // Value injected by FXMLLoader

	@FXML // fx:id="noteGrid"
	private GridPane noteGrid; // Value injected by FXMLLoader

	@FXML // fx:id="b_Commenterstellen"
	private Button b_Commenterstellen; // Value injected by FXMLLoader

	@FXML // fx:id="l_taskdescription"
	private Label l_taskdescription; // Value injected by FXMLLoader

	@FXML // fx:id="tf_taskname"
	private TextField tf_taskname; // Value injected by FXMLLoader

	private TaskViewController taskViewController;
	private CreateCommentViewController createCommentViewController;

	private Stage changeTaskStage;
	private Stage createNoteStage;
	private Stage takeTaskStage;
	private Stage taskDoneStage;
	private Stage unassingTaskStage;

	public TaskViewController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TaskView.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void changeTask(ActionEvent event) {
		taskViewController = new TaskViewController();
		Scene sc = new Scene(taskViewController, 640, 400);
		changeTaskStage.setScene(sc);
		changeTaskStage.setTitle("Task ändern");
		changeTaskStage.show();

	}

	@FXML
	void createNote(ActionEvent event) {
		createCommentViewController = new CreateCommentViewController();
		Scene sc = new Scene(createCommentViewController,650, 300);
		createNoteStage.setScene(sc);
		createNoteStage.setTitle("Note erstellen");
		createNoteStage.show();
	}

	@FXML
	void takeTask(ActionEvent event) {
		taskViewController = new TaskViewController();
		Scene sc = new Scene(taskViewController, 640, 400);
		takeTaskStage.setScene(sc);
		takeTaskStage.setTitle("Task nehmen");
		takeTaskStage.show();
	}

	@FXML
	void taskDone(ActionEvent event) {
		taskViewController = new TaskViewController();
		Scene sc = new Scene(taskViewController, 640, 400 );
		taskDoneStage.setScene(sc);
		taskDoneStage.setTitle("Task ist fertig");
		taskDoneStage.show();
	}

	@FXML
	void unassingTask(ActionEvent event) {
		taskViewController = new TaskViewController();
		Scene sc = new Scene(taskViewController, 640, 400 );
		unassingTaskStage.setScene(sc);
		unassingTaskStage.setTitle("Task ist nicht zugewiesen");
		unassingTaskStage.show();

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert b_edittask != null : "fx:id=\"b_edittask\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert b_taketask != null : "fx:id=\"b_taketask\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert b_taskdone != null : "fx:id=\"b_taskdone\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert b_abbruch != null : "fx:id=\"b_abbruch\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert l_taskname != null : "fx:id=\"l_taskname\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert scrollpane != null : "fx:id=\"scrollpane\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert ancpane != null : "fx:id=\"ancpane\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert l_developer != null : "fx:id=\"l_developer\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert l_deadline != null : "fx:id=\"l_deadline\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert tf_taskdescription != null : "fx:id=\"tf_taskdescription\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert noteGrid != null : "fx:id=\"noteGrid\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert b_Commenterstellen != null : "fx:id=\"b_Commenterstellen\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert l_taskdescription != null : "fx:id=\"l_taskdescription\" was not injected: check your FXML file 'TaskView.fxml'.";
		assert tf_taskname != null : "fx:id=\"tf_taskname\" was not injected: check your FXML file 'TaskView.fxml'.";

	}
}
