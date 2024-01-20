package view;

/**
 * Sample Skeleton for 'ProjectPreview.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Project;
import model.Team;

public class ProjectPreviewController extends AnchorPane {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rectanglePreviewWindow"
    private Rectangle rectanglePreviewWindow; // Value injected by FXMLLoader

    @FXML // fx:id="buttonManageTeam"
    private Button buttonManageTeam; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldProjectName"
    private Text textFieldProjectName; // Value injected by FXMLLoader

    private KanbanBoardViewController kanbanBoardViewController;
    private TeamViewController teamViewController;

    private Project project;
    private Team team;

    private Stage kanbanBoardStage;
    private Stage manageTeamStage;
    private MainController mainController;

    public ProjectPreviewController(MainController pMC, Team team, Project project) {
        this.team = team;
        this.project = project;
        this.mainController = pMC;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectPreview.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void manageTeam(ActionEvent event) {
        teamViewController = new TeamViewController(this.mainController);
        Scene scene = new Scene(teamViewController, 600, 400);
        manageTeamStage.setScene(scene);
        manageTeamStage.setTitle("Team verwalten");
        manageTeamStage.show();
    }

    @FXML
    void showKanbanBoard(MouseEvent event) {
        kanbanBoardViewController = new KanbanBoardViewController(this.mainController, project, team);
        Scene scene = new Scene(kanbanBoardViewController, 900, 700);
        kanbanBoardStage.setScene(scene);
        kanbanBoardStage.setTitle("KanbanBoard");
        kanbanBoardStage.show();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rectanglePreviewWindow != null : "fx:id=\"rectanglePreviewWindow\" was not injected: check your FXML file 'ProjectPreview.fxml'.";
        assert buttonManageTeam != null : "fx:id=\"buttonManageTeam\" was not injected: check your FXML file 'ProjectPreview.fxml'.";
        kanbanBoardStage = new Stage();
        manageTeamStage = new Stage();
        this.textFieldProjectName.setText(this.project.getName());
    }
}

