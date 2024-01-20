package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testet den MainController.
 *
 *
 */
public class MainControllerTest {
	
	private MainController mc;
	/**
	 * Testet den Konstruktor.
	 *
	 */
	

		/**
		 * Erzeugt vor jeder Test-Methode eine neue Testumgebung.
		 *
		 * @throws Exception
		 *             Mögliche Exceptions beim setUp.
		 */
		@Before
		public void setUp() throws Exception {
			mc = new MainController();
		}

		/**
		 * Testet den MainController-Konstruktor.
		 * Alle anderen Konstruktoren sollten initialisiert und neues Objekt
		 * erzeugt sein.
		 */
		@Test
		public void test() {
			assertNotNull(mc.getProjectController() );
			assertNotNull(mc.getDevController());
			assertNotNull(mc.getiOController());
			assertNotNull(mc.getTeamController());
			assertNotNull(mc.getCommentController());
			assertNotNull(mc.getStatisticController());
			assertNotNull(mc.getTaskController());
			assertNotNull(mc.getCompany());

		

		}
		
}
