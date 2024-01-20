package view;

/**
 * Sample Skeleton for 'CreateCommentView.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.*;

public class CreateCommentViewController extends VBox {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuItemAbout"
    private MenuItem menuItemAbout; // Value injected by FXMLLoader

    @FXML // fx:id="textAreaContent"
    private TextArea textAreaContent; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonChooseDeveloper"
    private MenuButton menuButtonChooseDeveloper; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCreate"
    private Button buttonCreate; // Value injected by FXMLLoader

    private CreateCommentViewController createCommentViewController;
    private AboutViewController aboutViewController;
    private CreateTeamViewController createTeamViewController;
    
    private Stage aboutStage;
    private Stage chooseDeveloperStage;
    private Stage createStage;

    private MainController mainController;


    public CreateCommentViewController() {
        this.mainController = new MainController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateCommentView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void about(ActionEvent event) {
        aboutViewController = new AboutViewController(this.mainController);
        Scene sc = new Scene(aboutViewController, 381, 311);
        aboutStage.setScene(sc);
        aboutStage.setTitle("about");
        aboutStage.show();
    }

    @FXML
    void chooseDeveloper(ActionEvent event) {

    }

    @FXML
    void create(ActionEvent event) {
        createCommentViewController = new CreateCommentViewController();
        Scene sc = new Scene(createCommentViewController, 650, 300);
        createStage.setScene(sc);
        createStage.setTitle("Comment erstellen");
        createStage.show();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuItemAbout != null : "fx:id=\"menuItemAbout\" was not injected: check your FXML file 'CreateCommentView.fxml'.";
        assert textAreaContent != null : "fx:id=\"textAreaContent\" was not injected: check your FXML file 'CreateCommentView.fxml'.";
        assert menuButtonChooseDeveloper != null : "fx:id=\"menuButtonChooseDeveloper\" was not injected: check your FXML file 'CreateCommentView.fxml'.";
        assert buttonCreate != null : "fx:id=\"buttonCreate\" was not injected: check your FXML file 'CreateCommentView.fxml'.";

    }
}

