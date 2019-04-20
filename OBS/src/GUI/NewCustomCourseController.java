package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.jfoenix.controls.JFXButton;

import entity.Course;
import entity.Schedule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import util.GUI;
import util.Scanner;

public class NewCustomCourseController {

    @FXML
    private Label IDcourseLBL;

    @FXML
    private TextField IDcourseTF;

    @FXML
    private JFXButton SearchBTN;

    @FXML
    public TabPane SelectPane;

    @FXML
    public Tab lectureTAB;

    @FXML
    private FlowPane lecturePane;

    @FXML
    public Tab exerciseTAB;

    @FXML
    private FlowPane exercisePane;

    @FXML
    public Tab labTAB;

    @FXML
    private FlowPane labPane;

    @FXML
    private Label CourseName;
    
	@FXML
    private ProgressIndicator PBar;
	
    
    private Scanner st ;
    
    public Course course;
    
    private Boolean byThread;
    
	@FXML 
	public void initialize() 
	{
		byThread=false;
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
    	if(Course.couseExist(Integer.parseInt(IDcourseTF.getText())))
    	{
    		course=Course.getCourse(Integer.parseInt(IDcourseTF.getText()));
    		resultSearchCouse(); 
    	}
    	else
    	{
             st = new Scanner(IDcourseTF.getText());
             PBar.setVisible(true);
             SearchBTN.setDisable(true);
     		 CourseName.setVisible(false);
     		 SelectPane.setVisible(false);
             st.start();
             byThread=true;
            
    	}
    }
    
