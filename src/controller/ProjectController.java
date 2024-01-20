package controller;

import model.Developer;
import model.Project;
import model.Task;
import model.Team;
import view.ProjectAUI;
import view.TaskAUI;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * ProjectController für Methoden zur Verwaltung der Projekte und mehr
 *
 * @author Nenad
 */

public class ProjectController {
	/**
	 * Die Referenz auf den MainController, der zum Austausch zwischen den
	 * Controllern dient
	 */
	private MainController mainController;

	private ProjectAUI projectAUI;

	private TaskAUI taskAUI;

	private LinkedList<Team> teamList;


	/**
	 * Konstruktor
	 *
	 * @param 	pMC
	 *            pMC ist die Referenz auf den MainController
	 *            der zustaendig fuer alle Controller ist
	 * @throws NullPointerException
	 *             Die NullPointerException wird geworfen, wenn der Parameter
	 *             null ist
	 */

	public ProjectController(MainController pMC ) throws NullPointerException {
		if(pMC == null)
			throw new NullPointerException();

		mainController = pMC;
		teamList = mainController.getTeams();

	}

	/**
	 * Erstellt ein neues Projekt mit eindeutigem Namen,
	 * einer Beschreibung und einer Deadline
	 * diese Projekt wird in
	 * 			 NullPointerException
	 *             Die NullPointerException wird geworfen, wenn der Parameter
	 *             null ist
	 * @param team Das Team welches dem neuen Projekt zugewiesen wird.
	 * @param pName
	 * 			Name des Projekts
	 * @param pDescription
	 * 			Beschreibung des Projekts
	 * @param pDeadline
	 * 			Deadline
	 */
	public void createProject(Team team, String pName, String pDescription, LocalDate pDeadline) {
		if(pName == null || pDescription == null || pDeadline == null || team == null)
			throw new NullPointerException();
		Project project = new Project(pName, pDescription, pDeadline);
		team.addProject(project);
		if (this.projectAUI != null && taskAUI != null) {
			this.projectAUI.refreshProjects();
			this.taskAUI.refreshTask();
		}
	}

	/**
	 * Bearbeitet die Rahmendaten eines uebergebenen Projekts
	 * @param pProject
	 * 			dieses Projekt ist das zu bearbeitende
	 * @param pName
	 * 			dieser String ersetzt den Namen
	 * @param pDescription
	 * 			dieser Text ersetzt die Beschreibung
	 * @param pDeadline
	 * 			dieser setzt die Deadline
	 * throws NullPointerException
	 *             Die NullPointerException wird geworfen, wenn der Parameter
	 *             null ist
	 */

	public void editProject(Project pProject, String pName, String pDescription, LocalDate pDeadline) {
		if(pName == null || pDescription == null || pDeadline == null || pProject == null)
			throw new NullPointerException();
		pProject.setDeadline(pDeadline);
		pProject.setDescription(pDescription);
		pProject.setName(pName);

	}

	/**
	 * Hier wird ein Projekt geloescht,
	 * indem es von seinem derzeitgen Team geloest wird
	 *
	 * @param team
	 * uebergeben nur das Team welches ihr Projekt verliert
	 */
	public void deleteProject(Team team) {
		if(team == null)
			throw new NullPointerException();
		if(team.getActiveProject() == null)
			throw new NullPointerException(" no active Project");
		team.removeProject();
		if (this.projectAUI != null && taskAUI != null) {
			this.projectAUI.refreshProjects();
			this.taskAUI.refreshTask();
		}
	}

	/**
	 * Hier soll das uebergeben Team ihr Projekt archivieren
	 * @param team
	 * 			das Team hat ein activeProjekt
	 * 			welches archviert werden soll
	 * throws NullPointerException
	 *             Die NullPointerException wird geworfen, wenn der Parameter
	 *             null ist
	 */
	public void archiveProject(Team team) {
		if (team.getActiveProject() == null || team == null)
			throw new NullPointerException(" no active Project");
		team.archiveActiveProject();
		if (this.projectAUI != null && taskAUI != null) {
			this.projectAUI.refreshProjects();
			this.taskAUI.refreshTask();
		}
	}

	/**
	 * Soll die uebergebene Aufgabe beenden (Von Bearbeitung nach Erledigt)
	 * @param pTask
	 * die Task wird ge"shifted" und Anpassungen an Dev und co werden vorgenommen
	 * throws NullPointerException
	 *             Die NullPointerException wird geworfen, wenn der Parameter
	 *             null ist
	 */

	public void completeTask(Task pTask) {
		if(pTask == null)
			throw new NullPointerException();
		Developer dev = pTask.getWorkingDeveloper();
		if(dev == null)
			throw new NullPointerException();
		dev.increaseCompletedTask();
		pTask.endWorking();
		if(pTask.getProject() == null)
			throw new NullPointerException();
		pTask.getProject().shiftTaskFwd(pTask);
		if (this.projectAUI != null && taskAUI != null) {
			this.projectAUI.refreshProjects();
			this.taskAUI.refreshTask();
		}

	}

	/**
	 * Ein Dev soll eine Aufgabe uebernehmen
	 * @param pDev
	 * 			der Developer welcher arbeiten will
	 * @param pTask
	 * 			die Aufgabe welche zu bearbeiten gilt
	 * throws NullPointerException
	 *             Die NullPointerException wird geworfen, wenn der Parameter
	 *             null ist
	 */
	public void assignTask(Developer pDev, Task pTask) {
		if(pDev == null)
			throw new NullPointerException();
		if(pTask == null )
			throw new NullPointerException();
		pTask.startWorking(pDev);
		pTask.getProject().shiftTaskFwd(pTask);
		if (this.projectAUI != null && taskAUI != null) {
			this.projectAUI.refreshProjects();
			this.taskAUI.refreshTask();
		}
	}

	/**
	 * Die Aufgabe soll abgelegt werden also will der Dev
	 * somit die Bearbeitung der Aufgabe abbrechen
	 * @param pTask
	 * 			Die Aufgabe hat einen isworkedAt und activeDev
	 * 		welche angepasst werden sollen.
	 * throws NullPointerException
	 *             Die NullPointerException wird geworfen, wenn der Parameter
	 *             null ist
	 */
	public void unassignTask(Task pTask) {
		if(pTask == null)
			throw new NullPointerException();
		pTask.endWorking();
		pTask.getProject().shiftTaskBck(pTask);
		if (this.projectAUI != null && taskAUI != null) {
			this.projectAUI.refreshProjects();
			this.taskAUI.refreshTask();
		}
	}

}
