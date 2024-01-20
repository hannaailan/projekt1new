package controller;

import model.Comment;
import model.Project;
import model.Task;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Testet die Methoden der Klasse CommentController
 */
public class CommentControllerTest {

    private CommentController commentController;
    private MainController mainController;
    private String content;
    private LocalDate localDate;
    private Project project;
    private Task task;
    private Comment comment;

    /**
     * Erzeugt vor dem Klassentest eine neue Testumgebung
     * @throws Exception Mögliche Exceptions beim SetUp
     */
    @Before
    public void setUp() throws Exception {
        mainController = new MainController();
        commentController = new CommentController(mainController);
        content = "Test-Kommentar";
        localDate = LocalDate.now();
        project = new Project("test-Name", "test-Beschreibung", localDate);
        task = new Task(project);
        comment = new Comment();
        comment.setContent(content);
        assertEquals(content, comment.getContent());
        //CommentAUI commentAUI = new CommentAUI();
        //commentController.setCommentAUI();//TODO anpassen sobald die Klasse da ist und gesetzt werden kann
        //assertEquals(commentAUI, commentController.getCommentAUI());
    }

    /**
     * Testet den ob im Konstruktor der Klasse CommentController der übergebene MainController korrekt zugewiesen wird
     */
    @Test
    public void testConstructor() {
        assertNotNull(commentController.getMainController());
        assertEquals(mainController, commentController.getMainController());
    }

    /**
     * Testet ob ein Kommentar korrekt erstellt und einer Aufgabe zugewiesen wird
     */
    @Test
    public void testCreateComment() {
        assertEquals(0, task.getComments().size());
        commentController.createComment(task, content);
        assertEquals(1, task.getComments().size());
        assertEquals(content, task.getComments().getFirst().getContent());
    }

    /**
     * Testet ob ein Kommentar nach dem Löschen wirklich nicht mehr in der Aufgabe existiert
     */
    @Test
    public void testDeleteComment() {
        assertEquals(0, task.getComments().size());
        assertTrue(task.addComment(comment));
        assertEquals(1, task.getComments().size());
        assertEquals(comment, task.getComments().getFirst());
        commentController.deleteComment(task, comment);
        assertEquals(0, task.getComments().size());
    }

    /**
     * Testet ob ein Kommentar nach dem bearbeiten wirklich den neu gesetzten Inhalt hat
     */
    @Test
    public void testEditComment() {
        String newContent = "Ein neuer Kommentarinhalt";
        commentController.editComment(comment, newContent);
        assertEquals(newContent, comment.getContent());
    }
}