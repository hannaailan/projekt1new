package controller;

import model.Comment;
import model.Task;
import view.CommentAUI;


/**
 * Die CommentController-Klasse welche die Kommentare verwaltet
 */
public class CommentController {

	private MainController mainController;

	private CommentAUI commentAUI;

	/**
	 * Bei dem erzeugen der Klasse CommentController wird hier der
	 * MainController pMC dem Attribut mainController zugewiesen.
	 *
	 * @param pMC Der übergebene MainController
	 * @throws IllegalArgumentException Wird geworfen, wenn der Parameter ein null-Wert ist.
	 */
	public CommentController(MainController pMC) {
		if (pMC != null) {
			this.mainController = pMC;
		} else {
			throw new IllegalArgumentException("Parameter ist null");
		}
	}

	/**
	 * Die Methode createComment erstellt einen neuen Kommentar mit dem Inhalt pContent. Dieser Kommentar
	 * wird anschließend der Aufgabe pTask hinzugefügt.
	 *
	 * @param pTask    Die Aufgabe wozu der Kommentar hinzugefügt werden soll.
	 * @param pContent Der Inhalt des Kommenter welcher hinzugefügt werden soll.
	 * @throws IllegalArgumentException Wird geworfen, falls mindestens ein Parameter einen null-Wert enthält.
	 * @throws NullPointerException     Wird geworfen falls die CommentAUI noch nicht gesetzt wurde.
	 */
	public void createComment(Task pTask, String pContent) {
		if (pTask != null && pContent != null) {
			Comment newComment = new Comment(pContent);
			pTask.addComment(newComment);
			if (this.commentAUI != null) {
				this.commentAUI.refreshComment();
			} else {
				//throw new NullPointerException("Die CommentAUI wurde noch nicht gesetzt");//TODO anpassen sobald es die Klasse gibt
			}
		} else {
			throw new IllegalArgumentException("Mindestens ein Parameter ist null");
		}
	}

	/**
	 * Die Methode deleteComment entfernt den Kommentar pComment aus der Liste aller Kommentare von der Aufgabe pTask.
	 *
	 * @param pTask    Die Aufgabe woraus der Kommentar gelöscht werden soll.
	 * @param pComment Der zu löschende Kommentar.
	 * @throws IllegalArgumentException Wird geworfen, falls mindestens ein Parameter einen null-Wert enthält.
	 * @throws NullPointerException     Wird geworfen falls die CommentAUI noch nicht gesetzt wurde.
	 */
	public void deleteComment(Task pTask, Comment pComment) {
		if (pComment != null && pTask != null){
			pTask.removeComment(pComment);
			if (this.commentAUI != null) {
				this.commentAUI.refreshComment();
			} else {
				//throw new NullPointerException("Die CommentAUI wurde noch nicht gesetzt");//TODO anpassen sobald es die Klasse gibt
			}
		} else {
			throw new IllegalArgumentException("Mindestens ein Parameter ist null");
		}
	}

	/**
	 * Die Methode editComment setzt den Kommentarinhalt von pComment auf pContent.
	 *
	 * @param pComment Der Kommentar wo der Inhalt geändert werden soll.
	 * @param pContent Der neue Inhalt für den gegebenen Kommenter pComment.
	 * @throws IllegalArgumentException Wird geworfen, falls mindestens ein Parameter einen null-Wert enthält.
	 * @throws NullPointerException     Wird geworfen falls die CommentAUI noch nicht gesetzt wurde.
	 */
	public void editComment(Comment pComment, String pContent) {
		if (pComment != null && pContent != null){
			pComment.setContent(pContent);
			if (this.commentAUI != null) {
				this.commentAUI.refreshComment();
			} else {
				//throw new NullPointerException("Die CommentAUI wurde noch nicht gesetzt");//TODO anpassen sobald es die Klasse gibt
			}
		} else {
			throw new IllegalArgumentException("Mindestens ein Parameter ist null");
		}
	}

	/**
	 * Setze das @link this.commentAUI.
	 *
	 * @param pCAUI Das neue CommentAUI Objekt.
	 * @throws IllegalArgumentException Wird geworfen, wenn der Parameter ein null-Wert ist.
	 */
	public void setCommentAUI(CommentAUI pCAUI){
		if (pCAUI != null) {
			this.commentAUI = pCAUI;
		} else {
			throw new IllegalArgumentException("Mindestens ein Parameter ist null");
		}
	}

	/**
	 * Get-Methode für @link this.mainController.
	 *
	 * @return Das MainController Objekt von CommentController.
	 */
	public MainController getMainController(){
		return this.mainController;
	}

	/**
	 * Get-Methode für @link this.commentAUI.
	 *
	 * @return Das CommentAUI Objekt von CommentController.
	 */
	public CommentAUI getCommentAUI(){
		return this.commentAUI;
	}

}
