package GUI;

import com.jfoenix.controls.JFXButton;

import entity.AutoAlgoritam;
import entity.Course;
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

    @FXML
    void StartAlgoritam(ActionEvent event) 
    {

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
    	if(AutoAlgoritam.listSize()<7)
    	{
    		if(IDcourseTF.getText().length()==5)
    		{
	        	if(Course.couseExist(Integer.parseInt(IDcourseTF.getText())))
	        	{
	        		course=Course.getCourse(Integer.parseInt(IDcourseTF.getText()));
	        		SelectedCourses.getItems().add(course);
	        		AutoAlgoritam.addCourse(course);
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
	   AutoAlgoritam.addCourse(course);
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
