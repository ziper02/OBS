package GUI;

import java.io.IOException;
import java.net.URL;

import com.jfoenix.controls.JFXButton;

import entity.Course;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ScheduleController 
{

	@FXML
	private Pane RightPane;

	@FXML
	public JFXButton VaildSchedule;

	@FXML
	public JFXButton CreateCustomSchedule;

	@FXML
	public JFXButton SaveSchedule;

	@FXML
	public JFXButton CreateAutomaticSchedule;

	@FXML
	public JFXButton LoadSchedule;

	@FXML
	private Pane MiddlePane;

	@FXML
	private Pane LeftPane;

	@FXML
	private GridPane ScheduleGrid;


	public static int selection;
	
	public static NewCustomCourseController controller=null;
	public static AutoCourseController controllerAuto=null;
	
    @FXML
    void LoadNewCusomCourseController(ActionEvent event) 
    {
    	Pane newLoadedPane;
		try 
		{
			if(selection!=1)
			{
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/NewCustomCourse.fxml"));
				newLoadedPane = loader.load();
				if(controllerAuto!=null)
					MiddlePane.getChildren().remove(0);
				MiddlePane.getChildren().add(newLoadedPane);
				controller=loader.getController();
				selection=1;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void LoadAutoSchedulePane(ActionEvent event) 
    {
    	Pane newLoadedPane;
		try 
		{
			if(selection!=2)
			{
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/AutoCourse.fxml"));
				newLoadedPane = loader.load();
				if(controller!=null)
					MiddlePane.getChildren().remove(0);
				MiddlePane.getChildren().add(newLoadedPane);
				controllerAuto=loader.getController();
				selection=2;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    }
    
    public GridPane getScheduleGrid() 
    {
		return ScheduleGrid;
	}

	public void setScheduleGrid(GridPane scheduleGrid) 
	{
		ScheduleGrid = scheduleGrid;
	}

	@FXML
	void LoadSchedulePressed(ActionEvent event) 
	{

	}

	@FXML
	void SaveSchedulePressed(ActionEvent event) 
	{

	}

	@FXML
	void VaildSchedulePressed(ActionEvent event) 
	{
		Course.VaildSchedule();
	}

	
}
