package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TeamTest {

    private Team team;
    @Before
    public void setUp(){
        team = new Team("n","p");
    }

    @Test
    public void testKonstruktor() {
        String name = "name";
        String pl= "pl";

        team = new Team(name,pl);

        assertEquals(name,team.getName());
        assertEquals(pl,team.getProjectLeader());

        Developer dev = new Developer("Projectleader");

        team = new Team(name,dev);

        assertEquals(name,team.getName());
        assertEquals(dev.getName(),team.getProjectLeader());
    }

    @Test
    public void testAddDeveloper(){
        Developer dev = new Developer("devname");

        assertTrue(team.addDeveloper(dev));

        assertTrue(team.getDevelopers().contains(dev));
    }

    @Test
    public void testremoveDeveloper(){
        Developer dev = new Developer("devname");

        team.addDeveloper(dev);

        assertTrue(team.removeDeveloper(dev));

        assertFalse(team.getDevelopers().contains(dev));

        team.addDeveloper(dev);

        assertTrue(team.removeDeveloper(dev.getDevID()));

        assertFalse(team.getDevelopers().contains(dev));
    }

    @Test
    public void testAddProject(){
        Project pro = new Project("n","b", LocalDate.now().plusDays(1));

        assertNull(team.getActiveProject());

        assertTrue(team.addProject(pro));

        assertEquals(pro,team.getActiveProject());
    }

    /**
     *
     */
    @Test
    public void testRemoveProject(){
        Project pro = new Project("n","b", LocalDate.now().plusDays(1));

        team.addProject(pro);

        assertEquals(pro,team.removeProject());

        assertNull(team.getActiveProject());
    }

    @Test
    public void testArchiveActiveProject(){
        Project pro = new Project("n","b", LocalDate.now().plusDays(1));

        team.addProject(pro);

        assertEquals(pro,team.getActiveProject());

        team.archiveActiveProject();

        assertTrue(team.getArchivedProjects().contains(pro));
    }
}
