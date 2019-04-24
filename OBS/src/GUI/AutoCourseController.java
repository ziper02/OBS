package GUI;

import java.util.ArrayList;
import java.util.Random;

import com.jfoenix.controls.JFXButton;

import entity.Course;
import entity.Schedule;
import geneticAlgorithm.GA;
import geneticAlgorithm.Population;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private ListView<Course> SelectedCourses;

    @FXML
    private JFXButton StartAuto;
    
    private Scanner st;
    
    public Course course;
    Scanner st4;
    Scanner st1;
    Scanner st2;
    Scanner st3;
    Scanner st5;
    Scanner st6;
    @FXML
    void StartAlgoritam(ActionEvent event) 
    {
    	ScheduleController.selection=4;
    	st4=new Scanner(Integer.toString(61761));
    	st4.start();
    	st1=new Scanner(Integer.toString(11158));
    	st1.start();
    	st2=new Scanner(Integer.toString(61764));
    	st2.start();
    	st3=new Scanner(Integer.toString(61763));
    	st3.start();
    	st5=new Scanner(Integer.toString(11069));
    	st5.start();
    	st6=new Scanner(Integer.toString(61758));
    	st6.start();
    	System.out.println("here");
    	
    }
    
    public void StartAlgoritamTEST() 
    {
    	GA.addCourse(st4.getValue());
    	GA.addCourse(st5.getValue());
    	GA.addCourse(st6.getValue());
    	GA.addCourse(st2.getValue());
    	GA.addCourse(st3.getValue());
    	GA.addCourse(st1.getValue());
    	ScheduleController.removeAllSchedule();   	
    	GA ga=new GA(100);
    	Random rn = new Random();
    	System.out.println("Generation: " + ga.generationCount + " Fittest: " + (Math.floor(ga.fittest.getFitness() * 100) / 100)+" Conflicts: "+ga.fittest.getConflicts());
    	double max=(Math.floor(ga.population.getFittest().getFitness() * 100) / 100);
    	ga.selection();
    	while(ga.fittest.getFitness()<0.8 && ga.generationCount<300000 )
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
    	
    	ArrayList<Schedule> sc;
    	if(ga.fittest.getFitness()>=0.8)
    	{
    		sc=ga.fittest.getGenes();
        	for(int i=0;i<sc.size();i++)
        		NewCustomCourseController.addToGrid(sc.get(i));
        	return;
    	}
    	ga.generationCount=0;
    	while(ga.fittest.getFitness()<0.7 && ga.generationCount<300000 )
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
            	  System.out.println(ga.generationCount);
    	}
    	if(ga.fittest.getFitness()>=0.7)
    	{
    		sc=ga.fittest.getGenes();
        	for(int i=0;i<sc.size();i++)
        		NewCustomCourseController.addToGrid(sc.get(i));
        	return;
    	}
    	ga.generationCount=0;
    	while(ga.fittest.getFitness()<0.6 && ga.generationCount<200000 )
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
            	  System.out.println(ga.generationCount);
    	}
    	if(ga.fittest.getFitness()>=0.6)
    	{
    		sc=ga.fittest.getGenes();
        	for(int i=0;i<sc.size();i++)
        		NewCustomCourseController.addToGrid(sc.get(i));
        	return;
    	}
    	ga.generationCount=0;
    	while(ga.fittest.getFitness()<0.5 && ga.generationCount<100000 )
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
            	  System.out.println(ga.generationCount);
    	}
    	if(ga.fittest.getFitness()>=0.5)
    	{
    		sc=ga.fittest.getGenes();
        	for(int i=0;i<sc.size();i++)
        		NewCustomCourseController.addToGrid(sc.get(i));
        	return;
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
