package view;

import controller.MainController;
import controller.TeamController;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


/**
 * Sample Skeleton for 'ManageTeamsView.fxml' Controller Class
 */

import java.net.URL;
		import java.util.ResourceBundle;
		import javafx.event.ActionEvent;
		import javafx.fxml.FXML;
		import javafx.scene.control.Button;
		import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class ManageTeamsViewController<Team> extends VBox {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	private TableView<Team> table;

	@FXML // fx:id="tableTeam"
	private TableColumn<Team, String> tableTeam; // Value injected by FXMLLoader

	@FXML // fx:id="tableLeader"
	private TableColumn<Team, String> tableLeader; // Value injected by FXMLLoader

	@FXML // fx:id="tableProject"
	private TableColumn<Team, String> tableProject; // Value injected by FXMLLoader

	@FXML // fx:id="buttonAdd"
	private Button buttonAdd; // Value injected by FXMLLoader

	@FXML // fx:id="buttonCreate"
	private Button buttonCreate; // Value injected by FXMLLoader

	@FXML // fx:id="buttpnEdit"
	private Button buttpnEdit; // Value injected by FXMLLoader

	@FXML // fx:id="buttonDelete"
	private Button buttonDelete; // Value injected by FXMLLoader

	private CreateTeamViewController createTeamViewController;
	private MainController mainController;
	private TeamViewController teamViewController;
	private TeamController teamController;
	private CreateProjectViewController createProjectViewController;
	private ProjectsViewController projectsViewController;

	private Stage createProjectStage = new Stage();
	private Stage manageTeamStage = new Stage();
	private Stage createTeamStage = new Stage();

	public ManageTeamsViewController(MainController pMainController, ProjectsViewController projectsViewController) {
		mainController = pMainController;
		this.projectsViewController = projectsViewController;
		teamController = mainController.getTeamController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageTeamsView.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void add(ActionEvent event) {
		createTeamViewController = new CreateTeamViewController(mainController,this);
		Scene scene = new Scene(createTeamViewController, 489.0, 183.0);
		createTeamStage.setScene(scene);
		createTeamStage.setTitle("Team erstellen");
		createTeamStage.show();
	}

	@FXML
	void create(ActionEvent event) {
		createProjectViewController = new CreateProjectViewController(mainController,projectsViewController);
		Scene scene = new Scene(createProjectViewController, 489.0, 359.0);
		createProjectStage.setScene(scene);
		createProjectStage.setTitle("Projekt erstellen");
		createProjectStage.show();
	}

	@FXML
	void delete(ActionEvent event) {
	}

	@FXML
	void edit(ActionEvent event) {
		teamViewController = new TeamViewController(mainController);
		Scene scene = new Scene(teamViewController, 600, 400);
		manageTeamStage.setScene(scene);
		manageTeamStage.setTitle("Team verwalten");
		manageTeamStage.show();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert tableTeam != null : "fx:id=\"tableTeam\" was not injected: check your FXML file 'ManageTeamsView.fxml'.";
		assert tableLeader != null : "fx:id=\"tableLeader\" was not injected: check your FXML file 'ManageTeamsView.fxml'.";
		assert tableProject != null : "fx:id=\"tableProject\" was not injected: check your FXML file 'ManageTeamsView.fxml'.";
		assert buttonAdd != null : "fx:id=\"buttonAdd\" was not injected: check your FXML file 'ManageTeamsView.fxml'.";
		assert buttonCreate != null : "fx:id=\"buttonCreate\" was not injected: check your FXML file 'ManageTeamsView.fxml'.";
		assert buttpnEdit != null : "fx:id=\"buttpnEdit\" was not injected: check your FXML file 'ManageTeamsView.fxml'.";
		assert buttonDelete != null : "fx:id=\"buttonDelete\" was not injected: check your FXML file 'ManageTeamsView.fxml'.";

		tableTeam.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
		tableTeam.setCellValueFactory(new PropertyValueFactory<Team, String>("projectLeader"));
		tableTeam.setCellValueFactory(new PropertyValueFactory<Team, String>("projectName"));

		// table.setItems(); Noch eine Observalbe list rein und man kann das testen

	}
}

