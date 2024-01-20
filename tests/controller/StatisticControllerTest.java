package controller;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Testet die Klasse {@link StatisticController}.
 *
 * @author Malte Sichtermann
 */

public class StatisticControllerTest {

    private MainController mC;
    private StatisticController statController;
    private LocalDate localDate;
    private Project project;
    private Team team;


    /**
     * Erzeugt vor jeder Test-Methode eine neue Testumgebung.
     *
     * @throws Exception mögliche Exceptions die beim setUp auftreten.
     */
    @Before
    public void setUp() throws Exception {

        mC = new MainController();
        statController = new StatisticController(mC);
        localDate = LocalDate.now();
        LocalDate localDate = LocalDate.now();
        project = new Project("TestProject","TestDescr", localDate);
        team = new Team("TestTeam", "TestLeader");
    }

    /**
     * Prüft ob der im Konstruktor übergebene MainController richtig zugewiesen wurde.
     */
    @Test
    public void testConstructor() {
        assertNotNull(statController.getMainController());
        assertEquals(mC, statController.getMainController());
    }

    /**
     * Erstellt eine Test-Liste von 10 Entwicklern, die jeweils eine zufällige Anzahl an Aufgaben zwischen 0 und 50
     * bekommen. Anschließend wird calculateRanking mit der Liste aufgerufen und mit einer manuell sortierten Liste
     * abgeglichen.
     */
    @Test
    public void calculateRanking() {

        List<Developer> listDevs = new LinkedList<>();
        for (int i = 0; i < 10; i++){
            listDevs.add(new Developer("Test" + i));
            int j = (int) (Math.random() * (51));
            while(j >= 0){
                listDevs.get(i).increaseCompletedTask();
                j--;
            }
        }
        assertEquals(10, listDevs.size());

        List<DeveloperModel> resList = statController.calculateRanking(listDevs);
        assertEquals(listDevs.size(), resList.size());
        List<DeveloperModel> testList = new LinkedList<>();
        listDevs.sort(Comparator.comparing(Developer::getCountTasksSum));
        for(int i = 0; i < listDevs.size(); i++){
            int devID =  listDevs.get(i).getDevID();
            String name = listDevs.get(i).getName();
            int countTasks = listDevs.get(i).getCountTasksSum();
            testList.add(new DeveloperModel(i, devID, name, countTasks));
        }
        assertEquals(resList.size(), testList.size());
    }

    /**
     * Es wird zunächst ein Test-Projekt erzeugt, welches je 10 Aufgaben und Entwickler besitzt. Über diesem wird
     * anschließend calculateStatistics  aufgerufen und überprüft ob die von der Methode errechneten Werte im
     * ausgegebenen Array korrekt sind.
     */
    @Test
    public void calculateStatistic() {


        for (int i = 0; i < 10; i++){
            assertTrue(team.addDeveloper(new Developer("Test" + i)));
            assertTrue(project.addTask(new Task(project,"Task" + i,"test",localDate)));

        }
        LinkedList<Duration> testList = new LinkedList<>();
        Duration d;
        for(int i = 0; i < 10; i++){
            d = Duration.between(LocalDateTime.of(2020,1, 1, 0, 0), LocalDateTime.of(2020,1, 1, i, 0));
            assertTrue(testList.add(d));
            team.getDevelopers().get(i).setProcessingTimeTest((LinkedList<Duration>) testList.clone());
            project.getTasks()[0].get(i).setProcessingTimeTest((LinkedList<Duration>) testList.clone());
        }

        project.setDevsTest(team.getDevelopers());

        assertNotNull(project.getDevs());
        assertNotNull(project.getTasks());
        assertEquals(project.getDevs(),team.getDevelopers());
        int[] test = statController.calculateStatistic(project);

        assertEquals(6,test.length);
        assertEquals(0,test[0]);
        assertEquals(45,test[1]);
        assertEquals(16,test[2]);
        assertEquals(0,test[3]);
        assertEquals(45,test[4]);
        assertEquals(16,test[5]);
    }

}