package GUI;

import java.util.ArrayList;
import java.util.Random;

import com.jfoenix.controls.JFXButton;

import entity.Course;
import entity.Schedule;
import geneticAlgorithm.GA;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import util.Scanner;

public class AutoCourseController {

    @FXML
    private Label IDcourseLBL;

    @FXML
    private TextField IDcourseTF;

    @FXML
    private JFXButton SearchBTN;

    @FXML
    private Label CourseName;

    @FXML
    private ProgressIndicator PBar;

    @FXML
    private ProgressIndicator PBar2;
    
    @FXML
    private ListView<Course> SelectedCourses;

    @FXML
    private JFXButton StartAuto;
    
    private Scanner st;
    
    public Course course;
    
    @FXML
    private FlowPane FPResult;

    @FXML
    private JFXButton Back;
    
    private ArrayList<ArrayList<Schedule>> sc;
    private double value;
    
    @FXML
    private Label ResultLabel;
    
    @FXML
    private ScrollPane SPResult;
    

    
    
	public void initialize() 
	{
		FPResult.setVgap(8);
		FPResult.setHgap(4);
		FPResult.setPrefWrapLength(300); // preferred width = 300
		FPResult.setAlignment(Pos.TOP_RIGHT);
		
	}
    
    

    
    @FXML
    void GoBack(ActionEvent event)
    {
    	IDcourseTF.setVisible(true);
    	SearchBTN.setVisible(true);
    	IDcourseLBL.setVisible(true);
    	SelectedCourses.setVisible(true);
    	StartAuto.setVisible(true);
    	FPResult.setVisible(false);
    	Back.setVisible(false);
    	SPResult.setVisible(false);
    	ResultLabel.setVisible(false);
    	PBar2.setVisible(false);
    	
    }
    @FXML
    void StartAlgoritam(ActionEvent event) 
    {
    	if(sc!=null)
    		if(sc.isEmpty()==false)
    			sc.clear();
    	PBar2.setVisible(true);
    	SelectedCourses.setVisible(false);
    	StartAuto.setVisible(false);
    	IDcourseTF.setVisible(false);
    	SearchBTN.setVisible(false);
    	IDcourseLBL.setVisible(false);
    	GA ga=new GA(100);
    	ga.selection();
    	calculateByValue(0.9,ga,200000);
    	if(sc.isEmpty())
    	{
    		calculateByValue(0.8,ga,200000);
    		if(sc.isEmpty())
    		{
    			calculateByValue(0.7,ga,200000);
        		if(sc.isEmpty())
        		{
        			calculateByValue(0.6,ga,200000);
            		if(sc.isEmpty())
            		{
            			calculateByValue(0.5,ga,200000);
            		}
            		if(sc.isEmpty())
            		{
            			util.GUI.alertErrorWithOption("לא נמצאה מערכת מתאימה\n נסה קורסים אחרים או הרכבת ידנית.", "לא נמצא", "אישור");
            			GoBack(new ActionEvent());
            		}
        		}
    		}
    	}
    	if(sc.isEmpty()==false)
    	{
    		for(int i=0;i<10;i++)
    		{
    			if(value==0.9 || value==0.8 || value==0.7)
    			{
    				ga=new GA(100);
    				calculateByValue(value,ga,100000);
    			}
    			else
    			{
    				ga=new GA(100);
    				calculateByValue(value,ga,75000);
    			}
    		}
    		Platform.runLater(()->
 			{ 
 				FPResult.getChildren().clear();
 				for(int i=0;i<sc.size();i++)
 	    		{
 					JFXButton button=new JFXButton();
 					button.setText("אפשרות מספר "+(i+1));
 					button.setPrefHeight(40);
 					button.setPrefWidth(114);
 	    			button.setAlignment(Pos.CENTER_LEFT);
 	    			button.setStyle("-fx-font-size:12px;-fx-background-color:#66cdaa;-fx-text-fill:#fff8f8;");
 	    			button.setUserData(sc.get(i));
 	    			button.setOnAction(e -> addToGrid(button.getUserData()));	    			
 	    			FPResult.getChildren().add(button);
 	    		}
 			});
    		FPResult.setVisible(true);
    		SPResult.setVisible(true);
        	Back.setVisible(true);
        	ResultLabel.setVisible(true);
        	PBar2.setVisible(false);
    	}
    }
    
    public void addToGrid(Object userData)
    {
    	@SuppressWarnings("unchecked")
		ArrayList<Schedule> schedule=(ArrayList<Schedule>)userData;
    	ScheduleController.removeAllSchedule();
    	for(int i=0;i<schedule.size();i++)
        	NewCustomCourseController.addToGrid(schedule.get(i));
    }
    
    
    
