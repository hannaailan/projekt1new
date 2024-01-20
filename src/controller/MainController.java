package controller;

import java.io.File;
import java.io.IOException;

import model.Team;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import model.Company;

import model.Project;


/**
 * MainController-Klasse dient zur Kommunikation zwischen den Controllern und Schnittstelle zur Model-Schicht
 */
public class MainController {

	/**
	 * Das aktuelle Company-Objekt
	 */
	private Company company;
	
	private StatisticController statisticController;

	private ProjectController projectController;

	private TeamController teamController;

	private TaskController taskController;

	private DevController devController;

	private CommentController commentController;

	private IOController iOController;

	private LinkedList<Team> teams;

	/**
	 *  Der Konstruktor. Erzeugt die anderen Controller und ein neues Company-Objekt
	 */
	public MainController() {
		devController = new DevController(this);
		iOController = new IOController(this);
		commentController = new CommentController(this);
		taskController = new TaskController(this);
		teamController = new TeamController(this);
		projectController = new ProjectController(this);
		statisticController = new StatisticController(this);
		company = new Company ("company");
		teams = company.getTeams();
		try {
		iOController.load();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	  }
	
	/** 
	 * Gibt das aktuelle projekt zurück
	 * @return company-Objekt
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 *
	 */
	public void setCompany(Company pCompany){
		if(pCompany==null)
			return;
		this.company=pCompany;

	}
	/**
	 * Gibt die aktuelle teamList zurück
	 * @return ListTeam-Objekt
	 */
	public LinkedList<Team> getTeams() {return teams;}

	/**
	 * Gibt den Statistik-Controller zurück
	 * @return der Statistik-Controller
	 */
	public StatisticController getStatisticController() {
		return statisticController;
	}

	/**
	 * Gibt den Projekt-Controller zurück
	 * @return der Projekt-Controller
	 */
	public ProjectController getProjectController() {
		return projectController;
	}

	/**
	 * Gibt den TeamController zurück  
	 * @return der team-Controller
	 */
	public TeamController getTeamController() {
		return teamController;
	}

	/**
	 * Gibt den TaskController zurück
	 * @return der Task-Comttroller
	 */
	public TaskController getTaskController() {
		return taskController;
	}

	/** Gibt den DevController zurück
	 * @return der dev-Controller
	 */
	public DevController getDevController() {
		return devController;
	}

	/**
	 * Gibt den Comment-Controller zurück
	 * @return der CommentController 
	 */
	public CommentController getCommentController() {
		return commentController;
	}

	/**
	 * Gibt den IO-Controller zurück
	 * @return IO-Controller
	 */
	public IOController getiOController() {
		return iOController;
	}
	/**
	 * Gibt jedes Projekt mit der Beschreibung und deadline auf der gleichen pdfSeite zurück
	 * @param pProjects Liste aller Projekte die als Pdf exportiert werden sollen
	 * @throws java.io.IOException möglicher Fehler
	 */
	public void exportPdfAllOnOne(List<Project> pProjects) throws IOException {
			
			// Fehler wenn der Seite zu Ende ist wird keine neue Seite erstellt! 
			PDDocument doc = null;
			try
			{
			    doc = new PDDocument();
			    PDPage page = new PDPage();
			    doc.addPage(page);
			    PDPageContentStream contentStream = new PDPageContentStream(doc, page);
	
			    PDFont pdfFont = PDType1Font.HELVETICA;
			    float fontSize = 25;
			    float leading = 1.5f * fontSize;
	
			    PDRectangle mediabox = page.getMediaBox();
			    float margin = 72;
			    float width = mediabox.getWidth() - 2*margin;
			    float startX = mediabox.getLowerLeftX() + margin;
			    float startY = mediabox.getUpperRightY() - margin;
	
			    String text = ""; 
			    for(int i=0; i<pProjects.size(); i++)
			    { 
			    	text +=  " Projekt Name " + pProjects.get(i).getName() ;
			    	text +=  " Projekt Deadline " + pProjects.get(i).getDeadline();
			    	text +=  " Projekt Describtion " + pProjects.get(i).getDescribtion() ;
			    }
			    List<String> lines = new ArrayList<String>();
			    int lastSpace = -1;
			    while (text.length() > 0)
			    {
			        int spaceIndex = text.indexOf(' ', lastSpace + 1);
			        if (spaceIndex < 0)
			            spaceIndex = text.length();
			        String subString = text.substring(0, spaceIndex);
			        float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
			        System.out.printf("'%s' - %f of %f\n", subString, size, width);
			        if (size > width)
			        {
			            if (lastSpace < 0)
			                lastSpace = spaceIndex;
			            subString = text.substring(0, lastSpace);
			            lines.add(subString);
			            text = text.substring(lastSpace).trim();
			            System.out.printf("'%s' is line\n", subString);
			            lastSpace = -1;
			        }
			        else if (spaceIndex == text.length())
			        {
			            lines.add(text);
			            System.out.printf("'%s' is line\n", text);
			            text = "";
			        }
			        else
			        {
			            lastSpace = spaceIndex;
			        }
			    }
	
			    contentStream.beginText();
			    contentStream.setFont(pdfFont, fontSize);
			    contentStream.newLineAtOffset(startX, startY);
			    for (String line: lines)
			    {
			        contentStream.showText(line);
			        contentStream.newLineAtOffset(0, -leading);
			    }
			    contentStream.endText(); 
			    contentStream.close();
	
			    doc.save(new File("break-long-string.pdf"));
			}
			finally
			{
			    if (doc != null)
			    {
			        doc.close();
			    }
			}
		
		}
	/**
	 * Gibt jedes Projekt mit der Beschreibung und deadline auf eine einzelne pdfSeite zurück
	 * @param pProjects Liste aller Projekte die als Pdf exportiert werden sollen
	 */
	public void exportPdfSeparate(List<Project> pProjects) {	   
		    try{			
	        String fileName = "PdfWithtext.pdf"; // name of our file
	        PDDocument doc = new PDDocument();
	        PDPage page = new PDPage();
	        doc.addPage(page);
	        PDPageContentStream content = new PDPageContentStream(doc, page);
	        for(int i=0; i<pProjects.size(); i++)
		       { 
	        	content.beginText();	        	
		        content.setFont(PDType1Font.HELVETICA, 16);
		        content.newLineAtOffset(80, 700);
		        content.setLeading(14);
		        content.showText(" Project Name " + pProjects.get(i).getName()) ;
	        	content.newLine();
	        	content.showText(" Project Desrception " + pProjects.get(i).getDescribtion()) ;
	        	content.newLine();
	        	content.showText(" Project deadline " + pProjects.get(i).getDeadline()) ;
	        	content.newLine();
	        	content.endText();	        	
	        	content.close();
	        	page = new PDPage();
	        	doc.addPage(page);
	        	content = new PDPageContentStream(doc, page);	        	
		       }	        
	        content.close();
	        doc.save(fileName);
	        doc.close();
	        System.out.println("your file created in : "+ System.getProperty("user.dir"));
	        }
	        catch(Exception e) { 
	        	e.printStackTrace();
	        	}
	        }
		    
		}

		
		

	