    public void resultSearchCouse()
    {
    	if(byThread)
    	{
    			course=st.getValue();
    			byThread=false;
    	}
    	if(course==null)
    	{
    		SelectPane.setVisible(false);
    		util.GUI.infoAlert("לצערנו הקורס אינו זמין במערכת שעות,"+"\n"+"בדוק כי הקלדת נכון את קוד הקורס.", "הקורס לא קיים", "המשך");
    		CourseName.setVisible(false);
    	}
    	else
    	{
    		Course.addCourse(course);
 			Platform.runLater(()->
 			{ 
 				boolean countLec=false,countEx=false,countLab=false;
    		lecturePane.getChildren().clear();
    		exercisePane.getChildren().clear();
    		labPane.getChildren().clear();
    		CourseName.setText(course.getName());
    		for(int i=0;i<course.getSchedule().size();i++)
    		{
    			Schedule schedule=course.get(i);
    			JFXButton button=new JFXButton();
    			if(schedule.getTwoTimes())
    			{
        			button.setText("שם המרצה: "+schedule.getLecturer()+"\r\n" + 
        					"יום: "+schedule.getDay()+"\r\n" + 
        					"שעת התחלה: "+schedule.getStartTime()+"\r\n" + 
        					"שעת סיום: "+schedule.getEndTime()+"\r\n" + 
        					"כיתה: "+schedule.getClasslec()+"\r\n" +
        					"מפגש נוסף:\r\n"+
        					"יום: "+schedule.getDayTwo()+"\r\n" +
        					"שעת התחלה: "+schedule.getStartTimeTwo()+"\r\n" + 
        					"שעת סיום: "+schedule.getEndTimeTwo()+"\r\n" + 
        					"כיתה: "+schedule.getClasslecTwo());
        					button.setPrefHeight(180);
    			}
    			else
    			{
    				button.setText("שם המרצה: "+schedule.getLecturer()+"\r\n" + 
							"יום: "+schedule.getDay()+"\r\n" + 
							"שעת התחלה: "+schedule.getStartTime()+"\r\n" + 
							"שעת סיום: "+schedule.getEndTime()+"\r\n" + 
							"כיתה: "+schedule.getClasslec());
    						button.setPrefHeight(90);
    			}					
    			button.setPrefWidth(250);
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
    		SearchBTN.setDisable(false);
    		PBar.setVisible(false);
    		CourseName.setVisible(true);
    		SelectPane.setVisible(true);
 			});
    	}
    }



	private void addToGrid(Object userData) 
	{
			Schedule schedule=(Schedule)userData;
			replaceCourse(schedule);
			ArrayList<Schedule> list = Course.getSchduledCourses();
			for(int i=0;i<list.size();i++)
			{
				if((schedule.getStartTime().getIndex()>=list.get(i).getStartTime().getIndex() &&schedule.getEndTime().getIndex()<=list.get(i).getEndTime().getIndex() &&schedule.getDay().getIndex()==list.get(i).getDay().getIndex())||
						(schedule.getStartTime().getIndex()<list.get(i).getStartTime().getIndex() &&schedule.getEndTime().getIndex()>list.get(i).getStartTime().getIndex() &&schedule.getDay().getIndex()==list.get(i).getDay().getIndex())||
				(schedule.getStartTime().getIndex()>list.get(i).getStartTime().getIndex() &&schedule.getStartTime().getIndex()<list.get(i).getEndTime().getIndex() &&schedule.getDay().getIndex()==list.get(i).getDay().getIndex()))
				{
					GUI.infoAlert("קיימת התנגשות במערכת", "התנגשות", "חזור");
					return ;
				}
				if(list.get(i).getTwoTimes())
				{
					if((schedule.getStartTime().getIndex()>=list.get(i).getStartTimeTwo().getIndex() &&schedule.getEndTime().getIndex()<=list.get(i).getEndTimeTwo().getIndex() &&schedule.getDay().getIndex()==list.get(i).getDayTwo().getIndex())||
							(schedule.getStartTime().getIndex()<list.get(i).getStartTimeTwo().getIndex() &&schedule.getEndTime().getIndex()>list.get(i).getStartTimeTwo().getIndex() &&schedule.getDay().getIndex()==list.get(i).getDayTwo().getIndex())||
					(schedule.getStartTime().getIndex()>list.get(i).getStartTimeTwo().getIndex() &&schedule.getStartTime().getIndex()<list.get(i).getEndTimeTwo().getIndex() &&schedule.getDay().getIndex()==list.get(i).getDayTwo().getIndex()))
					{
						GUI.infoAlert("קיימת התנגשות במערכת", "התנגשות", "חזור");
						return ;
					}
				}
				if(schedule.getTwoTimes())
				{
					if((schedule.getStartTimeTwo().getIndex()>=list.get(i).getStartTime().getIndex() &&schedule.getEndTimeTwo().getIndex()<=list.get(i).getEndTime().getIndex() &&schedule.getDayTwo().getIndex()==list.get(i).getDay().getIndex())||
							(schedule.getStartTimeTwo().getIndex()<list.get(i).getStartTime().getIndex() &&schedule.getEndTimeTwo().getIndex()>list.get(i).getStartTime().getIndex() &&schedule.getDayTwo().getIndex()==list.get(i).getDay().getIndex())||
					(schedule.getStartTimeTwo().getIndex()>list.get(i).getStartTime().getIndex() &&schedule.getStartTimeTwo().getIndex()<list.get(i).getEndTime().getIndex() &&schedule.getDayTwo().getIndex()==list.get(i).getDay().getIndex()))
					{
						GUI.infoAlert("קיימת התנגשות במערכת", "התנגשות", "חזור");
						return ;
					}
					if(list.get(i).getTwoTimes())
					{
						if((schedule.getStartTimeTwo().getIndex()>=list.get(i).getStartTimeTwo().getIndex() &&schedule.getEndTimeTwo().getIndex()<=list.get(i).getEndTimeTwo().getIndex() &&schedule.getDayTwo().getIndex()==list.get(i).getDayTwo().getIndex())||
								(schedule.getStartTimeTwo().getIndex()<list.get(i).getStartTimeTwo().getIndex() &&schedule.getEndTimeTwo().getIndex()>list.get(i).getStartTimeTwo().getIndex() &&schedule.getDayTwo().getIndex()==list.get(i).getDayTwo().getIndex())||
						(schedule.getStartTimeTwo().getIndex()>list.get(i).getStartTimeTwo().getIndex() &&schedule.getStartTimeTwo().getIndex()<list.get(i).getEndTimeTwo().getIndex() &&schedule.getDayTwo().getIndex()==list.get(i).getDayTwo().getIndex()))
						{
							GUI.infoAlert("קיימת התנגשות במערכת", "התנגשות", "חזור");
							return ;
						}
					}
				}
			}
		Platform.runLater(()->
		{ 
			if (schedule.getSplited()) 
			{ //If there is a break
				Main.scheduleController.getScheduleGrid().add(schedule.getGridPaneVBox1(), schedule.getDay().getIndex(), schedule.getStartTime().getIndex(), 1, 5 - schedule.getStartTime().getIndex());
				if (schedule.getDay().getIndex() == 4)
				{//if tuesday 
					Main.scheduleController.getScheduleGrid().add(schedule.getGridPaneVBox2(), schedule.getDay().getIndex(), 8, 1, schedule.getEndTime().getIndex() - 8);
				}
				else 
				{
					Main.scheduleController.getScheduleGrid().add(schedule.getGridPaneVBox2(), schedule.getDay().getIndex(), 6, 1, schedule.getEndTime().getIndex() - 6);
				}
			}
			else 
				Main.scheduleController.getScheduleGrid().add(schedule.getGridPaneVBox1(), schedule.getDay().getIndex(), schedule.getStartTime().getIndex(), 1, schedule.getEndTime().getIndex() - schedule.getStartTime().getIndex());
			if(schedule.getTwoTimes())
			{
				if (schedule.getSplitedTwo()) 
				{ //If there is a break
					Main.scheduleController.getScheduleGrid().add(schedule.getGridPaneVBox3(), schedule.getDayTwo().getIndex(), schedule.getStartTimeTwo().getIndex(), 1, 5 - schedule.getStartTimeTwo().getIndex());
					if (schedule.getDay().getIndex() == 4)
					{//if tuesday 
						Main.scheduleController.getScheduleGrid().add(schedule.getGridPaneVBox4(), schedule.getDayTwo().getIndex(), 8, 1, schedule.getEndTimeTwo().getIndex() - 8);
					}
					else 
					{
						Main.scheduleController.getScheduleGrid().add(schedule.getGridPaneVBox4(), schedule.getDayTwo().getIndex(), 6, 1, schedule.getEndTimeTwo().getIndex() - 6);
					}
				}
				else 
					Main.scheduleController.getScheduleGrid().add(schedule.getGridPaneVBox3(), schedule.getDayTwo().getIndex(), schedule.getStartTimeTwo().getIndex(), 1, schedule.getEndTimeTwo().getIndex() - schedule.getStartTimeTwo().getIndex());
			}
			
			schedule.setSelected(true);
			
		});
		
	}

	
	
	private void replaceCourse(Schedule schedule)
	{
		ArrayList<Schedule> list = Course.getSchduledCourses();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getCourse().getID()==schedule.getCourse().getID())
			{
				if(list.get(i).getType().equals(schedule.getType()))
				{
					Main.scheduleController.getScheduleGrid().getChildren().remove(list.get(i).getGridPaneVBox1());
					if(list.get(i).getSplited())
					{
						Main.scheduleController.getScheduleGrid().getChildren().remove(list.get(i).getGridPaneVBox2());
					}
					if(list.get(i).getTwoTimes())
					{
						Main.scheduleController.getScheduleGrid().getChildren().remove(list.get(i).getGridPaneVBox3());
						if(list.get(i).getSplitedTwo())
						{
							Main.scheduleController.getScheduleGrid().getChildren().remove(list.get(i).getGridPaneVBox4());
						}
					}
					list.get(i).setSelected(false);
					return;
				}
			}
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
	


}
