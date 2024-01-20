package controller;

import model.Company;

import java.util.LinkedList;


import model.Team;

import view.TeamAUI;

/**
 * Die TeamController-Klasse ist für die verwaltung von Teams zuständig.
 */
public class TeamController {
	/**
	 * Controller für Funktionen zur Verwaltung des Teams.
	 *
	 * @author Hanna
	 */

	private MainController mainController;

	private TeamAUI teamAUI;
	
	

	/**
	 * @param pMC ist die Referenze auf den MainController
	 * @throws IllegalArgumentException wird zuruerckgegeben ,wenn pMC null ist
	 */
	public TeamController(MainController pMC) {
		if(pMC==null) {
			throw new IllegalArgumentException();
		}
		this.mainController=pMC;

	}
	
	 /**
	  *
	  * @param company Die Company wozu das neue Team hinzugefügt werden soll.
	  * @param pName: dieser String reference auf den Namen des Teams
	  * @param pProjectLeader: dieser String reference auf den Boss des Teams
       @throws IllegalArgumentException Wird geworfen, wenn es einen Null Wert gibt.	  
       *
       * Die Methode erzeugt einen neuen Team
	  */
	public void createTeam( Company company, String pName, String pProjectLeader) {
		if(pName == null || pProjectLeader == null) {
			throw new IllegalArgumentException();
		}
		else {
			Team team1 = new Team(pName, pProjectLeader);
			company.addTeam(team1);

		}
	}
	/**
	 * @param company Die Company woraus das Team gelöscht wird.
	 * @param pTeam: dieser String reference auf den Namen des Teams
	 * @throws IllegalArgumentException Wird geworfen, wenn pTeam Null-Wert ist.	  
     *
	 * die Methode löscht einen Team
	 */
	public void deleteTeam( Company company, Team pTeam) {
		if(pTeam==null) {
			throw new IllegalArgumentException();
		}
		else {
		 company.removeTeam(pTeam);
		}
	}

	/**
	 * @param pTeam Das Team welches bearbeitet wird.
	 * @param pName dieser String reference auf den Namen
	 * @param pProjectLeader dieser String reference auf den Boss des Teams
	 * 
	 * @throws IllegalArgumentException
	 *      eine IllegalArgumentException wird zurueckgegeben ,wenn ein String der Wert Null ist
	 * 
	 * die Methode kann in dem aktuellen Team bearbeiten
	 */
	public void editTeam(Team pTeam, String pName, String pProjectLeader) {
		if(pName == null || pProjectLeader == null) {
			throw new IllegalArgumentException();
		}
		pTeam.setName(pName);
		pTeam.setProjectLeader(pProjectLeader);
	}

}
