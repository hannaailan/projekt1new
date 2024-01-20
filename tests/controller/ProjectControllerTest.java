package controller;

import model.Developer;
import model.Project;
import model.Task;
import model.Team;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Testklasse für den ProjectController
 */
public class ProjectControllerTest {

    private static final Team TEAM = new Team("beispiel", "beispiel");
    private static final String NAME = "b";
    private static final String DESCRIPTION = "b";
    private static final LocalDate  DEADLINE = LocalDate.now();
    private static final LocalDate DEADLY = LocalDate.now();
    private static final Project test = new Project(NAME, DESCRIPTION, DEADLINE);
    private static final Task TASK = new Task(test, NAME, DESCRIPTION, DEADLY);
    private static final Developer DEV = new Developer("Nenad hehe");

    private ProjectController projectController;
    //private static final String CONTENT = "Test - ProjectController";

    /**
     * Setup welches vor dem testen ausgeführt wird
     */
    @Before
    public void setUp() {
        MainController mainController = new MainController();
        projectController = mainController.getProjectController();

    }

    /**
     * Testmethode für createProject
     */
    @Test
    public void createProject() {
        Team TEAM = new Team("beispiel", "beispiel");
        assertNotNull(TEAM);
        projectController.createProject(TEAM, NAME, DESCRIPTION, DEADLINE);
        assertNotNull(TEAM.getActiveProject());

    }

    /**
     * Testmethode für editProject
     */
    @Test
    public void editProject() {
        assertNotNull(test);
        assertEquals(NAME,test.getName());
        assertEquals(DESCRIPTION, test.getDescribtion());
        assertEquals(DEADLINE, test.getDeadline());
        projectController.editProject(test, "", "", LocalDate.now().plusDays(1));
        assertNotEquals(NAME,test.getName());
        assertNotEquals(DESCRIPTION, test.getDescribtion());
        assertNotEquals(DEADLINE, test.getDeadline());
    }

    /**
     * Testmethode für deleteProject
     */
    @Test
    public void deleteProject() {
        if(TEAM.getActiveProject() == null)
            TEAM.addProject(test);
        assertEquals(TEAM.getActiveProject(), test);
        projectController.deleteProject(TEAM);
        assertNotEquals(TEAM.getActiveProject(), test);
    }

    /**
     * Testmethode für archiveProject
     */
    @Test
    public void archiveProject() {
        if(TEAM.getActiveProject() == null)
            TEAM.addProject(test);
        assertNotNull(TEAM.getArchivedProjects());
        int a = TEAM.getArchivedProjects().size();
        TEAM.archiveActiveProject();
        assertNotEquals(TEAM.getArchivedProjects().size(), a);
    }

    /**
     * Testmethode für completeTask
     */
    @Test
    public void completeTask() {
        if(!TASK.isWorkedAt())
            TASK.startWorking(DEV);
        projectController.completeTask(TASK);
        assertFalse(TASK.isWorkedAt());

    }

    /**
     * Testmethode für assignTask
     */
    @Test
    public void assignTask() {
        if(TASK.isWorkedAt())
            TASK.endWorking();
        projectController.assignTask(DEV, TASK);
        assertTrue(TASK.isWorkedAt());
    }

    /**
     * Testmethode für unassignTask
     */
    @Test
    public void unassignTask() {
        if(!TASK.isWorkedAt())
            TASK.startWorking(DEV);
        projectController.unassignTask(TASK);
        assertFalse(TASK.isWorkedAt());

    }
}