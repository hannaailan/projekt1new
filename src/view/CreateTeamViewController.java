package view;

/**
 * Sample Skeleton for 'CreateTeamView.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
        import javafx.scene.control.MenuItem;
        import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateTeamViewController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuItemAbout"
    private MenuItem menuItemAbout; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCreate"
    private Button buttonCreate; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetName"
    private TextField textFieldGetName; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetTeamleader"
    private TextField textFieldGetTeamleader; // Value injected by FXMLLoader

    @FXML // fx:id="textCheckInputs"
    private Text textCheckInputs; // Value injected by FXMLLoader

    MainController mainController;
    ManageTeamsViewController manageTeamsViewController;

    public CreateTeamViewController(MainController pMC, ManageTeamsViewController pMTVC) {
        this.mainController = pMC;
        this.manageTeamsViewController = pMTVC;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreateTeamView.fxml"));
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

    }

    @FXML
    void create(ActionEvent event) {
        try {
            String name = textFieldGetName.getText();
            String teamLeader = textFieldGetTeamleader.getText();
            this.mainController.getTeamController().createTeam(mainController.getCompany(), name, teamLeader);
            //this.manageTeamsViewController.refreshTeam();//TODO refresh Methode in manageTeamsViewController ergänzen
        } catch (Exception e) {
            textCheckInputs.setVisible(true);
            e.printStackTrace();
        }
        Stage stage = (Stage) buttonCreate.getScene().getWindow();
        stage.close();
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuItemAbout != null : "fx:id=\"menuItemAbout\" was not injected: check your FXML file 'CreateTeamView.fxml'.";
        assert buttonCreate != null : "fx:id=\"buttonCreate\" was not injected: check your FXML file 'CreateTeamView.fxml'.";
        assert textFieldGetName != null : "fx:id=\"textFieldGetName\" was not injected: check your FXML file 'CreateTeamView.fxml'.";
        assert textFieldGetTeamleader != null : "fx:id=\"textFieldGetTeamleader\" was not injected: check your FXML file 'CreateTeamView.fxml'.";
        assert textCheckInputs != null : "fx:id=\"textCheckInputs\" was not injected: check your FXML file 'CreateTeamView.fxml'.";

    }
}
