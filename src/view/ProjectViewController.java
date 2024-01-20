package view;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import controller.ProjectController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Project;
import model.Team;

public class ProjectViewController extends VBox {

        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="buttonAdjust"
        private Button buttonAdjust; // Value injected by FXMLLoader

        @FXML // fx:id="buttonConfrimed"
        private Button buttonConfrimed; // Value injected by FXMLLoader

        @FXML // fx:id="buttonDelete"
        private Button buttonDelete; // Value injected by FXMLLoader

        @FXML // fx:id="buttonArchived"
        private Button buttonArchived; // Value injected by FXMLLoader

        @FXML // fx:id="textName"
        private TextField textName; // Value injected by FXMLLoader

        @FXML // fx:id="textTeam"
        private TextField textTeam; // Value injected by FXMLLoader

        @FXML // fx:id="textTeam"
        private DatePicker dateDeadline;

        @FXML // fx:id="textStatus"
        private TextField textStatus; // Value injected by FXMLLoader

        @FXML // fx:id="textDescription"
        private TextField textDescription; // Value injected by FXMLLoader

        private ProjectController projectController;
        private MainController mainController;

        private Team team;
        private Project currentProject;

        public ProjectViewController(MainController mainController, Team team) {
            currentProject = team.getActiveProject();  //
            this.mainController = mainController;
            projectController = mainController.getProjectController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProjectView.fxml"));
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
           textTeam.setEditable(true);
           dateDeadline.setEditable(true);
           textDescription.setEditable(true);
           textName.setEditable(true);
           textStatus.setEditable(true);
           projectController.editProject(currentProject, textTeam.getText(), textName.getText(),  dateDeadline.getValue());
        }


        @FXML
        void confirmed(ActionEvent event) {
            textTeam.setEditable(false);
            dateDeadline.setEditable(false);
            textDescription.setEditable(false);
            textName.setEditable(false);
            textStatus.setEditable(false);
        }

        @FXML
        void delete(ActionEvent event) {
            projectController.deleteProject(team);
        }

        @FXML
        void archive(ActionEvent event) {
            projectController.archiveProject(team);
        }

        @FXML // This method is called by the FXMLLoader when initialization is complete
        void initialize() {
            assert buttonAdjust != null : "fx:id=\"buttonAdjust\" was not injected: check your FXML file 'ProjectView.fxml'.";
            assert buttonConfrimed != null : "fx:id=\"buttonConfrimed\" was not injected: check your FXML file 'ProjectView.fxml'.";
            assert buttonDelete != null : "fx:id=\"buttonDelete\" was not injected: check your FXML file 'ProjectView.fxml'.";
            assert buttonArchived != null : "fx:id=\"buttonArchived\" was not injected: check your FXML file 'ProjectView.fxml'.";
            assert textName != null : "fx:id=\"textName\" was not injected: check your FXML file 'ProjectView.fxml'.";
            assert textTeam != null : "fx:id=\"textTeam\" was not injected: check your FXML file 'ProjectView.fxml'.";
            assert dateDeadline != null : "fx:id=\"dateDeadline\" was not injected: check your FXML file 'ProjectView.fxml'.";
            assert textStatus != null : "fx:id=\"textStatus\" was not injected: check your FXML file 'ProjectView.fxml'.";
            assert textDescription != null : "fx:id=\"textDescription\" was not injected: check your FXML file 'ProjectView.fxml'.";

            dateDeadline.setEditable(false);
        }

}
