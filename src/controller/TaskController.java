package controller;

import model.Comment;
import model.Project;
import model.Task;
import view.ProjectAUI;
import view.TaskAUI;

import java.time.LocalDate;
import java.util.Date;

/**
 * TaskController-Klasse verwaltet die Tasks
 */
public class TaskController {

	private MainController mainController;

	private ProjectAUI projectAUI;
		
	private TaskAUI taskAUI;
	/**
	 * Bei dem erzeugen der Klasse TaskController wird hier der
	 * MainController pMC dem Attribut mainController zugewiesen.
	 *
	 * @param pMC Der als Parameter übergebene MainController
	 * @throws IllegalArgumentException Wird geworfen, wenn der Parameter null ist.
	 */
	public TaskController(MainController pMC) {
		
		if(pMC==null) {
			throw new IllegalArgumentException();
		}
		this.mainController=pMC;
	}

	/**
	  * Erstellt eine neue Task
	  * @param pProject Das aktuelle Projekt.
	  * @param pName: dieser String reference auf den Namen der Task
	  * @param pDescription: dieser String reference auf die Beschreibung der Task
	  * @param pDeadLine : referenziert auf das deadline von der Task
	  */
	public void createTask(String pName, String pDescription, LocalDate pDeadLine, Project pProject) {
				if(pName == null || pDescription == null|| pDeadLine ==  null || pProject == null) {
					throw new IllegalArgumentException();
				}
				else {
					Task task1 = new Task(pProject, pName, pDescription, pDeadLine);
					pProject.addTask(task1);
				}
				if (this.projectAUI != null && taskAUI != null) {
					this.projectAUI.refreshProjects();
					this.taskAUI.refreshTask();
				}
	}

	/**
	  * bearbeitet die Task
	  * @param pTask die aktuelle Task.
	  * @param pName: dieser String reference auf den Namen der Task
	  * @param pDescription: dieser String reference auf die Beschreibung der Task
	  * @param pDeadline : referenziert auf das deadline von der Task
	  */
	public void editTask(Task pTask, String pName, String pDescription, LocalDate pDeadline) {
		if(pName == null || pDescription == null || pDeadline == null ) {
			throw new IllegalArgumentException();
		}
		pTask.setName(pName);
		pTask.setDescription(pDescription);
		pTask.setDeadline(pDeadline);
	}

	/**
	  * Löscht die Task
	  * @param pProject Das aktuelle Projekt.
	  * @param pTask die zu löschende Task.
	  */
	public void deleteTask(Project pProject, Task pTask) {
		if(pProject == null) {
			throw new IllegalArgumentException();
		}
		pProject.removeTask(pTask);
		if (this.projectAUI != null && taskAUI != null) {
			this.projectAUI.refreshProjects();
			this.taskAUI.refreshTask();
		}
	}

	
	/**
	  * Kommntiert die Task
	  * @param pTask die aktuelle Task.
	  * @param pComment der hinzuzufügende Kommentar .
	  */
	public void commentTask(Task pTask, Comment pComment) {
		
		if( pComment == null) {
			throw new IllegalArgumentException();
		}
		pTask.addComment(pComment);
		if (taskAUI != null) {
			this.taskAUI.refreshTask();
		}
	}

	

}
