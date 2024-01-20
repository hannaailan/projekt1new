package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Die Project Klasse modelliert ein Projekt
 */
public class Project implements Serializable {

	private String name,describtion;
	private LocalDate deadline;
	
	@SuppressWarnings("unchecked")
	private LinkedList<Task>[] taskLists=new LinkedList[8];
	
	@SuppressWarnings("unused")
	private LinkedList<Developer> devs = new LinkedList<>();
	public static final int NEU=0;
	public static final int[] INWORK= {1,3,5};
	public static final int[] FINISHED= {2,4,6};
	public static final int CLOSED=7;

	/**
	 * Konstruktor von Project
	 * @param pName Der Name von dem neuen Projekt
	 * @param pDescription Die Beschreibung von dem neuen Projekt
	 * @param pDeadline Die Deadline von dem neuen Projekt
	 */
	public Project(String pName, String pDescription,LocalDate pDeadline) {
		initTaskLists();
		setName(pName);
		setDescription(pDescription);
		setDeadline(pDeadline);

	}
	
	private void initTaskLists() {
		for(int listIndex = 0; listIndex<8;listIndex++)  {
			taskLists[listIndex] = new LinkedList<>();
		}
	}
	
	/**
	 * löscht die übergebene pTask aus dem Project
	 * @throws IllegalArgumentException falls pTask "null" ist
	 * @param pTask , die zu entfernende Aufgabe
	 * @return ob pTask überhaupt vorhanden war
	 */
	public boolean removeTask(Task pTask) {
		if(pTask==null) 
			throw new IllegalArgumentException("pTask ist null");
		for(LinkedList<Task> item : taskLists) {
			if(item.remove(pTask))
				return true;
		}
		return false;
	}

	/**
	 * fügt pTask dem Project hinzu
	 * @throws IllegalArgumentException falls pTask "null" ist
	 * @param pTask , die neue Aufgabe
	 * @return ob pTask noch nicht vorhanden war
	 * wenn pTask irgentwo im Project vorhanden ist, wird es nicht erneut hinzugefügt 
	 */
	public boolean addTask(Task pTask) {
		if(pTask==null) 
			throw new IllegalArgumentException("pTask ist null");
		if(findTask(pTask)!=-1)
			return false;
		taskLists[NEU].add(pTask);
		return true;
	}

	/**
	 *
	 * @return Gibt eine Liste mit allen Aufgaben zurück
	 */
	public LinkedList<Task>[] getTasks(){return taskLists;}

	/**
	 *
	 * @return Gibt eine Liste mit allen Developern zurück
	 */
	public LinkedList<Developer> getDevs() {return devs;}

	/**
	 *
	 * @return Gibt die Beschreibung von dem Projekt zurück
	 */
	public String getDescribtion() {
		return describtion;
	}

	/**
	 * Setzt die Beschreibung auf den Parameter description
	 * @param description Die neue Beschreibung
	 */
	public void setDescription(String description) {
		if(description==null)
			throw new IllegalArgumentException("Parameter ist null");
		this.describtion = description;
	}

	/**
	 *
	 * @return Gibt den Namen von dem Projekt zurück
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen von dem Projekt auf den Parameter name
	 * @param name Der neue Name für das Projekt
	 */
	public void setName(String name) {
		if(name==null)
			throw new IllegalArgumentException("Parameter ist null");
		this.name = name;
	}
	
	/**
	 * Gibt den Index der Liste in {@link #taskLists} zurück in der sich pTask befindet
	 * Wird pTask nicht gefunden wird -1 zurückgegeben
	 * @param pTask , die zu findende Aufgabe
	 * @return die Liste in der sich die Aufgabe befindet, bzw. -1 wenn die Aufgabe nicht gefunden wurde
	 */
	public int findTask(Task pTask) {
		for(int listIndex = 0; listIndex<8;listIndex++) {
			if(taskLists[listIndex].contains(pTask))
				return listIndex;
		}
		return -1;
	}

	
	
	/**
	 * bewegt die  Übergebene Task eine Station weiter
	 * @throws IllegalArgumentException falls die Aufgabe nicht im Projekt vorhanden ist oder die Aufgabe bereit abgeschlossen ist
	 * @param pTask , die zu bewegende Aufgabe
	 * @return ob geshiftet werden konnte
	 */
	public boolean shiftTaskFwd(Task pTask) {
		int listIndex = findTask(pTask);
		if(listIndex==-1 | listIndex==7)//pTask ist nicht im Projekt / ist in "Abgeschlossen"
			return false;
	
		taskLists[listIndex].remove(pTask);
		taskLists[listIndex+1].add(pTask);
		return true;
	}
	
	/**
	 * bewegt die  Übergebene Task eine Station zurück
	 * @throws IllegalArgumentException falls die Aufgabe nicht im Projekt vorhanden ist oder die Aufgabe erst neu ist
	 * @param pTask , die zu bewegende Aufgabe
	 * @return ob geshiftet werden konnte
	 */
	public boolean shiftTaskBck(Task pTask) {
		int listIndex = findTask(pTask);
		if(listIndex==-1 | listIndex==0)
			return false;
		taskLists[listIndex].remove(pTask);
		taskLists[listIndex-1].add(pTask);
		return true;
	}

	/**
	 *
	 * @return Gibt die Deadline des Projektes zurück
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * Setzt die Deadline auf den Parameter deadline
	 * @param deadline Die neue Deadline für das Projekt
	 */
	public void setDeadline(LocalDate deadline) {
		if(deadline == null)
			throw new IllegalArgumentException("Argument ist null");
		this.deadline = deadline;
	}

	/**
	 * Setzt das Attribut @this.devs auf den Parameter devs
	 * @param devs Die neue Liste von Developern
	 */
	public void setDevsTest(LinkedList<Developer> devs) {
		this.devs = devs;
	}
}
