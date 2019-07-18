package GUI;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import entity.Course;
import entity.Department;
import entity.Schedule;
import geneticAlgorithm.GA;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import util.Scanner;

public class AutoCourseController {



    @FXML
    private   TextField IDcourseTF;

    @FXML
    private  JFXButton SearchBTN;

    @FXML
    private  Label CourseName;

    @FXML
    private ProgressIndicator PBar;

    @FXML
    private  ProgressIndicator PBar2;
    
    @FXML
    private  ListView<Course> SelectedCourses;

    @FXML
    private  JFXButton StartAuto;
    
    private Scanner st;
    
    public Course course;
    
    @FXML
    public FlowPane FPResult;

    @FXML
    private  JFXButton Back;
    
    
    @FXML
    private  Label ResultLabel;
    
    @FXML
    private  ScrollPane SPResult;
    
    public int NumberOfGA=5;
    public int SecoundNumberOfGA=10;
    
    private ArrayList<GA> ga=new ArrayList<>();


	private AutoCompletionBinding<Course> autoCompletionBinding;


	public void initialize()
	{
		FPResult.setVgap(8);
		FPResult.setHgap(4);
		FPResult.setPrefWrapLength(300); // preferred width = 300
		FPResult.setAlignment(Pos.TOP_RIGHT);
		SelectedCourses.setOnMouseClicked(arg0 -> Platform.runLater(()->
		{
			int index = SelectedCourses.getSelectionModel().getSelectedIndex();
			Course course=SelectedCourses.getSelectionModel().getSelectedItem();
			if (index >= 0)
			{
				SelectedCourses.getItems().remove(index);
				GA.removeCourse(course);
			}
		}));
		autoCompletionBinding = TextFields.bindAutoCompletion(IDcourseTF, Department.Courselist.values());
		autoCompletionBinding.setOnAutoCompleted(event -> IDcourseTF.setText(event.getCompletion().getID()));
	}
    
    
	
    @FXML
    void ValidTextSearchChanged(InputMethodEvent event) 
    {
		/* util.GUI.IDcourseTFChanged(IDcourseTF); */
    }

    
    @FXML
    public void GoBack(ActionEvent event)
    {
    	IDcourseTF.setVisible(true);
    	SearchBTN.setVisible(true);
    	SelectedCourses.setVisible(true);
    	StartAuto.setVisible(true);
    	FPResult.setVisible(false);
    	Back.setVisible(false);
    	SPResult.setVisible(false);
    	ResultLabel.setVisible(false);
    	PBar2.setVisible(false);    	
    }
    
    
    public void GoBack()
    {
    	IDcourseTF.setVisible(true);
    	SearchBTN.setVisible(true);
    	SelectedCourses.setVisible(true);
    	StartAuto.setVisible(true);
    	FPResult.setVisible(false);
    	Back.setVisible(false);
    	SPResult.setVisible(false);
    	ResultLabel.setVisible(false);
    	PBar2.setVisible(false);
    }
    
    @FXML
    void StartAlgoritam()
    {
		if(GA.listSize()==0)
		{
			util.GUI.alertErrorWithOption("הרשימה ריקה אנא בחר קורסים","הרשימה ריקה","אישור");
			return;
		}
		GA.countValues=0;
		GA.choosenValue=0;
		if(!GA.isEmpty())
			GA.Finalsc.clear();
		if(!ga.isEmpty())
			ga.clear();
    	PBar2.setVisible(true);
    	SelectedCourses.setVisible(false);
    	StartAuto.setVisible(false);
    	IDcourseTF.setVisible(false);
    	SearchBTN.setVisible(false);
    	
    	for(int i=0;i<NumberOfGA;i++)
    	{
    		ga.add(new GA(100,0.9-(0.100000000*i),200000,1));
    		ga.get(i).selection();
    		ga.get(i).start();
    	}
    }

    private void addToGrid(Object userData)
    {
    	@SuppressWarnings("unchecked")
		ArrayList<Schedule> schedule=(ArrayList<Schedule>)userData;
    	ScheduleController.removeAllSchedule();
		for (Schedule value : schedule)
			NewCustomCourseController.addToGrid(value);
    }
    
    
    
    public void resultOfGAFirst()
    {
    	int maxi=0;
    	GA.choosenValue=0;
    	for(int i=0;i<ga.size();i++)
    	{
    		if(!ga.get(i).getSc().isEmpty() && ga.get(i).getValue()>GA.choosenValue)
    		{
    			GA.choosenValue=ga.get(i).getValue();
    			maxi=i;
    		}
    	}
		GA.countValues=0;
		GA.choosenValue=0;
		for(int i=0;i<SecoundNumberOfGA;i++)
		{
			GA ga1=new GA(100,ga.get(maxi).getValue(),75000,2);
			ga1.start();
		}
    }
    
    public void resultOfGASecound()
    {
    	Platform.runLater(()->
			{ 
				FPResult.getChildren().clear();
				for(int i=0;i<GA.Finalsc.size();i++)
	    		{
					JFXButton button=new JFXButton();
					button.setText("אפשרות מספר "+(i+1));
					button.setPrefHeight(40);
					button.setPrefWidth(114);
	    			button.setAlignment(Pos.CENTER_LEFT);
	    			button.setStyle("-fx-font-size:12px;-fx-background-color:#66cdaa;-fx-text-fill:#fff8f8;");
	    			button.setUserData(GA.Finalsc.get(i));
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

    @FXML
    void keyTypedCourseTF(KeyEvent event) 
    {
    	/*for(int i=0;i<IDcourseTF.getText().length();i++)
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
		}*/
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
