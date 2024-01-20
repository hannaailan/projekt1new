package view;
/**
 * Sample Skeleton for 'AboutView.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AboutViewController extends VBox {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="labelProjectName"
    private Text labelProjectName; // Value injected by FXMLLoader

    @FXML // fx:id="labelAuthors"
    private Text labelAuthors; // Value injected by FXMLLoader

    @FXML // fx:id="labelVersion"
    private Text labelVersion; // Value injected by FXMLLoader

    @FXML // fx:id="labelProjectNameVal"
    private Label labelProjectNameVal; // Value injected by FXMLLoader

    @FXML // fx:id="labelAuthorsVal"
    private Text labelAuthorsVal; // Value injected by FXMLLoader

    @FXML // fx:id="labelVersionVal"
    private Text labelVersionVal; // Value injected by FXMLLoader

    public AboutViewController(MainController pMainController) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert labelProjectName != null : "fx:id=\"labelProjectName\" was not injected: check your FXML file 'AboutView.fxml'.";
        assert labelAuthors != null : "fx:id=\"labelAuthors\" was not injected: check your FXML file 'AboutView.fxml'.";
        assert labelVersion != null : "fx:id=\"labelVersion\" was not injected: check your FXML file 'AboutView.fxml'.";
        assert labelProjectNameVal != null : "fx:id=\"labelProjectNameVal\" was not injected: check your FXML file 'AboutView.fxml'.";
        assert labelAuthorsVal != null : "fx:id=\"labelAuthorsVal\" was not injected: check your FXML file 'AboutView.fxml'.";
        assert labelVersionVal != null : "fx:id=\"labelVersionVal\" was not injected: check your FXML file 'AboutView.fxml'.";

    }
}
