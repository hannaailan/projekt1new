package view;

import controller.MainController;
import controller.StatisticController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Sample Skeleton for 'StatisticsView.fxml' Controller Class
 */

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Developer;
import model.DeveloperModel;
import model.Project;
import model.Team;

public class StatisticsViewController extends AnchorPane {


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuItemAbout"
    private MenuItem menuItemAbout; // Value injected by FXMLLoader

    @FXML // fx:id="labelFirstHeading"
    private Label labelFirstHeading; // Value injected by FXMLLoader

    @FXML // fx:id="choiceBoxSelectProject"
    private ChoiceBox<?> choiceBoxSelectProject; // Value injected by FXMLLoader

    @FXML // fx:id="buttonRevealStatistics"
    private Button buttonRevealStatistics; // Value injected by FXMLLoader

    @FXML // fx:id="labelTasks"
    private Label labelTasks; // Value injected by FXMLLoader

    @FXML // fx:id="labelDevelopers"
    private Label labelDevelopers; // Value injected by FXMLLoader

    @FXML // fx:id="labelSecondHeading"
    private Label labelSecondHeading; // Value injected by FXMLLoader

    @FXML // fx:id="tableViewRanking"
    private TableView<DeveloperModel> tableViewRanking; // Value injected by FXMLLoader

    @FXML // fx:id="tableColoumnRank"
    private TableColumn<DeveloperModel,Integer> tableColoumnRank; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnDevID"
    private TableColumn<DeveloperModel,Integer> tableColumnDevID; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnWorkers"
    private TableColumn<DeveloperModel,String> tableColumnWorkers; // Value injected by FXMLLoader

    @FXML // fx:id="tableColumnTaskCount"
    private TableColumn<DeveloperModel,Integer> tableColumnTaskCount; // Value injected by FXMLLoader

    @FXML // fx:id="labelMinDevs"
    private Label labelMinDevs; // Value injected by FXMLLoader

    @FXML // fx:id="labelMaxDevs"
    private Label labelMaxDevs; // Value injected by FXMLLoader

    @FXML // fx:id="labelAvgDevs"
    private Label labelAvgDevs; // Value injected by FXMLLoader

    @FXML // fx:id="labelMinTasks"
    private Label labelMinTasks; // Value injected by FXMLLoader

    @FXML // fx:id="labelMaxTasks"
    private Label labelMaxTasks; // Value injected by FXMLLoader

    @FXML // fx:id="labelAvgTasks"
    private Label labelAvgTasks; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetMinDevs"
    private TextField textFieldGetMinDevs; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetMaxDevs"
    private TextField textFieldGetMaxDevs; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetAvgDevs"
    private TextField textFieldGetAvgDevs; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetMinTasks"
    private TextField textFieldGetMinTasks; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetMaxTasks"
    private TextField textFieldGetMaxTasks; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldGetAvgTasks"
    private TextField textFieldGetAvgTasks; // Value injected by FXMLLoader

    @FXML // fx:id="labelStatistics"
    private Label labelStatistics; // Value injected by FXMLLoader


    private MainController mainController;
    private StatisticController statisticController;
    private List<Project> projects;
    private List<DeveloperModel> developerModels;
    private List<Developer> developers;
    private List<Team> teams;

    public StatisticsViewController(MainController pMainController) {
        this.mainController = pMainController;
        this.statisticController = mainController.getStatisticController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticsView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initializeChoiceBoxSelectProject() {
        projects = new LinkedList<>();
        teams = mainController.getCompany().getTeams();
        for (Team team : teams) {
            projects.add(team.getActiveProject());
        }
        for (Team team : teams) {
            projects.addAll(team.getArchivedProjects());
        }
        LinkedList boxList = new LinkedList<String>();
        for (int i = 0; i < projects.size(); i++) {
            boxList.add(projects.get(i).getName());
        }
        choiceBoxSelectProject.setItems(FXCollections.observableList(boxList));
    }

    private void initializeTableViewRanking(){
        developers = mainController.getCompany().getDevs();
        tableColoumnRank.setCellValueFactory(new PropertyValueFactory<DeveloperModel,Integer>("ranked"));
        tableColumnDevID.setCellValueFactory(new PropertyValueFactory<DeveloperModel,Integer>("devID"));
        tableColumnWorkers.setCellValueFactory(new PropertyValueFactory<DeveloperModel,String>("name"));
        tableColumnTaskCount.setCellValueFactory(new PropertyValueFactory<DeveloperModel,Integer>("countTasks"));
        developerModels = statisticController.calculateRanking(developers);
        ObservableList<DeveloperModel> modelObservableList = FXCollections.observableList(developerModels);
        tableViewRanking.setItems(modelObservableList);
    }

    @FXML
    void revealStatistics(ActionEvent event) {

        int help = choiceBoxSelectProject.getSelectionModel().getSelectedIndex();
        if(help >= 0) {
            int[] results = statisticController.calculateStatistic(projects.get(help));
            textFieldGetMinDevs.setText(String.valueOf(results[0]));
            textFieldGetMaxDevs.setText(String.valueOf(results[1]));
            textFieldGetAvgDevs.setText(String.valueOf(results[2]));
            textFieldGetMinTasks.setText(String.valueOf(results[3]));
            textFieldGetMaxTasks.setText(String.valueOf(results[4]));
            textFieldGetAvgTasks.setText(String.valueOf(results[5]));
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuItemAbout != null : "fx:id=\"menuItemAbout\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelFirstHeading != null : "fx:id=\"labelFirstHeading\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert choiceBoxSelectProject != null : "fx:id=\"choiceBoxSelectProject\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert buttonRevealStatistics != null : "fx:id=\"buttonShowStatistics\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelTasks != null : "fx:id=\"labelTasks\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelDevelopers != null : "fx:id=\"labelDevelopers\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelSecondHeading != null : "fx:id=\"labelSecondHeading\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert tableViewRanking != null : "fx:id=\"tableViewRanking\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert tableColoumnRank != null : "fx:id=\"tableColoumnRank\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert tableColumnDevID != null : "fx:id=\"tableColumnDevID\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert tableColumnWorkers != null : "fx:id=\"tableColumnWorkers\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert tableColumnTaskCount != null : "fx:id=\"tableColumnTaskCount\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelMinDevs != null : "fx:id=\"labelMinDevs\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelMaxDevs != null : "fx:id=\"labelMaxDevs\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelAvgDevs != null : "fx:id=\"labelAvgDevs\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelMinTasks != null : "fx:id=\"labelMinTasks\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelMaxTasks != null : "fx:id=\"labelMaxTasks\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelAvgTasks != null : "fx:id=\"labelAvgTasks\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert textFieldGetMinDevs != null : "fx:id=\"textFieldGetMinDevs\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert textFieldGetMaxDevs != null : "fx:id=\"textFieldGetMaxDevs\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert textFieldGetAvgDevs != null : "fx:id=\"textFieldGetAvgDevs\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert textFieldGetMinTasks != null : "fx:id=\"textFieldGetMinTasks\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert textFieldGetMaxTasks != null : "fx:id=\"textFieldGetMaxTasks\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert textFieldGetAvgTasks != null : "fx:id=\"textFieldGetAvgTasks\" was not injected: check your FXML file 'StatisticsView.fxml'.";
        assert labelStatistics != null : "fx:id=\"labelStatistics\" was not injected: check your FXML file 'StatisticsView.fxml'.";

        choiceBoxSelectProject = new ChoiceBox();
        buttonRevealStatistics = new Button();
        tableViewRanking = new TableView<>();

        initializeChoiceBoxSelectProject();
        initializeTableViewRanking();

    }
}



