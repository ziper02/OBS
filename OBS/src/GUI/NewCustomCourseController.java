package GUI;

import com.jfoenix.controls.JFXButton;

import entity.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import util.Scanner;

public class NewCustomCourseController {

    @FXML
    private Label IDcourseLBL;

    @FXML
    private TextField IDcourseTF;

    @FXML
    private JFXButton SearchBTN;

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
    void searchCourse(ActionEvent event) 
    {
    	Course course=Scanner.getSchedule(IDcourseTF.getText());
    	if(course==null)
    	{
    		util.GUI.infoAlert("לצערנו הקורס אינו זמין במערכת שעות,"+"\n"+"בדוק כי הקלדת נכון את קוד הקורס.", "הקורס לא קיים", "המשך");
    	}
    }

}
