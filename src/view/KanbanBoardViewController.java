package view;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Developer;
import model.Project;
import model.Task;
import model.Team;

public class KanbanBoardViewController extends AnchorPane implements TaskAUI{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="buttonManageProjects"
    private Button buttonManageProjects; // Value injected by FXMLLoader

    @FXML // fx:id="buttonManageTeam"
    private Button buttonManageTeam; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCreateTask"
    private Button buttonCreateTask; // Value injected by FXMLLoader

    @FXML // fx:id="buttonManageTask"
    private Button buttonManageTask; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCompleteTask"
    private Button buttonCompleteTask; // Value injected by FXMLLoader

    @FXML // fx:id="buttonAcceptTask"
    private Button buttonAcceptTask; // Value injected by FXMLLoader

    @FXML // fx:id="buttonUnassignTask"
    private Button buttonUnassignTask; // Value injected by FXMLLoader

    @FXML // fx:id="buttonShowDetails"
    private Button buttonShowDetails; // Value injected by FXMLLoader

    @FXML // fx:id="buttonPreviousProject"
    private Button buttonPreviousProject; // Value injected by FXMLLoader

    @FXML // fx:id="labelProjectname"
    private Label labelProjectname; // Value injected by FXMLLoader

    @FXML // fx:id="buttonNextProject"
    private Button buttonNextProject; // Value injected by FXMLLoader

    @FXML // fx:id="vBoxUnassignedEmployees"
    private VBox vBoxUnassignedEmployees; // Value injected by FXMLLoader

    @FXML // fx:id="gridPaneTaskView"
    private GridPane gridPaneTaskView; // Value injected by FXMLLoader

    @FXML // fx:id="listAnalysisProcessing"
    private ListView<String> listAnalysisProcessing; // Value injected by FXMLLoader

    @FXML // fx:id="listAnalysisDone"
    private ListView<String> listAnalysisDone; // Value injected by FXMLLoader

    @FXML // fx:id="listImplementationProcessing"
    private ListView<String> listImplementationProcessing; // Value injected by FXMLLoader

    @FXML // fx:id="listImplementationDone"
    private ListView<String> listImplementationDone; // Value injected by FXMLLoader

    @FXML // fx:id="listTestProcessing"
    private ListView<String> listTestProcessing; // Value injected by FXMLLoader

    @FXML // fx:id="listTestDone"
    private ListView<String> listTestDone; // Value injected by FXMLLoader

    @FXML // fx:id="listNew"
    private ListView<String> listNew; // Value injected by FXMLLoader

    @FXML // fx:id="listFinished"
    private ListView<String> listFinished; // Value injected by FXMLLoader

    @FXML
    private Button buttonCreateComment;

    @FXML
    private ListView<?> listViewUnassignedDevelopers;

    private Project currentProject;
    private Team team;
    private LinkedList<Task> list0;
    private LinkedList<Task> list1;
    private LinkedList<Task> list2;
    private LinkedList<Task> list3;
    private LinkedList<Task> list4;
    private LinkedList<Task> list5;
    private LinkedList<Task> list6;
    private LinkedList<Task> list7;

    private TeamViewController teamViewController;
    private CreateCommentViewController createCommentViewController;
    private CreateTaskViewController createTaskViewController;
    private TaskViewController taskViewController;
    private ProjectViewController projectViewController;
    private Stage manageTeamStage = new Stage();
    private Stage manageProjectStage = new Stage();
    private Stage createCommentStage = new Stage();
    private Stage createTaskStage = new Stage();
    private Stage manageTaskStage = new Stage();

    Developer test1 = new Developer("test1");
    Developer test2 = new Developer("test2");

    private Task lastSelected;

