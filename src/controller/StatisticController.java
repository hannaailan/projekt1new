package controller;

import java.util.*;

import model.Developer;

import model.DeveloperModel;
import model.Project;
import model.Task;

/**
 *  Im Statistik Controller werden die Methoden zur Berechnung des Mitarbeiter Rankings und der Statistiken zur
 *  Bearbeitungszeit von Aufgaben und Entwicklern bereitgestellt
 *
 * @author Malte, Sichtermann
 */
public class StatisticController {
	/**
	 * Der Hauptcontroller über welchen alle Zugriffe auf Model, View und Controller laufen
	 */
	private MainController mainController;

	/**
	 * Konstruktor, erzeugt einen Statistik Controller und setzt den Hauptcontroller auf den übergebenen Wert
	 * preconditions: pMC darf nicht null sein
	 *
 	 * @param pMC Durch Aufruf übergebenes Hauptcontroller Objekt
	 */
	public StatisticController(MainController pMC) {
		if(pMC==null)
			throw new IllegalArgumentException("Parameter pMC ist 'null'");
		mainController = pMC;
	}

	/**
	 * Get-Methode für @link this.mainController.
	 *
	 * @return Das MainController Objekt von CommentController.
	 */
	public MainController getMainController(){
		return this.mainController;
	}

	/**
	 * Gibt eine absteigend, entsprechend der Anzahl der bearbeiteten Aufgaben sortierte Liste aller Entwickler zurück
	 * die zur Darstellung des Mitarbeiter Rankings zur verfügung gestellt wird.
	 *
	 * @param pListDev Zu sortierende Liste aller Entwickler
	 * @return resList Die sortierte Liste
	 */
	public List<DeveloperModel> calculateRanking(List<Developer> pListDev) {
		if(pListDev ==null)
			throw new IllegalArgumentException("Parameter pListDev ist 'null'");
		pListDev.sort(Comparator.comparing(Developer::getCountTasksSum));
		List<DeveloperModel> resList = new LinkedList<>();
				for(int i = 0; i < pListDev.size(); i++){
					int devID =  pListDev.get(i).getDevID();
					String name = pListDev.get(i).getName();
					int countTasks = pListDev.get(i).getCountTasksSum();
					resList.add(new DeveloperModel(i, devID, name, countTasks));
				}

		return resList;
	}

	/**
	 * Errechnet jeweils für Entwickler und Aufgaben Minimum, Maximum und Durchschnitt der Bearbeitungszeit
	 * @param pProject Das übergebene Projekt für welches die Statistik berechnet werden soll.
	 * @return stats Array mit 6 Einträgen ([0-2]: Min, Max und Avg von Entwickler; [3-5]: Min, Max und Avg von Aufgaben)
	 */
	public int[] calculateStatistic(Project pProject) {
		if(pProject==null)
			throw new IllegalArgumentException("Parameter pProject ist 'null'");

		int[] stats = new int[6];
		LinkedList<Developer> devs = pProject.getDevs();
		LinkedList<Task>[] taskLists = pProject.getTasks();

		// Erzeugen einer Liste in welche später für jeden Entwickler die Summe seiner Bearbeitungszeit eingetragen wird
		ArrayList<Integer> devDurations = new ArrayList<>();

		// Erzeugen einer Liste in welche später für jede Aufgabe die Summe ihrer Bearbeitungszeit eingetragen wird
		ArrayList<Integer> taskDurations = new ArrayList<>();

		/**
		 * Die Liste aller Entwickler durchlaufen und jeweils die berechnete Summe ihrer Bearbeitungszeit (in Stunden)
		 * in die dafür erzeugte Liste eingetragen.
		 */
		for (Developer dev : devs){
			devDurations.add((int) dev.sumProcessingTime().toHours());
		}

		// Für jeden Eintrag im Feld, welches die Aufgabenlisten verwaltet wird die enthaltene Aufgabenliste durchlaufen
		// und jeweils die berechnete Summe der Bearbeitungszeit pro Aufgabe (in Stunden) in die dafür erzeugte Liste
		// eingetragen.
		for (LinkedList<Task> tL : taskLists){
			for (Task tsk : tL){
				taskDurations.add((int) tsk.sumProcessingTime().toHours());
			}
		}

		// Befüllen des Ausgabearrays mit den Entsprechenden statistischen Werten, die durch Hilfsfunktionen berechnet
		// werden (Bedeutung der Arrayeinträge siehe Methodendokumentation)
		stats[0] = Collections.min(devDurations);
		stats[1] = Collections.max(devDurations);
		int dAvg = 0;
		for(Integer dD : devDurations){
			dAvg += dD;
		}
		stats[2] = Math.round(dAvg/devDurations.size());
		stats[3] = Collections.min(taskDurations);
		stats[4] = Collections.max(taskDurations);
		int tAvg = 0;
		for(Integer tD : taskDurations){
			tAvg += tD;
		}
		stats[5] = Math.round(tAvg/taskDurations.size());
		return stats;
	}

}
