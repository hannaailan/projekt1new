package controller;

import model.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;



/**
 *die Klasse TeamControllerTest testen
 */
public class TeamControllerTest {
	
	private TeamController tC;
	private MainController mC;
	private String name;
	private String projektLeader;
	private Company company;
	private Team team;
	
	
	
	/**
	 * vor dem Test eine neue Testumgebung erzeugen
	 *
	 * @throws Exception Mögliche Fehler beim setUp
	 */
	@Before
	public void setUp() throws Exception {
		mC= new MainController();
		tC= new TeamController(mC);
		name="Team-Name-Test";
		projektLeader="ProjectLeader-Test";
		company=new Company("cName");
		team= new Team(name,projektLeader);
		assertEquals(name,team.getName());
	}
	
	
	
	
	/**
	 * die Methode testCreateTeam() testen
	 */
	@Test
	
	public void testCreateTeam(){
        assertEquals(0, company.getTeams().size());
        tC.createTeam(company,name,projektLeader);
        assertEquals(1, company.getTeams().size());
        
	}
	
	/**
	 * die Methode testDeleteTeam() testen
	 */
	@Test
	
	public void testDeleteTeam() {
		assertEquals(0, company.getTeams().size());
		assertTrue(company.addTeam(team));
		assertEquals(1, company.getTeams().size());
		tC.deleteTeam(company,team);
		assertEquals(0, company.getTeams().size());
	}
	
	/**
	 * die Methode testEditTeam() testen
	 */
	@Test
	
	public void testEditTeam() {
		String newName="neue Name";
		String newBoss="neuer Chef";
		tC.editTeam(team,newName,newBoss);
		assertEquals(newName,team.getName());
		assertEquals(newBoss,team.getProjectLeader());
	}
}

