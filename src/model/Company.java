package model;

import java.util.LinkedList;

/**
 * Hauptklasse: Verwaltet eine Liste von {@link Team} und eine Liste von {@link Developer}
 * @author Tobias
 *
 */
/**
 * @author Tobias
 *
 */

public class Company {
	
	/**
	 * Eindeutiger nichtänderbarer Name der Firma
	 */
	private final String name;
	
	/**
	 * Liste der {@link Team}s
	 */
	private LinkedList<Team> teams=new LinkedList<Team>();
	
	/**
	 * Liste der {@link Developer}s
	 */
	private LinkedList<Developer> devs = new LinkedList<Developer>();

	/**
	 * Konstruktor
	 * @param name der Firma, kann nachher nicht mehr verändert werden
	 */
	public Company(String name) {
		this.name = name;
		
	}

	/**
	 *  Entfernt das Team
	 *
	 * @throws IllegalArgumentException falls pTeam "null" ist
	 * @param pTeam, das zu löschende Team
	 * @return ob das Team überhaupt vorhanden war
	 */
	public boolean removeTeam(Team pTeam) {
		if(pTeam==null)
			throw new IllegalArgumentException("pTeam ist null");

		return teams.remove(pTeam);
		
	}

	/**
	 * fügt den Parameter als neues Team hinzu
	 *
	 * @throws IllegalArgumentException falls pTeam "null" ist
	 * @param pTeam, das neue Team
	 * @return ob das Team noch nicht vorhanden war
	 * 	ist pTeam bereits vorhanden wird es nicht erneut hinzugefügt
	 */
	public boolean addTeam(Team pTeam) {
		if(pTeam==null)
			throw new IllegalArgumentException("pTeam ist null");
		if(teams.contains(pTeam))
			return false;
		teams.add(pTeam);
		return true;
	}

	/**
	 *  Entfernt den Developer
	 *  Entfernt den Developer nicht aus seinem Team!
	 *
	 * @throws IllegalArgumentException falls pDev "null" ist
	 * @param pDev, den zu löschenden Developer
	 * @return ob der Developer überhaupt vorhanden war
	 */
	public boolean removeDeveloper(Developer pDev) {
		if(pDev==null)
			throw new IllegalArgumentException("pDev ist null");
		return devs.remove(pDev);
		
	}

	/**
	 *
	 * fügt den Parameter als neue Developer hinzu
	 *
	 * @throws IllegalArgumentException falls pDev "null" ist
	 * @param pDev, der neue Developer
	 * @return ob der Developer bereits vorhanden war
	 * 	ist pDev bereits vorhanden wird er nicht erneut hinzugefügt
	 */
	public boolean addDeveloper(Developer pDev) {
		if(pDev==null)
			throw new IllegalArgumentException("pDev ist null");
		if(devs.contains(pDev))
			return false;
		devs.add(pDev);
		return true;
	}

	/**
	 *
	 * @return Gibt eine Liste mit allen Teams zurück.
	 */
	public LinkedList<Team> getTeams(){
		return teams;
	}

	/**
	 *
	 * @return Gibt eine Liste mit allen Entwicklern zurück.
	 */
	public LinkedList<Developer> getDevs(){
		return devs;
	}

	/**
	 *
	 * @return Gibt den Namen der Company zurück.
	 */
	public String getName() {
		return name;
	}
}