    private MainController mainController;
    public KanbanBoardViewController(MainController mainController, Project project, Team team) {
        this.currentProject = project;
        this.team = team;
        this.mainController = mainController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("KanbanBoardView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshTask() {
        list0 = this.currentProject.getTasks()[0];
        list1 = this.currentProject.getTasks()[1];
        list2 = this.currentProject.getTasks()[2];
        list3 = this.currentProject.getTasks()[3];
        list4 = this.currentProject.getTasks()[4];
        list5 = this.currentProject.getTasks()[5];
        list6 = this.currentProject.getTasks()[6];
        list7 = this.currentProject.getTasks()[7];

        LinkedList listNew = new LinkedList<String>();
        for (Task currentTask : list0) {
            listNew.add(currentTask.getName());
        }
        LinkedList listAnalysisProcessing = new LinkedList<String>();
        for (Task currentTask : list1) {
            listAnalysisProcessing.add(currentTask.getName());
        }
        LinkedList listAnalysisDone = new LinkedList<String>();
        for (Task currentTask : list2) {
            listAnalysisDone.add(currentTask.getName());
        }
        LinkedList listImplementationProcessing = new LinkedList<String>();
        for (Task currentTask : list3) {
            listImplementationProcessing.add(currentTask.getName());
        }
        LinkedList listImplementationDone = new LinkedList<String>();
        for (Task currentTask : list4) {
            listImplementationDone.add(currentTask.getName());
        }
        LinkedList listTestProcessing = new LinkedList<String>();
        for (Task currentTask : list5) {
            listTestProcessing.add(currentTask.getName());
        }
        LinkedList listTestDone = new LinkedList<String>();
        for (Task currentTask : list6) {
            listTestDone.add(currentTask.getName());
        }
        LinkedList listFinished = new LinkedList<String>();
        for (Task currentTask : list7) {
            listFinished.add(currentTask.getName());
        }

        this.listNew.setItems(FXCollections.observableList(listNew));
        this.listAnalysisProcessing.setItems(FXCollections.observableList(listAnalysisProcessing));
        this.listAnalysisDone.setItems(FXCollections.observableList(listAnalysisDone));
        this.listImplementationProcessing.setItems(FXCollections.observableList(listImplementationProcessing));
        this.listImplementationDone.setItems(FXCollections.observableList(listImplementationDone));
        this.listTestProcessing.setItems(FXCollections.observableList(listTestProcessing));
        this.listTestDone.setItems(FXCollections.observableList(listTestDone));
        this.listFinished.setItems(FXCollections.observableList(listFinished));
    }

    @FXML
    void createComment(ActionEvent event) {
        createCommentViewController = new CreateCommentViewController();
        Scene scene = new Scene(createCommentViewController, 650, 300);
        createCommentStage.setScene(scene);
        createCommentStage.setTitle("Kommentar erstellen");
        createCommentStage.show();
    }

    @FXML
    void acceptTask(ActionEvent event) {

        this.mainController.getProjectController().assignTask(test1,lastSelected);
        test1 = test2;
        lastSelected = null;
        refreshTask();

    }

    @FXML
    void completeTask(ActionEvent event) {

        this.mainController.getProjectController().completeTask(lastSelected);
        lastSelected = null;
        refreshTask();
    }

    @FXML
    void createTask(ActionEvent event) {
        createTaskViewController = new CreateTaskViewController(this.mainController, this, this.currentProject);
        Scene scene = new Scene(createTaskViewController, 481, 330);
        createTaskStage.setScene(scene);
        createTaskStage.setTitle("Aufgabe erstellen");
        createTaskStage.show();
    }

    @FXML
    void manageProjects(ActionEvent event) {
        projectViewController = new ProjectViewController(this.mainController, team);
        Scene scene = new Scene(projectViewController, 640, 400);
        manageProjectStage.setScene(scene);
        manageProjectStage.setTitle("Projekt verwalten");
        manageProjectStage.show();
    }

    @FXML
    void manageTask(ActionEvent event) {
        taskViewController = new TaskViewController(this.mainController);
        Scene scene = new Scene(taskViewController, 650, 300);
        manageTaskStage.setScene(scene);
        manageTaskStage.setTitle("Aufgabe verwalten");
        manageTaskStage.show();
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
    void nextProject(ActionEvent event) {

    }

    @FXML
    void previousProject(ActionEvent event) {

    }

    @FXML
    void showDetails(ActionEvent event) {

    }

    @FXML
    void unassignTask(ActionEvent event) {
        this.mainController.getProjectController().unassignTask(lastSelected);
        lastSelected = null;
        refreshTask();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert buttonManageProjects != null : "fx:id=\"buttonManageProjects\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert buttonManageTeam != null : "fx:id=\"buttonManageTeam\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert buttonCreateTask != null : "fx:id=\"buttonCreateTask\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert buttonManageTask != null : "fx:id=\"buttonManageTask\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert buttonCompleteTask != null : "fx:id=\"buttonCompleteTask\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert buttonAcceptTask != null : "fx:id=\"buttonAcceptTask\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert buttonUnassignTask != null : "fx:id=\"buttonUnassignTask\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert buttonShowDetails != null : "fx:id=\"buttonShowDetails\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert buttonPreviousProject != null : "fx:id=\"buttonPreviousProject\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert labelProjectname != null : "fx:id=\"labelProjectname\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert buttonNextProject != null : "fx:id=\"buttonNextProject\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert vBoxUnassignedEmployees != null : "fx:id=\"vBoxUnassignedEmployees\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert gridPaneTaskView != null : "fx:id=\"gridPaneTaskView\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert listAnalysisProcessing != null : "fx:id=\"listAnalysisProcessing\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert listAnalysisDone != null : "fx:id=\"listAnalysisDone\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert listImplementationProcessing != null : "fx:id=\"listImplementationProcessing\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert listImplementationDone != null : "fx:id=\"listImplementationDone\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert listTestProcessing != null : "fx:id=\"listTestProcessing\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert listTestDone != null : "fx:id=\"listTestDone\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert listNew != null : "fx:id=\"listNew\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";
        assert listFinished != null : "fx:id=\"ListFinished\" was not injected: check your FXML file 'KanbanBoardView.fxml'.";

        this.labelProjectname.setText(this.currentProject.getName());
        initializeListeners();;

    }
    private void initializeListeners(){
        this.listNew.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listNew.getSelectionModel().getSelectedIndex();
                lastSelected = list0.get(index);
                System.out.println(lastSelected.getName());
            }
        });
        this.listAnalysisProcessing.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listAnalysisProcessing.getSelectionModel().getSelectedIndex();
                lastSelected = list1.get(index);
                System.out.println(lastSelected.getName());
            }
        });
        this.listAnalysisDone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listAnalysisDone.getSelectionModel().getSelectedIndex();
                lastSelected = list2.get(index);
                System.out.println(lastSelected.getName());
            }
        });
        this.listImplementationProcessing.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listImplementationProcessing.getSelectionModel().getSelectedIndex();
                lastSelected = list3.get(index);
                System.out.println(lastSelected.getName());
            }
        });
        this.listImplementationDone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listImplementationDone.getSelectionModel().getSelectedIndex();
                lastSelected = list4.get(index);
                System.out.println(lastSelected.getName());
            }
        });
        this.listTestProcessing.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listTestProcessing.getSelectionModel().getSelectedIndex();
                lastSelected = list5.get(index);
                System.out.println(lastSelected.getName());
            }
        });
        this.listTestDone.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listTestDone.getSelectionModel().getSelectedIndex();
                lastSelected = list6.get(index);
                System.out.println(lastSelected.getName());
            }
        });
        this.listFinished.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int index = listFinished.getSelectionModel().getSelectedIndex();
                lastSelected = list7.get(index);
                System.out.println(lastSelected.getName());
            }
        });

    }
}
