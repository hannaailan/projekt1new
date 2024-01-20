package model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;


/**
 * 
 * @author Tobias
 *
 */
public class Developer implements Serializable {

	/**
	 * uneindeutiger Name des Developers
	 */
	private String name;
	
	
	private boolean isWorking=false;
	
	/**
	 * wird benutzt um die eindeutige Id eines Developers zu bestimmen
	 * @see #devID
	 */
	public static int globalID=0;
	
	/**
	 * eindeutige ID des Developers
	 */
	private final int devID=globalID++;
	
	
	
	/**
	 * Representiert den aktuellen Wochentag als Zahl
	 * Bewegt sich im Intervall  [0,6]
	 * Wird benutzt um den richtigen wert in "countTask zu setzten 
	 */
	private int currentDay=LocalDate.now().getDayOfWeek().getValue()-1;

	private LinkedList<Duration> processingTime = new LinkedList<>();
	
	/**
	 * Hält den zeitpunkt fest an dem der Developer angefangen hat zu arbeiten
	 * @see #startWorking()
	 */
	private LocalDateTime startTime;
	
	/**
	 * beinhaltet die Anzahl der Bearbeiteten aufgaben des Developers in den letzten sieben Tage
	 * 0:Montag
	 * 1:Dienstag
	 * ...
	 * 6:Sonntag
	 * Aktueller Tag: {@link #currentDay}
	 */
	private int[] countTasks = new int[7];

	/**
	 * Konstruktor von Developer
	 * @param pName Der Name von dem neuen Deloper
	 */
	public Developer(String pName){
		name = pName;
	}
	/**
	 * Falls der Developer nicht arbeitet, wird {@link #startTime} auf die aktuelle Zeit und {@link #isWorking} auf true gesetzt
	 * @throws IllegalStateException falls {@link #isWorking} = true
	 */
	public void startWorking() {
		if(isWorking)
			throw new IllegalStateException("Entwickler arbeitet schon");
		isWorking=true;
		startTime=LocalDateTime.now();
	}
	
	/**
	 * Falls der Developer arbeitet  wird die Arbeitszeit {@link #processingTime} hinzugefügt und  {@link #isWorking} auf false gesetzt
	 * @throws IllegalStateException falls {@link #isWorking} = false
	 */
	public void endWorking() {
		if(!isWorking)
			throw new IllegalStateException("Aufgabe wird nicht bearbeitet");
		isWorking=false;
		processingTime.add(Duration.between(startTime, LocalDateTime.now()));
		
	}
	
	/**
	 * gibt zurück ob der Developer grade arbeitet
	 * @return isWorking
	 */
	public boolean isWorking() {
		return isWorking;
	}
	
	/**
	 * 
	 * @return countTasks
	 */
	public int getCountTasksSum() {
		return Arrays.stream(countTasks).sum();
	}
	
	/**
	 * 
	 * @return processingTime
	 */
	public LinkedList<Duration> getProcessingTime() {
		return processingTime;
	}
	
	/**
	 * 
	 * @return {@link #devID}
	 */
	public int getDevID() {
		return devID;
	}
	
	/**
	 * 
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * überschreibt den Namen des Developers
	 * @throws IllegalArgumentException falls name "null" ist
	 * @param name , neuer Developername
	 */
	public void setName(String name) {
		if(name==null)
			throw new IllegalArgumentException("name ist null");
		this.name = name;
	}
	
	/**
	 * Falls {@link #currentDay} nicht den aktuellen Tag representiert:
	 * 	Setzt den Wert von {@link #countTasks} am aktuellen Tag auf 0 und aktualisiert den {@link #currentDay} 
	 */
	public void updateCountTasks() {
		if(currentDay==LocalDate.now().getDayOfWeek().getValue()-1)
			return;
		
		currentDay=LocalDate.now().getDayOfWeek().getValue()-1;
		countTasks[currentDay]=0;
	}
	
	/**
	 * erhöht die Anzahl der Aufgaben die der Developer am aktuellen Tag erledigt hat
	 * @see #countTasks
	 */
	public void increaseCompletedTask() {
		countTasks[currentDay]++;
	}
	
	/**
	 * summiert  die Zeit auf die der Developer gearbeitet hat
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
	 * Erlaubt es für den StatisticControllerTest eine Liste von bearbeitungszeiten manuell zu setzen
	 * @param processingTime Übergebene Liste von Bearbeitungszeiten
	 */
	public void setProcessingTimeTest(LinkedList<Duration> processingTime) {
		this.processingTime = processingTime;
	}


}
