package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Tobias
 *
 */
public class Team implements Serializable {

	 private String name, projectLeader;
	 private LinkedList<Developer> developers = new LinkedList<>();
	 private LinkedList<Project> archivedProjects  = new LinkedList<>();
	 private Project activeProject;
	 private String projectName;

	 
	 /**
	  * 
	  * @param pName , neuer Projektname
	  * @param pProjectLeader , neuer Projectleader
	  */
	public Team(String pName, String pProjectLeader) {
		setName(pName);
		setProjectLeader(pProjectLeader);
		projectName = "Noch kein Projekt";
	}
	
	/**
	 * 
	 * @param pName , neuer Projektname
	 * @param pProjectLeader , neuer Projektleader als [{@link Developer#getName()}
	 */
	public Team(String pName, Developer pProjectLeader) {
		setName(pName);
		setProjectLeader(pProjectLeader.getName());
		projectName = "Noch kein Projekt";
	}


	/**
	 * Fügt dem Team einen Developer zu
	 * @param pDev Der hizuzufügende Developer
	 * @return ob der Developer noch nicht vorhanden war
	 */
	 public boolean addDeveloper(Developer pDev) {
		 if(pDev==null) {
			 throw new IllegalArgumentException("pDev ist null");
		 }
		 if(developers.contains(pDev)){
			 return false;
		 }
		 developers.add(pDev);
		 return true;

	 }

	 /**
	  * Entfernt den übergebenen Developer
	  * @param pDev Der übergebene Developer
	  * @return ob pDev überhaupt im Team war
	  */
	 public boolean removeDeveloper(Developer pDev) {
		 if(pDev==null) {
			 throw new IllegalArgumentException("pDev ist null");
		 }
		 if(!developers.contains(pDev)){
			 return false;
		 }
		 developers.remove(pDev);
		 return true;
	 }
	 
	 /**
	  * Entfernt den Developer mit der pDevid
	  * @param pDevid Die übergebene DevID
	  * @return Wenn das entfernen erfolgreich war, wird true, ansonsten false zurückgegeben.
	  * @see #removeDeveloper(Developer)
	  */
	 public boolean removeDeveloper(int pDevid) {
		 for(Developer dev : developers) {
			 if(dev.getDevID()==pDevid){
				 developers.remove(dev);
				 return true;
			 }
		 }
		 return false;

	 }


	 /**
	  * Gibt dem Team ein aktives Projekt sollenges es noch keins hat
	  * @param pProject Das übergebene Projekt
	  * @return false, wenn bereits aktives Projekt vorliegt, true wenn das Projekt erfolgreich
	  * hinzugefügt wurde.
	  */
	 public boolean addProject(Project pProject) {
		 if(pProject==null) {
			 throw new IllegalArgumentException("pProject ist null");
		 }
		 if(activeProject!=null) {
			 return false;
		 }
		 projectName = pProject.getName();
		 activeProject=pProject;
		 return true;

	 }

	 /**
	  * Entfern das Aktive Projekt, sofern vorhanden, vom Team
	  * Das Projekt wird entfernt und nicht archiviert
	  * @return das entfernte Projekt
	  */
	 public Project removeProject() {
		 if(activeProject==null) {
			 throw new IllegalStateException("Team "+name+" hat kein aktives Projekt");
		 }
		 Project temp = activeProject;
		 activeProject=null;
		 projectName = "Noch kein Projekt";
		 return temp;
	 }

	 /**
	  * archiviert das aktive Projekt , sofern vorhanden. 
	  */
	 public void archiveActiveProject() {
		 if(activeProject==null){
			 throw new IllegalStateException("Team "+name+" hat kein ein aktives Projekt");
		 }
		 archivedProjects.add(activeProject);
		 activeProject=null;
	 }

	 /**
	  * 
	  * @return Die Liste der archivierten Projekte
	  */
	 public List<Project> getArchivedProjects(){
		 return archivedProjects;

	 }
	 
	 /**
	  * @return {@link #name}
	  */
	 public String getName() {
		return name;
	 }
	 
	 /**
	  * Setzt {@link #name}
	  * @param name , neuer Name
	  */
	 public void setName(String name) {
		this.name = name;
	 }
	 
	 /**
	  * 
	  * @return {@link #projectLeader}
	  */
	public String getProjectLeader() {
		return projectLeader;
	}
	
	/**
	 * Setzt {@link #projectLeader}
	 * @param projectLeader , neuer Projektleader
	 */
	public void setProjectLeader(String projectLeader) {

		this.projectLeader = projectLeader;
	}

	/**
	 * 
	 * @return {@link #activeProject}
	 */
	public Project getActiveProject () {
	 	return activeProject;
	}

	/**
	 *
	 * @return {@link #developers}
	 */
	public LinkedList<Developer> getDevelopers(){
		return developers;
	}
}