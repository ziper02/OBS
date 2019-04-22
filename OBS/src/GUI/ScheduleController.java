package GUI;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;

import entity.Course;
import entity.Schedule;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.Scanner;

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
	public  ProgressIndicator PBar;
	
	@FXML
	private Pane MiddlePane;

	@FXML
	private Pane LeftPane;

	@FXML
	private GridPane ScheduleGrid;

	public static ArrayList<Scanner> st;
	static ArrayList<Schedule> schedule;

    @FXML
    public JFXButton SaveSchedulePNG;

	public static int selection;
	private static int lastSelection;
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
		util.GUI.infoAlert("טעינת הקובץ עלולה לקחת כמה שניות"+"\n עקב בדיקה עם השעות עדיין רלוונטיות.", "אישור הבקשה", "אישור");
		FileChooser fc=new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("TMP Files","*.tmp"));
		fc.setInitialDirectory(new File("c:\\"));
		File f=fc.showOpenDialog(null);
		if(f!=null)
		{
			String str=f.getName();
			String extension = str.substring(str.lastIndexOf(".") + 1);
			if(extension.equals("tmp"))
			{
				try 
				{
					FileInputStream fis = new FileInputStream(f);
					ObjectInputStream ois = new ObjectInputStream(fis);
					List<Schedule> list2 = (List<Schedule>) ois.readObject();
					ArrayList<Schedule> list=new ArrayList<Schedule>(list2);
					ois.close();
					lastSelection=selection;
					selection=3;
					checkForUpdate(list);
				}
				catch (Exception e) 
				{
						util.GUI.alertErrorWithOption("התרחשה שגיאה במהלך פתיחת הקובץ", "שגיאה", "אישור");
						e.printStackTrace();
				}
			}
		}
		else
		{
			util.GUI.alertErrorWithOption("התרחשה שגיאה במהלך פתיחת הקובץ", "שגיאה", "אישור");
		}
	}

	@FXML
	void SaveSchedulePressed(ActionEvent event) 
	{
		FileChooser fc=new FileChooser();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TMP File","*.tmp"));
		fc.setTitle("Save My Schedule");
		fc.setInitialFileName("MySchedule.tmp");
		File file =fc.showSaveDialog(Main.primaryStage);
		if(file!=null)
		{
			String str = file.getAbsolutePath();
			try 
			{
				FileOutputStream fos = new FileOutputStream(str);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(Course.getSchduledCourses());
				oos.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	@FXML
	void VaildSchedulePressed(ActionEvent event) 
	{
		Course.VaildSchedule();
	}

    @FXML
    void SaveSchedulePNGPressed(ActionEvent event) 
    {
    	  WritableImage image = ScheduleGrid.snapshot(new SnapshotParameters(), null);
    	    //File file2 = new File("MySchedule.png");
    	    
    	    try 
    	    {
    	        //ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file2);
    	        FileChooser fc=new FileChooser();
    			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG File","*.png"));
    			fc.setTitle("Save My Schedule");
    			fc.setInitialFileName("MySchedule.png");
    			File file =fc.showSaveDialog(Main.primaryStage);
    			if(file!=null)
    			{
    				String str = file.getAbsolutePath();
    				try 
    				{
    					RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);
    					// Write the snapshot to the chosen file
    					ImageIO.write(renderedImage, "png", file);
    				} 
    				catch (Exception e) 
    				{
    					e.printStackTrace();
    				}
    			}
    		}
    	    catch (Exception e) 
    	    {
    	        // TODO: handle exception here
    	    }
    }

	void checkForUpdate(ArrayList<Schedule> list)
	{
		st=new ArrayList<Scanner>();
		schedule=list;
		ArrayList<String> req=new ArrayList<String>();
		LoadSchedule.setVisible(false);
		PBar.setVisible(true);
		for(int i=0;i<list.size();i++)
		{
			String courseID=list.get(i).getCourse().getID();
			if(Course.couseExist(Integer.parseInt(courseID))==false)
			{
				Scanner temp=new Scanner(courseID);
				if(st.contains(temp)==false)
					st.add(temp);
			}
		}
		for(int i=0;i<st.size();i++)
		{
			st.get(i).start();
		}
		
	}

	
	public static void checkForUpdateResult()
	{
		removeAllSchedule();
		String errorlist="";
		for(int i=0;i<st.size();i++)
		{
			Course course=st.get(i).getValue();
			Course.addCourse(course);
		}
		for(int i=0;i<schedule.size();i++)
		{
			Schedule sc=schedule.get(i);
			sc.checkSetUpAllparamters();
			if(Schedule.checkIfAvailable(schedule.get(i))==false)
			{
				errorlist=errorlist+"\n"+sc.getCourse().getName().substring(1)+" "+sc.getType()+" "+sc.getLecturer();
				if(sc.getTwoTimes()==true)
					errorlist=errorlist+" ביום"+sc.getDay().getName()+" וביום"+sc.getDayTwo().getName();
				else
					errorlist=errorlist+" ביום"+sc.getDay().getName();
			}
			else
				NewCustomCourseController.addToGrid(sc);
			
		}
		if(errorlist.length()==0)
		{
			util.GUI.infoAlert("טעינה בוצעה בהצלחה\nוכל הקורסים עדיין זמינים", "נטען בהצלחה", "אישור");
		}
		else
		{
			util.GUI.alertErrorWithOption("המערכת נטענה אך שים לב שהקורסים הבאים חסרים:"+errorlist, "מערכת לא מעודכנת", "אישור");
		}
		selection=lastSelection;
		
	}

	private static void removeAllSchedule() 
	{
		ArrayList<Schedule> list=Course.getSchduledCourses();
		Platform.runLater(()->{
			for(int i=0;i<list.size();i++)
			{
				Schedule sc=list.get(i);
				Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox1());
	        	if(sc.getSplited())
	        		Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox2());
	        	if(sc.getTwoTimes())
	        	{
	            	Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox3());
	            	if(sc.getSplitedTwo())
	            		Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox4());
	        	}
			}
			list.clear();
		});
		
	}
	
	

	
	



}
