package model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * @author Tobias
 *
 */
public class Task implements Serializable {

	private String name,description;
	private LocalDate deadline;
	private  final Project project;
	private LinkedList<Duration> processingTime = new LinkedList<>();
	private LinkedList<Comment> comments = new LinkedList<>();
	private Developer workingDeveloper;
	private boolean isWorkedAt=false;
	private LocalDateTime startTime;


	/**
	 * parameterleerer Konstruktor
	 *
	 * @param pProject Das Projekt wozu diese Aufgabe gehört.
	 */
	public Task(Project pProject) {
		this.project = pProject;
		setName("Standartname");
		setDescription("Standartbeschreibung");
		setDeadline(LocalDate.now().plusDays(10));
		
	}
	
	/**
	 * Konstruktor mit Parameter
	 * @param pProject Das Projekt wozu diese Aufgabe gehört.
	 * @param pName , neuer Name
	 * @param pDescribtion , neue Beschreibung
	 * @param pDeadline , neue Deadline
	 */
	public Task(Project pProject, String pName, String pDescribtion, LocalDate pDeadline) {
		this.project = pProject;
		setName(pName);
		setDescription(pDescribtion);
		setDeadline(pDeadline);
	}
	/**
	 * fügt einen Kommentar hinzu
	 * @param pNote Der übergebene Kommentar
	 * @return ob der kommentar noch nicht vorhanden war
	 */
	public boolean addComment(Comment pNote) {
		if(pNote==null) {
			throw new IllegalArgumentException("pNote ist null");
		}
		if(comments.contains(pNote)) {
			return false;
		}
		comments.add(pNote);
		return true;
	}

	/**
	 * Fügt einen Kommentar mit dem übergebenen Inhalt hinzu
	 * @param pContent ,der Kommentarinhalt
	 */
	public void addComment(String pContent) {
		if(pContent==null) {
			throw new IllegalArgumentException("pContent ist null");
		}
		
		comments.add(new Comment(pContent));
		
	}
	
	/**
	 * löscht den übergebenen Kommentar von der Aufgabe
	 * @param pNote Der übergebene Kommentar
	 * @return ob pNote überhaupt vorhanden war
	 */
	public boolean removeComment(Comment pNote) {
		if(pNote==null) {
			throw new IllegalArgumentException("pNote ist null");
		}
		if(!comments.contains(pNote)) {
			return false;
		}
		 return comments.remove(pNote);
		 
		 
		 
	}

	/**
	 * Gibt die Liste der zu einer Aufgabe gehörigen Kommentare zurück
	 * @return comments
	 */
	public LinkedList<Comment> getComments(){
		return comments;
	}

	/**
	 * Gibt den Aufgabennamen zurück
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen einer Aufgabe
	 * @param name zu setzender Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt die Deadline einer Aufgabe zurück
	 * @return deadline
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * Setzt die Deadline einer Aufgabe
	 * @param deadline zu setzende Deadline
	 */
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	/**
	 * Gibt die Beschreibung einer Aufgabe an
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setzt die Beschreibung einer Aufgabe
	 * @param description Zu setzende Beschreibung
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return den Aktuellen Developer, der die Aufgabe bearbeitet
	 * !Achtung! wenn die Aufgabe grade nicht bearbeitet wird, wird "null" zurückgegeben
	 */
	public Developer getWorkingDeveloper() {
		
		return workingDeveloper;
	}

	/**
	 * Falls an er Aufgabe noch nicht gearbeitet wird, wird {@link #startTime} auf die aktuelle Zeit und {@link #isWorkedAt} auf true gesetzt
	 * @throws IllegalStateException falls {@link #isWorkedAt} = true 
	 * @param pDev , der Developer der an der Aufgabe arbeitet
	 */
	public void startWorking(Developer pDev) {
		if(isWorkedAt)
			throw new IllegalArgumentException("Aufgabe bereits in Bearbeitung");

		if(pDev==null)
			throw new IllegalArgumentException("pDev ist null");
		if(pDev.isWorking())
			throw new IllegalArgumentException("pDev arbeitet schon");
		isWorkedAt=true;
		workingDeveloper=pDev;
		workingDeveloper.startWorking();
		startTime=LocalDateTime.now();


	}
	
	
	/**
	 * Falls an der Aufgabe gearbeitet wird, wird die Arbeitszeit {@link #processingTime} hinzugefügt und  {@link #isWorkedAt} auf false gesetzt
	 * @throws IllegalStateException falls {@link #isWorkedAt} = false
	 */
	public void endWorking() {
		if(!isWorkedAt)
			throw new IllegalStateException("Aufgabe wird nicht bearbeitet");
		if(workingDeveloper==null)
			throw new IllegalStateException("der arbeitende Entwickler wurde gelöscht!");
		if(!workingDeveloper.isWorking())
			throw new IllegalStateException("der arbeitende Entwickler arbeitet nicht!");
		
		isWorkedAt=false;
		workingDeveloper.endWorking();
		workingDeveloper=null;
		processingTime.add(Duration.between(startTime, LocalDateTime.now()));
	}

	/**
	 * Gibt an ob die Aufgabe bereits in Bearbeitung ist
	 * @return isWorkedAt
	 */
	public boolean isWorkedAt() {
		return isWorkedAt;
	}

	/**
	 * summiert  die Zeit auf die Developer an der Aufgabe gearbeitet haben
	 * @return die Summe als {@link java.time.Duration}
	 */
	public Duration sumProcessingTime() {
		Duration sum=Duration.ZERO;
		for(Duration item : processingTime) {
			sum= sum.plus(item);
		}
		return sum;
	}

	/**
	 * Gibt das Projekt zurück, zu welchem die Aufgabe gehört
	 * @return project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Erlaubt es für den StatisticControllerTest eine Liste von bearbeitungszeiten manuell zu setzen
	 * @param processingTime Übergebene Liste von Bearbeitungszeiten
	 */
	public void setProcessingTimeTest(LinkedList<Duration> processingTime) {
		this.processingTime = processingTime;
	}
}
