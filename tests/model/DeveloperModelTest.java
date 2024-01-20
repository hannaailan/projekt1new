package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testet die Klasse {@link DeveloperModel}
 *
 * @author Malte Sichtermann
 */
public class DeveloperModelTest {

    private DeveloperModel developerModel;
    private int ranked;
    private int devID;
    private String name;
    private int countTasks;

    /**
     * SetUp einer Testumgebung vor dem Testen der Methoden
     *
     * @throws Exception Mögliche fehler beim setUp
     */
    @Before
    public void setUp() throws Exception {
        ranked = 1;
        devID = 1;
        name = "TestName";
        countTasks = 1;
        developerModel = new DeveloperModel(ranked,devID,name,countTasks);
    }

    /**
     * Testet getRanked.
     */
    @Test
    public void getRanked() {
        assertEquals(ranked,developerModel.getRanked());
    }

    /**
     * Testet setRanked.
     */
    @Test
    public void setRanked() {

        developerModel.setRanked(2);
        assertEquals(2,developerModel.getRanked());
    }

    /**
     * Testet getDevID.
     */
    @Test
    public void getDevID() {
        assertEquals(devID,developerModel.getDevID());
    }

    /**
     * Testet setDevID.
     */
    @Test
    public void setDevID() {
        developerModel.setDevID(2);
        assertEquals(2,developerModel.getDevID());
    }

    /**
     * Testet getName.
     */
    @Test
    public void getName() {
        assertEquals(name,developerModel.getName());
    }

    /**
     * Testet setName.
     */
    @Test
    public void setName() {
        developerModel.setName("testNew");
        assertEquals("testNew",developerModel.getName());
    }

    /**
     * Testet getCountTasks.
     */
    @Test
    public void getCountTasks() {
        assertEquals(countTasks,developerModel.getCountTasks());
    }

    /**
     * Testet setCountTasks.
     */
    @Test
    public void setCountTasks() {
        developerModel.setCountTasks(2);
        assertEquals(2,developerModel.getCountTasks());
    }
}