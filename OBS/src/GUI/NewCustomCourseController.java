package GUI;

import com.jfoenix.controls.JFXButton;

import entity.Course;
import entity.Schedule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import util.Scanner;

public class NewCustomCourseController {

    @FXML
    private Label IDcourseLBL;

    @FXML
    private TextField IDcourseTF;

    @FXML
    private JFXButton SearchBTN;

    @FXML
    private TabPane SelectPane;

    @FXML
    private Tab lectureTAB;

    @FXML
    private FlowPane lecturePane;

    @FXML
    private Tab exerciseTAB;

    @FXML
    private FlowPane exercisePane;

    @FXML
    private Tab labTAB;

    @FXML
    private FlowPane labPane;

    @FXML
    private Label CourseName;

    
    
	@FXML 
	public void initialize() 
	{
		lecturePane.setVgap(8);
		lecturePane.setHgap(4);
		lecturePane.setPrefWrapLength(300); // preferred width = 300
		exercisePane.setVgap(8);
		exercisePane.setHgap(4);
		exercisePane.setPrefWrapLength(300); // preferred width = 300
		labPane.setVgap(8);
		labPane.setHgap(4);
		labPane.setPrefWrapLength(300); // preferred width = 300
		SelectPane.setVisible(false);
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
    void searchCourse(ActionEvent event) 
    {
    	boolean countLec=false,countEx=false,countLab=false;
    	Course course=Scanner.getSchedule(IDcourseTF.getText());
    	if(course==null)
    	{
    		SelectPane.setVisible(false);
    		util.GUI.infoAlert("לצערנו הקורס אינו זמין במערכת שעות,"+"\n"+"בדוק כי הקלדת נכון את קוד הקורס.", "הקורס לא קיים", "המשך");
    		CourseName.setVisible(false);
    	}
    	else
    	{
    		lecturePane.getChildren().clear();
    		exercisePane.getChildren().clear();
    		labPane.getChildren().clear();
    		CourseName.setText(course.getName());
    		for(int i=0;i<course.getSchedule().size();i++)
    		{
    			Schedule schedule=course.get(i);
    			JFXButton button=new JFXButton();
    			button.setText("שם המרצה: "+schedule.getLecturer()+"\r\n" + 
    					"יום: "+schedule.getDay()+"\r\n" + 
    					"שעת התחלה: "+schedule.getStartTime()+"\r\n" + 
    					"שעת סיום: "+schedule.getEndTime()+"\r\n" + 
    					"כיתה: "+schedule.getClasslec());
    			button.setPrefWidth(250);
    			button.setPrefHeight(90);
    			button.setAlignment(Pos.CENTER_LEFT);
    			button.setStyle("-fx-font-size:12px;-fx-background-color:#66cdaa;-fx-text-fill:#fff8f8;");
    			button.setUserData(schedule);
    			button.setOnAction(e -> addToGrid(button.getUserData()));
    			if(schedule.getType().equals("הרצאה"))
    			{
    				countLec=true;
    				lecturePane.getChildren().add(button);
    			}
    			else if(schedule.getType().equals("תרגיל"))
    			{
    				countEx=true;
    				exercisePane.getChildren().add(button);
    			}
    			else
    			{
    				countLab=true;
    				labPane.getChildren().add(button);
    			} 				
    		}
    		if(countLec==true)
    		{
    			lectureTAB.setDisable(false);
    		}
    		else
    		{
    			lectureTAB.setDisable(true);
    		}
    		if(countEx==true)
    		{
    			exerciseTAB.setDisable(false);
    		}
    		else
    		{
    			exerciseTAB.setDisable(true);
    		}
    		if(countLab==true)
    		{
    			labTAB.setDisable(false);
    		}
    		else
    		{
    			labTAB.setDisable(true);
    		}
    		CourseName.setVisible(true);
    		SelectPane.setVisible(true);
    	}
    }


	private void addToGrid(Object userData) 
	{
		Platform.runLater(()->
		{ 
			Schedule schedule=(Schedule)userData;
			if (schedule.getSplited() == true) { //If there is a break
				ScheduleController.ScheduleGrid.add(schedule.getGridPaneVBox1(), schedule.getDay().getIndex(), schedule.getStartTime().getIndex(), 1, 5 - schedule.getStartTime().getIndex());
				if (schedule.getDay().getIndex() == 4)  //if tuesday 
					ScheduleController.ScheduleGrid.add(schedule.getGridPaneVBox2(), schedule.getDay().getIndex(), 8, 1, schedule.getEndTime().getIndex() - 8);
				else 
					ScheduleController.ScheduleGrid.add(schedule.getGridPaneVBox2(), schedule.getDay().getIndex(), 6, 1, schedule.getEndTime().getIndex() - 6);
			}
			else 
				ScheduleController.ScheduleGrid.add(schedule.getGridPaneVBox1(), schedule.getDay().getIndex(), schedule.getStartTime().getIndex(), 1, schedule.getEndTime().getIndex());
		});
	}
	
	@FXML
    void makeSearchWithEnterBtn(KeyEvent event)
    {
    	if(event.getCode().equals(KeyCode.ENTER))
    	{
    		searchCourse(new ActionEvent());
       }
    }

}
