package model;

import static org.junit.Assert.*;



import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
/**
 * @author Tobias
 *
 */
public class TaskTest {

	private Task task;
	private Project project;
	/**
	 * 
	 *
	 */
	@Before
	public void setUp(){
		project = new Project("n", "d", LocalDate.now().plusDays(10));
		task=new Task(project);
	}

	/**
	 * 
	 */
	@Test
	public void testKonstruktor() {
		assertNotNull(task.getName());
		assertNotNull(task.getDescription());
		assertNotNull(task.getDeadline());

		String neuerName="neu";
		String neueBesch="neu";
		LocalDate neueDead = LocalDate.now().plusDays(2);

		task=new Task(project,neuerName,neueBesch,neueDead);

		assertEquals(neuerName, task.getName());
		assertEquals(neueBesch, task.getDescription());
		assertEquals(neueDead, task.getDeadline());
	}
	
	/**
	 * 
	 */
	@Test
	public void testAddComment() {
		String message="Nachricht";
		
		task.addComment(message);
		
		assertEquals(message,task.getComments().get(0).getContent());
		 
		Comment com = new Comment(message);
		
		task.addComment(com);
		
		assertTrue(task.getComments().contains(com));

		
	}
	/**
	 * 
	 */
	@Test
	public void testRemoveComment() {
		Comment com = new Comment("message");
		
		task.addComment(com);
		
		assertTrue(task.getComments().contains(com));
		
		assertTrue(task.removeComment(com));
		
	}

}
