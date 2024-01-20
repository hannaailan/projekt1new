package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

import model.Developer;
import model.Team;
import model.Company;

/**
 * Testklasse für den DevController
 */
public class DevControllerTest {
	
	private MainController mainController;
	private DevController devController;
	private String name;
	Developer developer;
	Team team=new Team(name,"Project_Leader");
	private String nameComp; 
	Company comp=new Company(nameComp);
	private int currentDay=LocalDate.now().getDayOfWeek().getValue()-1;

    /**
     * Setup für die Testklasse
     * @throws Exception Wird geworfen wenn das Setup fehlschlägt
     */
	@Before
	public void setUp() throws Exception {
		mainController = new MainController();
        devController = new DevController(mainController);
        name = "Test-Developer_Name";
        developer= new Developer(name);        
        assertEquals(name, developer.getName());
	}

    /**
     * Testmethode für den Konstruktor
     */
	@Test
	public void testConstructor() {
		assertNotNull(devController.getMainController());
        assertEquals(mainController, devController.getMainController());
	}

    /**
     * Testmethode für createDev
     */
	@Test
    public void testCreateDev() {	
        assertEquals(0,team.getDevelopers().size());
        devController.createDev(team, name);
       assertEquals(1, team.getDevelopers().size());
    }

    /**
     * Testmethode für deleteDev
     */
    @Test
    public void testDeleteDev() {
		assertEquals(0,team.getDevelopers().size());
        assertTrue(team.addDeveloper(developer));
        assertEquals(1,team.getDevelopers().size());
        devController.deleteDev(team, developer);
        assertEquals(0,team.getDevelopers().size());
	}

    /**
     * Testmethode für editDev
     */
    @Test
    public void testEditDev() {
        String newName = "Neuer Developer";
        devController.editDev(developer,newName);
        assertEquals(newName, developer.getName());
    }

    /**
     * Testmethode für dailyUpdate
     */
    @Test
    public void testdailyUpdate() {
        int [] arr=new int[7];
       assertEquals(0,arr[currentDay]);
       developer.increaseCompletedTask();
       devController.dailyUpdate(comp);
       assertEquals(0,arr[currentDay]);
    }
}