    public void calculateByValue(double num, GA ga,int generations)
    {
    	Random rn = new Random();
    	if(sc==null)
    		sc=new ArrayList<ArrayList<Schedule>>();
    	System.out.println("Generation: " + ga.generationCount + " Fittest: " + (Math.floor(ga.fittest.getFitness() * 100) / 100)+" Conflicts: "+ga.fittest.getConflicts());
    	double max=(Math.floor(ga.population.getFittest().getFitness() * 100) / 100);
    	int CountGeneration=ga.generationCount;
    	while( ga.fittest.getFitness()<num && ga.generationCount<(generations+CountGeneration) ) //
    	{
    		  ++ga.generationCount;
              ga.crossover();
              if (rn.nextInt()%7 < 5) 
                  ga.mutation();
              ga.addFittestOffspring();
              if(max<(Math.floor(ga.population.getFittest().getFitness() * 100) / 100))
              {
            	  max=Math.floor(ga.population.getFittest().getFitness() * 100) / 100;
            	  System.out.println("Generation: " + ga.generationCount + " Fittest: " + (Math.floor(ga.fittest.getFitness() * 100) / 100)+" Conflicts: "+ga.fittest.getConflicts());
              }
              if(ga.generationCount%100000==0)
              {
            	  System.out.println(ga.generationCount);
              }
    	}
    	if(ga.fittest.getFitness()>=num)
    	{
    		
    		for(int i=0;i<ga.population.getChromosomes().size();i++)
    		{
    			if(ga.population.getChromosomes().get(i).getFitness()>=num)
    			{
    				if(Schedule.contaninsSameScheduleListSameOrder(sc,ga.population.getChromosomes().get(i).getGenes())==false)
    						sc.add(ga.population.getChromosomes().get(i).getGenes());
    			}
    		}
    		value=num;
    	}
    }
    


    @FXML
    void keyTypedCourseTF(KeyEvent event) 
    {
    	for(int i=0;i<IDcourseTF.getText().length();i++) 
		{
	    	if(IDcourseTF.getText().charAt(i)<'0' ||IDcourseTF.getText().charAt(i)>'9')
	    	{
	    		StringBuilder sb = new StringBuilder(IDcourseTF.getText());
	    		sb.deleteCharAt(i);
	    		IDcourseTF.setText(sb.toString());
	    	}
		}
		if(IDcourseTF.getText().length()>6)
		{
			IDcourseTF.setText(IDcourseTF.getText().substring(0, IDcourseTF.getText().length() - 1));
		}
    }

    @FXML
    void makeSearchWithEnterBtn(KeyEvent event) 
    {
    	if(event.getCode().equals(KeyCode.ENTER))
    	{
    		searchCourse(new ActionEvent());
       }
    }

    @FXML
    void searchCourse(ActionEvent event) 
    {
    	if(GA.listSize()<7)
    	{
    		if(IDcourseTF.getText().length()==5)
    		{
	        	if(Course.couseExist(Integer.parseInt(IDcourseTF.getText())))
	        	{
	        		course=Course.getCourse(Integer.parseInt(IDcourseTF.getText()));
	        		SelectedCourses.getItems().add(course);
	        		GA.addCourse(course);
	        	}
	        	else
	        	{
			    	 st = new Scanner(IDcourseTF.getText());
			    	 st.start();
			         PBar.setVisible(true);
			         SearchBTN.setDisable(true);
			         StartAuto.setDisable(true);
			         IDcourseTF.setDisable(true);
	        	}
    		}
    		else
    			util.GUI.infoAlert("לצערנו הקורס אינו זמין במערכת שעות,"+"\n"+"בדוק כי הקלדת נכון את קוד הקורס.", "הקורס לא קיים", "המשך");
    	}
    	else
    		util.GUI.infoAlert("לצערנו בחרת יותר מדי קורסים,"+"\n"+"מחק קורס או חפש מערכת מומלצת.", "יותר מדי קורסים", "המשך");
    }
    
    
   public void resultSearchCouse()
   {
	   course=st.getValue();
	   if(course==null)
	   {
   			util.GUI.infoAlert("לצערנו הקורס אינו זמין במערכת שעות,"+"\n"+"בדוק כי הקלדת נכון את קוד הקורס.", "הקורס לא קיים", "המשך");
   			PBar.setVisible(false);
   			SearchBTN.setDisable(false);
   			StartAuto.setDisable(false);
   			IDcourseTF.setDisable(false);
   			return;
	   }
	   GA.addCourse(course);
	   Course.addCourse(course);
	   Platform.runLater(()->
	   {
		   SelectedCourses.getItems().add(course);
		   PBar.setVisible(false);
	       SearchBTN.setDisable(false);
	       StartAuto.setDisable(false);
	       IDcourseTF.setDisable(false);
	   });
   }

}
