/**
 * Sample Skeleton for 'ProjectsView.fxml' Controller Class
 */

package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Project;
import model.Team;

public class ProjectsViewController extends AnchorPane implements ProjectAUI{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuItemAbout"
    private MenuItem menuItemAbout; // Value injected by FXMLLoader

    @FXML // fx:id="buttonAddProject"
    private Button buttonAddProject; // Value injected by FXMLLoader

    @FXML // fx:id="buttonManageTeams"
    private Button buttonManageTeams; // Value injected by FXMLLoader

    @FXML // fx:id="buttonShowStatistics"
    private Button buttonShowStatistics; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneProjectsView"
    private GridPane gridPaneProjectsView; // Value injected by FXMLLoader

    @FXML // fx:id="panePlaceholder"
    private AnchorPane panePlaceholder; // Value injected by FXMLLoader
    private Stage manageTeamStage;

    private MainController mainController;
    private ManageTeamsViewController manageTeamsViewController;
    private StatisticsViewController statisticsViewController;
    private CreateProjectViewController createProjectViewController;
    private AboutViewController aboutViewController;

    private Stage statisticsStage = new Stage();
    private Stage manageTeamsStage = new Stage();
    private Stage createProjectStage = new Stage();
    private Stage aboutStage = new Stage();

    public ProjectsViewController() {
        this.mainController = new MainController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectsView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
        try {
            Team team = new Team("einname", "einleader");
            Project project = new Project("ein Name", "beschreibung", LocalDate.now());
            ProjectPreviewController ppc = new ProjectPreviewController(this.mainController, team, project);
            GridPane.setHalignment(ppc, HPos.CENTER);
            GridPane.setValignment(ppc, VPos.CENTER);
            gridPaneProjectsView.add(ppc, 0, 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        **/

    }

    public void refreshProjects() {
        gridPaneProjectsView.getChildren().retainAll(gridPaneProjectsView.getChildren().get(0));

        int row = 0;
        int column = 0;
        LinkedList<Team> listTeams = this.mainController.getCompany().getTeams();
        for(Team currentTeam : listTeams) {
            Project currentProject = currentTeam.getActiveProject();
            if (currentProject != null) {
                ProjectPreviewController ppc = new ProjectPreviewController(this.mainController, currentTeam, currentProject);
                GridPane.setHalignment(ppc, HPos.CENTER);
                GridPane.setValignment(ppc, VPos.CENTER);
                gridPaneProjectsView.add(ppc, column, row);
                if (column == 2) {
                    column = 0;
                    row++;
                } else {
                    column++;
                }

            }
        }
    }

    @FXML
    void addProject(ActionEvent event) {
        createProjectViewController = new CreateProjectViewController(this.mainController, this);
        Scene scene = new Scene(createProjectViewController, 489.0, 359.0);
        createProjectStage.setScene(scene);
        createProjectStage.setTitle("Projekt erstellen");
        createProjectStage.show();
    }

    @FXML
    void manageTeams(ActionEvent event) {
        manageTeamsViewController = new ManageTeamsViewController(this.mainController, this);
        Scene scene = new Scene(manageTeamsViewController, 673.0, 441.0);
        manageTeamsStage.setScene(scene);
        manageTeamsStage.setTitle("Teams verwalten");
        manageTeamsStage.show();

    }

    @FXML
    void showAbout(ActionEvent event) {
        aboutViewController = new AboutViewController(this.mainController);
        Scene scene = new Scene(aboutViewController, 381, 311);
        aboutStage.setScene(scene);
        aboutStage.setTitle("About");
        aboutStage.show();
    }

    @FXML
    void showStatistics(ActionEvent event) {
        statisticsViewController = new StatisticsViewController(this.mainController);
        Scene scene = new Scene(statisticsViewController, 700.0, 560.0);
        statisticsStage.setScene(scene);
        statisticsStage.setTitle("Statistiken");
        statisticsStage.show();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuItemAbout != null : "fx:id=\"menuItemAbout\" was not injected: check your FXML file 'ProjectsView.fxml'.";
        assert buttonAddProject != null : "fx:id=\"buttonAddProject\" was not injected: check your FXML file 'ProjectsView.fxml'.";
        assert buttonManageTeams != null : "fx:id=\"buttonManageTeams\" was not injected: check your FXML file 'ProjectsView.fxml'.";
        assert buttonShowStatistics != null : "fx:id=\"buttonShowStatistics\" was not injected: check your FXML file 'ProjectsView.fxml'.";
        assert gridPaneProjectsView != null : "fx:id=\"gridPaneProjectsView\" was not injected: check your FXML file 'ProjectsView.fxml'.";
    }
}
