package view;

/**
 * Sample Skeleton for 'CreateTaskView.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Project;
import model.Team;

public class CreateTaskViewController extends VBox {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuItemAbout"
    private MenuItem menuItemAbout; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetTaskname"
    private TextField textFieldGetTaskname; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetDeadline"
    private TextField textFieldGetDeadline; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCreate"
    private Button buttonCreate; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetDesription"
    private TextArea textFieldGetDesription; // Value injected by FXMLLoader

    @FXML // fx:id="datePickerDeadline"
    private DatePicker datePickerDeadline; // Value injected by FXMLLoader

    @FXML // fx:id="textCheckInput"
    private Text textCheckInput; // Value injected by FXMLLoader

    private MainController mainController;
    private Project project;
    private KanbanBoardViewController kanbanBoardViewController;

    public CreateTaskViewController(MainController mainController, KanbanBoardViewController kanbanBoardViewController, Project project) {
        this.mainController = mainController;
        this.project = project;
        this.kanbanBoardViewController = kanbanBoardViewController;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateTaskView.fxml"));
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
            String name = textFieldGetTaskname.getText();
            LocalDate deadline = datePickerDeadline.getValue();
            String description = textFieldGetDesription.getText();
            System.out.println("Name:" + name + " Team:" + " Deadline:" + deadline + " Beschreibung:" + description);//TODO testausgabe entfernen
            this.mainController.getTaskController().createTask(name, description, deadline, this.project);
            this.kanbanBoardViewController.refreshTask();
        } catch (Exception e) {
            textCheckInput.setVisible(true);
            e.printStackTrace();
        }
    }

    @FXML
    void getDeadline(ActionEvent event) {

    }

    @FXML
    void getDescription(MouseEvent event) {

    }

    @FXML
    void getTaskname(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuItemAbout != null : "fx:id=\"menuItemAbout\" was not injected: check your FXML file 'CreateTaskView.fxml'.";
        assert textFieldGetTaskname != null : "fx:id=\"textFieldGetTaskname\" was not injected: check your FXML file 'CreateTaskView.fxml'.";
        assert textFieldGetDeadline != null : "fx:id=\"textFieldGetDeadline\" was not injected: check your FXML file 'CreateTaskView.fxml'.";
        assert buttonCreate != null : "fx:id=\"buttonCreate\" was not injected: check your FXML file 'CreateTaskView.fxml'.";
        assert textFieldGetDesription != null : "fx:id=\"textFieldGetDesription\" was not injected: check your FXML file 'CreateTaskView.fxml'.";

    }
}

