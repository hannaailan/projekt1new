package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Das Developer Model dient dazu, für das Ranking in der StatisticView ein Objekt mit mehr Informationen
 * als beim normalen Entwickler zu übergeben
 */
public class DeveloperModel {

    private SimpleIntegerProperty ranked;
    private SimpleIntegerProperty devID;
    private SimpleStringProperty name;
    private SimpleIntegerProperty countTasks;


    /**
     * Erzeugt ein neues DeveloperModel und setzt die Attribute auf übergebene Parameter
     *
     * @param ranked     Übergebener Rang
     * @param devID      Übergebene devID
     * @param name       Übergebener Name
     * @param countTasks Übergebene Anzahl der Aufgaben
     */
    public DeveloperModel(Integer ranked, Integer devID, String name, Integer countTasks) {
        this.ranked = new SimpleIntegerProperty(ranked);
        this.name = new SimpleStringProperty(name);
        this.countTasks = new SimpleIntegerProperty(countTasks);
        this.devID = new SimpleIntegerProperty(devID);
    }

    /**
     * Gibt den Rang zurück
     *
     * @return Rang
     */
    public int getRanked() {
        return ranked.get();
    }


    /**
     * Setzt den Rang.
     *
     * @param ranked Übergebener Rang
     */
    public void setRanked(int ranked) {
        this.ranked.set(ranked);
    }

    /**
     * Gibt die devID zurück
     *
     * @return devID
     */
    public int getDevID() {
        return devID.get();
    }

    /**
     * Setzt die devID
     *
     * @param devID Übergebene devID
     */
    public void setDevID(Integer devID) {
        this.devID.set(devID);
    }

    /**
     * Gibt den Namen zurück.
     *
     * @return Name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Setzt den Namen.
     *
     * @param name Übergebener name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gibt die Anzahl der Aufgaben zurück.
     *
     * @return Anzahl der Aufgaben
     */
    public int getCountTasks() {
        return countTasks.get();
    }

    /**
     * Setzt Anzahl der Aufgaben.
     *
     * @param countTasks Übergebene Anzahl der Aufgaben
     */
    public void setCountTasks(Integer countTasks) {
        this.countTasks.set(countTasks);
    }
}
