package model;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testet die Klasse {@link Company}
 *
 * @author Malte Sichtermann
 */
public class CompanyTest {
    private Company company;
    private String name;
    private Team team;
    private Developer developer;
    private LinkedList teams;
    /**
     * setUp der Testumgebung vor dem Testen aller Methoden
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        name = "TestName";
        team = new Team(name,"testLeader");
        developer = new Developer(name);
        company = new Company(name);
        assertTrue(company.addTeam(team));
        assertTrue(company.addDeveloper(developer));
    }

    /**
     * Testet den Konstruktor
     */
    @Test
    public void testKonstruktor() {
        assertEquals(name,company.getName());
    }

    /**
     * Testet die Methode remove Team
     */
    @Test
    public void removeTeam() {

        assertEquals(1,company.getTeams().size());
        assertTrue(company.removeTeam(team));
        assertEquals(0,company.getTeams().size());
    }

    /**
     * Testet die Methode addTeam
     */
    @Test
    public void addTeam() {
        assertEquals(1,company.getTeams().size());
        Team team1 = new Team("testName1","testLeader1");
        assertTrue(company.addTeam(team1));
        assertEquals(2,company.getTeams().size());
    }

    /**
     * Testet die Methode remove Developer
     */
    @Test
    public void removeDeveloper() {
        assertEquals(1,company.getDevs().size());
        assertTrue(company.removeDeveloper(developer));
        assertEquals(0,company.getDevs().size());
    }

    /**
     * Testet die Methode addDeveloper.
     */
    @Test
    public void addDeveloper() {
        assertEquals(1,company.getDevs().size());
        Developer developer1 = new Developer("testName1");
        assertTrue(company.addDeveloper(developer1));
        assertEquals(2,company.getDevs().size());
    }

    /**
     * Testet die Methode getTeams
     */
    @Test
    public void getTeams() {
        assertNotNull(company.getTeams());
    }

    /**
     * Testet die Methode getDevs
     */
    @Test
    public void getDevs() {
        assertNotNull(company.getDevs());
    }

    /**
     * Testet die Methode getName
     */
    @Test
    public void getName() {
        assertNotNull(company.getName());
    }
}