package model;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * Kommentar eines {@link Task}
 * Ein kommentar hat lediglich ein Inhalt und eine Zeitstempel
 * Der Inhalt ist jederzeit änderbar. wird dem Konstruktar kein Inhalt übergeben erhählt der Kommentar einen Standartzustand
 * DEr Zeitstempel wird eim der stellen gesetzt und ist dann nciht mehr änderbar. Man kann ihn aber noch auslesen
 * @author Tobias
 *
 */
public class Comment implements Serializable {
	
	/**
	 * Inhalt des Kommentars
	 */
	private String content;

	/**
	 * Zeitstempel des Kommentars
	 */
	private final LocalDateTime timestamp=LocalDateTime.now();

	/**
	 * Konstruktor ohne übergebenen Inhalt
	 */
	public Comment() {
		setContent("Kommentar hat noch keinen Inhalt");
	}
	
	/**
	 * Konstruktor mit übergebenen Inhalt
	 * 
	 * @throws IllegalArgumentException falls pContent "null" ist.
	 * @param pContent , Kommentarinhalt
	 */
	public Comment(String pContent) {
		setContent(pContent);
	}
	
	/**
	 * gibt den Inhalt des Kommentars zurück
	 * 
	 * @return Der Inhalt des Kommentars als String 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Setzt den Inhalt des Kommentars.
	 * Überschreibt dabei den alten Inhalt.
	 * 
	 * @throws IllegalArgumentException falls pContent "null" ist.
	 * @param pContent , neuer Kommentarinhalt
	 */
	public void setContent(String pContent) {
		if(pContent==null)
			throw new IllegalArgumentException("Content ist null");
		this.content = pContent;
	}
	
	/**
	 * Gibt den Zeitstempel des Kommentars zurück
	 * 
	 * @return den Zeitstempel des Kommentars
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
