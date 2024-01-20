package view;


import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TeamViewController extends VBox{
	/**
	 * Sample Skeleton for 'TeamView.fxml' Controller Class
	 */
	    @FXML // ResourceBundle that was given to the FXMLLoader
	    private ResourceBundle resources;

	    @FXML // URL location of the FXML file that was given to the FXMLLoader
	    private URL location;

	    @FXML // fx:id="VBox"
	    private VBox VBox; // Value injected by FXMLLoader

	    @FXML // fx:id="MenuBar"
	    private MenuBar MenuBar; // Value injected by FXMLLoader

	    @FXML // fx:id="MenuHelp"
	    private Menu MenuHelp; // Value injected by FXMLLoader

	    @FXML // fx:id="AnchorPane"
	    private AnchorPane AnchorPane; // Value injected by FXMLLoader

	    @FXML // fx:id="ButtonAdjust"
	    private Button ButtonAdjust; // Value injected by FXMLLoader

	    @FXML // fx:id="TextName"
	    private Text TextName; // Value injected by FXMLLoader

	    @FXML // fx:id="TextTeamLeader"
	    private Text TextTeamLeader; // Value injected by FXMLLoader

	    @FXML // fx:id="TextActiveProject"
	    private Text TextActiveProject; // Value injected by FXMLLoader

	    @FXML // fx:id="TextFieldName"
	    private TextField TextFieldName; // Value injected by FXMLLoader

	    @FXML // fx:id="TextFieldTeamLeader"
	    private TextField TextFieldTeamLeader; // Value injected by FXMLLoader

	    @FXML // fx:id="TextFieldActiveProject"
	    private TextField TextFieldActiveProject; // Value injected by FXMLLoader

	    @FXML // fx:id="ButtonConfirm"
	    private Button ButtonConfirm; // Value injected by FXMLLoader

	    @FXML // fx:id="ListViewArchivedProject"
	    private ListView<?> ListViewArchivedProject; // Value injected by FXMLLoader

	    @FXML // fx:id="LabelArchivedProject"
	    private Label LabelArchivedProject; // Value injected by FXMLLoader
    	
	    public TeamViewController(MainController pMainController) {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamView.fxml"));
	        loader.setRoot(this);
	        loader.setController(this);
	        try {
	            loader.load();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }



	 @FXML
	    void adjust(ActionEvent event) {
			TextFieldActiveProject.setEditable(false);
		 	TextFieldName.setEditable(false);
		 	TextFieldTeamLeader.setEditable(false);
		 	TextFieldActiveProject.setEditable(false);
	    }

	    @FXML
	    void confirm(ActionEvent event) {
			TextFieldActiveProject.setEditable(true);
			TextFieldName.setEditable(true);
			TextFieldTeamLeader.setEditable(true);
			TextFieldActiveProject.setEditable(true);

	    }

//	    @FXML
//	    void help(ActionEvent event) {
//
//	    }

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert VBox != null : "fx:id=\"VBox\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert MenuBar != null : "fx:id=\"MenuBar\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert MenuHelp != null : "fx:id=\"MenuHelp\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert AnchorPane != null : "fx:id=\"AnchorPane\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert ButtonAdjust != null : "fx:id=\"ButtonAdjust\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert TextName != null : "fx:id=\"TextName\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert TextTeamLeader != null : "fx:id=\"TextTeamLeader\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert TextActiveProject != null : "fx:id=\"TextActiveProject\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert TextFieldName != null : "fx:id=\"TextFieldName\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert TextFieldTeamLeader != null : "fx:id=\"TextFieldTeamLeader\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert TextFieldActiveProject != null : "fx:id=\"TextFieldActiveProject\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert ButtonConfirm != null : "fx:id=\"ButtonConfirm\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert ListViewArchivedProject != null : "fx:id=\"ListViewArchivedProject\" was not injected: check your FXML file 'TeamView.fxml'.";
		assert LabelArchivedProject != null : "fx:id=\"LabelArchivedProject\" was not injected: check your FXML file 'TeamView.fxml'.";

	}
}


