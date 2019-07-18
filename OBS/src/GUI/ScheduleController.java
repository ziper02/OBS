package GUI;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    public JFXButton MultiLoad;
	
	@FXML
	public JFXButton CreateAutomaticSchedule;

	@FXML
	public JFXButton LoadSchedule;

	@FXML
	public  ProgressIndicator PBar;
	
	@FXML
	public Pane MiddlePane;

	@FXML
	public Pane LeftPane;

	@FXML
	private GridPane ScheduleGrid;

	public static ArrayList<Scanner> st;
	private static ArrayList<Schedule> schedule;

    @FXML
    public JFXButton SaveSchedulePNG;

	public static int selection;
	private static int lastSelection;
	public static NewCustomCourseController controller=null;
	public static AutoCourseController controllerAuto=null;
	static  Pane saveSchedule;
    @FXML
    public void LoadNewCusomCourseController()
    {
    	Pane newLoadedPane;
		try 
		{
			if(selection!=1 && selection!=3)
			{
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/NewCustomCourse.fxml"));
				newLoadedPane = loader.load();
				if(controllerAuto!=null && MiddlePane.getChildren().size()!=0)
					MiddlePane.getChildren().remove(0);

				MiddlePane.getChildren().add(newLoadedPane);
				controller=loader.getController();
				selection=1;
			}
			else if(selection==3)
			{
				LeftPane.getChildren().remove(0);
				MiddlePane.getChildren().remove(0);
				LeftPane.getChildren().add(saveSchedule);
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
			if(selection!=2 && selection!=3)
			{
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/AutoCourse.fxml"));
				newLoadedPane = loader.load();
				if(controller!=null && MiddlePane.getChildren().size()!=0)
					MiddlePane.getChildren().remove(0);
				MiddlePane.getChildren().add(newLoadedPane);
				controllerAuto=loader.getController();
				selection=2;
			}
			else if(selection==3)
			{
				LeftPane.getChildren().remove(0);
				MiddlePane.getChildren().remove(0);
				LeftPane.getChildren().add(saveSchedule);
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


	@FXML
	void LoadSchedulePressed()
	{
		if(selection==3)
		{
			LeftPane.getChildren().remove(0);
			MiddlePane.getChildren().remove(0);
			LeftPane.getChildren().add(saveSchedule);
		}
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
					@SuppressWarnings("unchecked")
					List<Schedule> list2 = (List<Schedule>) ois.readObject();
					ArrayList<Schedule> list = new ArrayList<>(list2);
					ois.close();
					lastSelection=selection;
					selection=4;
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
	void SaveSchedulePressed()
	{
		if(selection==3)
		{
			LeftPane.getChildren().remove(0);
			MiddlePane.getChildren().remove(0);
			LeftPane.getChildren().add(saveSchedule);
		}
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
	void ValidSchedulePressed()
	{
		if(selection==3)
		{
			LeftPane.getChildren().remove(0);
			MiddlePane.getChildren().remove(0);
			LeftPane.getChildren().add(saveSchedule);
		}
		Course.VaildSchedule();
	}

    @FXML
    void SaveSchedulePNGPressed()
    {
		if(selection==3)
		{
			LeftPane.getChildren().remove(0);
			MiddlePane.getChildren().remove(0);
			LeftPane.getChildren().add(saveSchedule);
		}
    	WritableImage image = ScheduleGrid.snapshot(new SnapshotParameters(), null);
    	try
		{
			FileChooser fc=new FileChooser();
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG File","*.png"));
		  	fc.setTitle("Save My Schedule");
		  	fc.setInitialFileName("MySchedule.png");
		  	File file =fc.showSaveDialog(Main.primaryStage);
		  	if(file!=null)
		  	{
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
			e.printStackTrace();
		}
    }

	private void checkForUpdate(ArrayList<Schedule> list)
	{
		st=new ArrayList<>();
		schedule=list;
		LoadSchedule.setVisible(false);
		PBar.setVisible(true);
		for (Schedule value : list)
		{
			String courseID = value.getCourse().getID();
			if (!Course.couseExist(Integer.parseInt(courseID)))
			{
				Scanner temp = new Scanner(courseID);
				if (!st.contains(temp))
					st.add(temp);
			}
		}
		for (Scanner scanner : st)
		{
			scanner.start();
		}
		
	}

	
	public static void checkForUpdateResult()
	{
		removeAllSchedule();
		StringBuilder errorlist= new StringBuilder();
		for (Scanner scanner : st)
		{
			Course course = scanner.getValue();
			Course.addCourse(course);
		}
		for (Schedule sc : schedule)
		{
			sc.checkSetUpAllparamters();
			if (!Schedule.checkIfAvailable(sc)) {
				errorlist.append("\n").append(sc.getCourse().getName().substring(1)).append(" ").append(sc.getType()).append(" ").append(sc.getLecturer());
				if (sc.getTwoTimes())
					errorlist.append(" ביום").append(sc.getDay().getName()).append(" וביום").append(sc.getDayTwo().getName());
				else
					errorlist.append(" ביום").append(sc.getDay().getName());
			} else
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


	static void removeAllSchedule()
	{
		ArrayList<Schedule> list=Course.getSchduledCourses();
		try
		{
			for (Schedule sc : list) {
				Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox1());
				if (sc.getSplited())
					Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox2());
				if (sc.getTwoTimes()) {
					Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox3());
					if (sc.getSplitedTwo())
						Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox4());
				}
				sc.setSelected(false);
			}
			list.clear();
		}
		catch(Exception e)
		{
			Platform.runLater(()->{
				for (Schedule sc : list) {
					Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox1());
					if (sc.getSplited())
						Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox2());
					if (sc.getTwoTimes()) {
						Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox3());
						if (sc.getSplitedTwo())
							Main.scheduleController.getScheduleGrid().getChildren().remove(sc.getGridPaneVBox4());
					}
				}
				list.clear();
			});
		}
		
	}
	
	

    @FXML
    void MultiLoadAction()
    {
		Pane newLoadedPane;
		try
		{
			if(selection!=3)
			{
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/MultiSelection.fxml"));
				newLoadedPane = loader.load();
				saveSchedule= (Pane) LeftPane.getChildren().get(0);
				LeftPane.getChildren().remove(0);
				LeftPane.getChildren().add(newLoadedPane);
				Main.multiSelectionController =loader.getController();

				loader=new FXMLLoader(getClass().getResource("/GUI/DepSelection.fxml"));
				newLoadedPane = loader.load();
				if(MiddlePane.getChildren().size()!=0)
					MiddlePane.getChildren().remove(0);
				MiddlePane.getChildren().add(newLoadedPane);
				selection=3;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

    }


	public void MultiSelectionResult()
	{
		Platform.runLater(() ->
		{
			for (Scanner scanner : st)
			{
				Course course = scanner.getValue();
				if(course!= null)
					Course.addCourse(course);
			}
			LeftPane.getChildren().remove(0);
			MiddlePane.getChildren().remove(0);
			LeftPane.getChildren().add(saveSchedule);
			selection=(-1);
			util.GUI.infoAlert("הקורסים נטענו בהצלחה", "נטען בהצלחה", "אישור");
		});

	}




}
