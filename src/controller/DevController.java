package controller;

import model.Company;
import model.Developer;
import model.Team;
import view.ProjectAUI;
import view.TaskAUI;


/**
 * @author Mohamad Fistok
 *
 */
public class DevController {

	private MainController mainController;

	private ProjectAUI projectAUI;

	private TaskAUI taskAUI;
	/**
	 * Bei dem erzeugen der Klasse DevController wird hier der
	 * MainController pMC dem Attribut mainController zugewiesen.
	 * @param pMC Der übergebene MainController
	 * @throws IllegalArgumentException Wird geworfen, wenn der Parameter ein null-Wert ist.
	 */
	public DevController(MainController pMC) {
		if(pMC==null)
			throw new IllegalArgumentException("Parameter pMC ist 'null'");
		this.mainController=pMC;

	}

	/**
	 * Die Methode createDev erstellt einen neuen Developer mit dem Name und ID . Dieser Developer
	 * wird dann dem Team hinzugefügt.
	 * @param pName Der Name von Developer dem pTeam_Team hinzugefügt werden soll.
	 * @param pTeam das Team wozu der Developper hinzugefügt wird.
	 * @throws IllegalArgumentException Wird geworfen, falls mindestens ein Parameter einen null-Wert enthält.
	 * @throws NullPointerException Wird geworfen falls die ProjectAUI noch nicht gesetzt wurde.
	 * @throws NullPointerException Wird geworfen falls die TaskAUI noch nicht gesetzt wurde.
	 */
	public void createDev(Team pTeam, String pName) {
		if (pTeam== null || pName.equals("")){
			throw new IllegalArgumentException("Mind. ein Parameter ist null");
		} else {
			Developer pDev=new Developer(pName);
			pTeam.addDeveloper(pDev);
			if (this.projectAUI != null && taskAUI != null) {
				this.projectAUI.refreshProjects();
				this.taskAUI.refreshTask();
			} else {
				//throw new NullPointerException("Die CommentAUI wurde noch nicht gesetzt");//TODO anpassen sobald es die Klasse gibt
			}
		}
	}

	/**
	 * Die Methode deleteDevt löscht den Developer pDev aus der Developer-Liste  von einem Team.
	 * Der übergebene Parameter pDev ist zu löschen
	 * @param pDev  Der Name von Developer aus dem pTeam_ Team gelöscht werden soll.
	 * @param pTeam Das Team woraus der Developper gelöscht wird.
	 * @throws IllegalArgumentException Wird geworfen, falls mindestens ein Parameter einen null-Wert enthält.
	 * @throws NullPointerException Wird geworfen falls die ProjectAUI noch nicht gesetzt wurde.
	 * @throws NullPointerException Wird geworfen falls die TaskAUI noch nicht gesetzt wurde.
	 */
	public void deleteDev(Team pTeam,Developer pDev) {
		if (pDev== null){
			throw new IllegalArgumentException("Ein Parameter ist null");
		} else {
			pTeam.removeDeveloper(pDev);
			if (this.projectAUI != null && taskAUI != null) {
				this.projectAUI.refreshProjects();
				this.taskAUI.refreshTask();
			} else {
				//throw new NullPointerException("Die CommentAUI wurde noch nicht gesetzt");//TODO anpassen sobald es die Klasse gibt
			}
		}
	}
	/**
	 * Die Methode editDev ersetzt die alte Name und ID durch die übergebenen Parameter pName und pDevID.  
	 * Der übergebenene Parameter pName und pDevID sind zu ersetzen
	 * @param pDev Der Developper welcher bearbeitet wird.
	 * @param pName  Der Name von Developer wo den Inhalt geändert werden soll.
	 * @throws IllegalArgumentException Wird geworfen, falls mindestens ein Parameter einen null-Wert enthält.
	 * @throws NullPointerException Wird geworfen falls die ProjectAUI noch nicht gesetzt wurde.
	 * @throws NullPointerException Wird geworfen falls die TaskAUI noch nicht gesetzt wurde.
	 */
	public void editDev(Developer pDev, String pName) {
		if(pDev==null || pName.equals("")) {
			throw new IllegalArgumentException("Mind. ein Parametere ist null");
		}else {
			pDev.setName(pName);
			if (this.projectAUI != null && taskAUI != null) {
				this.projectAUI.refreshProjects();
				this.taskAUI.refreshTask();
			} else {
				//throw new NullPointerException("Die CommentAUI wurde noch nicht gesetzt");//TODO anpassen sobald es die Klasse gibt
			}
		}	
	}
	/**
	 * Die Methode dailyUpdate aktualisiert für am aktuellen Tag für alle Developer 
	 * @param pComp Der pComp_Company ist gegeben,um eine Liste zuzugreifen
	 * @throws IllegalArgumentException Wird geworfen, falls mindestens ein Parameter einen null-Wert enthält.
	 */
	public void dailyUpdate(Company pComp) {
		if(pComp==null)
		{
			throw new IllegalArgumentException("Der Parametere ist null");
		}else {
               for(Developer upDev : pComp.getDevs())
                  {
                	  upDev.updateCountTasks();
                  }
	          }
    }

	/**
	 *
	 * @return gibt die projectAUI zurück
	 */
	public ProjectAUI getProjectAUI() {
		return projectAUI;
	}

	/**
	 *
	 * @return gibt den MainController zurück
	 */
	public MainController getMainController() {
		return mainController;
	}

	/**
	 *
	 * @return gibt die taskAUI zurück
	 */
	public TaskAUI getTaskAUI() {
		return taskAUI;
	}
}