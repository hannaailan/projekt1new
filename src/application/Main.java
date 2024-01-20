package application;
	
import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.KanbanBoardViewController;
import view.ProjectsViewController;
import view.SampleViewController;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ProjectsViewController projectsViewController = new ProjectsViewController();
			//SampleViewController sampleViewController = new SampleViewController();
			int height = (int) projectsViewController.getHeight();
			int width = (int) projectsViewController.getWidth();
			Scene scene = new Scene(projectsViewController, width, height);
			//Scene scene = new Scene(sampleViewController,400,200);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Projekt Uebersicht");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
