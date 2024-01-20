package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import model.Company;

/**
 * IOController-Klasse zum Laden und Speichern der Savefile
 */
public class IOController {

	
	/**
	 * Filename zum speichern
	 */
	private static final File SAVE_FILE = new File("save");

	/**
	 * Die Referenz auf den zentralen Controller, der zum Austausch zwischen den
	 * Controllern dient.
	 */
	private MainController mc;

	/**
	 * Konstruktor von IOController
	 *
	 * @param pMC Der übergebene MainController
	 */
	public IOController(MainController pMC) {
		this.mc=pMC;
	}

	
	/**
	 * Lädt die Company-Daten aus der Datei.
	 *
	 */
	public void load() {
		if (!SAVE_FILE.exists()) {
			return;
		}
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(SAVE_FILE));
			

			Company company = (Company) stream.readObject();
			mc.setCompany(company);
			stream.close();
		
		} catch (Exception e) {
			System.err.println("Ladefehler");
			e.printStackTrace();
		}
	}

	
	/**
	 * Speichert die Company-Daten in der Datei.
	 * Die Exception wird geworfen, wenn beim Speichern ein Fehler auftritt.
	 */
	public void save() {
		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
			stream.writeObject(mc.getCompany());
			stream.close();
		}  catch (IOException e) {
			System.err.println("Speicherfehler");
			e.printStackTrace();
		}
	}

}
