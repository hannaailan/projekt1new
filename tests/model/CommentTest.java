package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author sopr046
 *
 */
public class CommentTest {

	private Comment comment;
	private static final String CONTENT = "Test-Kommentar";
	/**
	 * Erzeugt vor jeder Test-Methode eine neue Testumgebung
	 *
	 */
	@Before
	public void setUp() {
		comment = new Comment(CONTENT);
	}



	/**
	 * Erzeugt nach jeder Test-Methode eine neue Testumgebung
	 */
	@Test
	public void testConstructor() {
		assertNotNull(comment.getTimestamp());
		assertEquals(CONTENT, comment.getContent());
	}
	
	/**
	 * Erzeugt nach jeder Test-Methode eine neue Testumgebung
	 */
	@Test
	public void testSetContent() {
		assertEquals(CONTENT, comment.getContent());
		String neu = "neuer \n Content";
		comment.setContent(neu);
		assertEquals(neu, comment.getContent());
		
	}

}
