package view;

/**
 * Sample Skeleton for 'CreateProjectView.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Project;
import model.Team;

public class CreateProjectViewController extends AnchorPane {

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

    @FXML // fx:id="textFieldGetDeadline"
    private TextField textFieldGetDeadline; // Value injected by FXMLLoader

    @FXML // fx:id="menuButtonChooseTeam"
    private MenuButton menuButtonChooseTeam; // Value injected by FXMLLoader

    @FXML // fx:id="textAreaGetDescription"
    private TextArea textAreaGetDescription; // Value injected by FXMLLoader

    @FXML // fx:id="choiceBoxChooseTeam"
    private ChoiceBox<?> choiceBoxChooseTeam; // Value injected by FXMLLoader

    @FXML // fx:id="datePickerDeadline"
    private DatePicker datePickerDeadline; // Value injected by FXMLLoader

    @FXML // fx:id="textCheckInputs"
    private Text textCheckInputs; // Value injected by FXMLLoader


    private MainController mainController;
    private ProjectsViewController projectsViewController;
    private LinkedList<Team> listTeams;

    public CreateProjectViewController(MainController pMainController, ProjectsViewController pProjectsViewController) {
        this.mainController = pMainController;
        this.projectsViewController = pProjectsViewController;
        this.listTeams = this.mainController.getCompany().getTeams();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateProjectView.fxml"));
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
    void chooseTeam(ActionEvent event) {

    }

    @FXML
    void create(ActionEvent event) {
        try {
            int index = choiceBoxChooseTeam.getSelectionModel().getSelectedIndex();
            String name = textFieldGetName.getText();
            Team team = this.listTeams.get(index);
            LocalDate deadline = datePickerDeadline.getValue();
            String description = textAreaGetDescription.getText();
            System.out.println("Name:" + name + " Team:" + team.getName() + " Deadline:" + deadline + " Beschreibung:" + description);//TODO testausgabe entfernen
            //Project tmp = new Project(name, description, deadline);
            this.mainController.getProjectController().createProject(team, name, description, deadline);
            this.projectsViewController.refreshProjects();
        } catch (Exception e) {
            textCheckInputs.setVisible(true);
            e.printStackTrace();
        }
        Stage stage = (Stage) buttonCreate.getScene().getWindow();
        stage.close();
    }

    @FXML
    void getDeadline(ActionEvent event) {

    }

    @FXML
    void getName(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuItemAbout != null : "fx:id=\"menuItemAbout\" was not injected: check your FXML file 'CreateProjectView.fxml'.";
        assert buttonCreate != null : "fx:id=\"buttonCreate\" was not injected: check your FXML file 'CreateProjectView.fxml'.";
        assert textFieldGetName != null : "fx:id=\"textFieldGetName\" was not injected: check your FXML file 'CreateProjectView.fxml'.";
        assert textFieldGetDeadline != null : "fx:id=\"textFieldGetDeadline\" was not injected: check your FXML file 'CreateProjectView.fxml'.";
        assert menuButtonChooseTeam != null : "fx:id=\"menuButtonChooseTeam\" was not injected: check your FXML file 'CreateProjectView.fxml'.";

        LinkedList boxList = new LinkedList<String>();
        for (Team currentTeam : this.listTeams) {
            boxList.add(currentTeam.getName());
        }
        choiceBoxChooseTeam.setItems(FXCollections.observableList(boxList));
    }

}
