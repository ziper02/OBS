package GUI;

import java.io.IOException;

import entity.Course;
import entity.Department;
import entity.Semester;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
	public static  ScheduleController scheduleController;
	public static Stage primaryStage;
	  public void start(Stage primaryStage) throws IOException 
	  {
		  	Main.primaryStage=primaryStage;
		  	FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/LoadingPanel.fxml")); // load the FXML file
	        Parent root = (Parent) loader.load();
		    Scene scene=new Scene(root, 282, 341);
		    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/date_and_time_clock-512.png")));
		    primaryStage.setScene( scene );
		    primaryStage.setTitle( "Schedule" );
		    primaryStage.initStyle(StageStyle.UNDECORATED);
		    primaryStage.setOnCloseRequest(e->
			{
				try
				{
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				}
				catch (IOException ex)
				{
					util.GUI.alertErrorWithOptionWithExit("Failed to kill chromedriver.\nReason:not windows.","kill chromedriver error","exit");
					ex.printStackTrace();
				}
				System.exit(0);
			});
		    primaryStage.show();
	  }

	public static void main(String[] args) 
	{
		createDataBaseDepartments();
		launch(args);
	}

	private static void createDataBaseDepartments()
	{
		Department d=new Department("הנדסת תוכנה");
		Department.department.add(d);
		Semester s=new Semester("סמסטר א'",false);
		Course c=new Course();
		c.setName("אנגלית בסיסי");
		c.setID("110063");
		s.add(c);
		c=new Course();
		c.setName("אנגלית מתקדמים א'");
		c.setID("110064");
		s.add(c);
		c=new Course();
		c.setName("מבוא לפיזיקה אקדמית");
		c.setID("11179");
		s.add(c);
		c=new Course();
		c.setName("מיומנות למידה");
		c.setID("11947");
		s.add(c);
		c=new Course();
		c.setName("חדווא 1מ");
		c.setID("11004");
		s.add(c);
		c=new Course();
		c.setName("אלגברה 1מח");
		c.setID("11002");
		s.add(c);
		c=new Course();
		c.setName("מערכות ספרתיות");
		c.setID("61740");
		s.add(c);
		c=new Course();
		c.setName("מבוא למדעי המחשב");
		c.setID("61741");
		s.add(c);
		c=new Course();
		c.setName("אוריינות בעברית");
		c.setID("11351");
		s.add(c);
		d.add(s);

		s=new Semester("סמסטר ב'",false);
		c=new Course();
		c.setName("חדווא 2מ");
		c.setID("11006");
		s.add(c);
		c=new Course();
		c.setName("אלגברה 2מח");
		c.setID("11020");
		s.add(c);
		c=new Course();
		c.setName("אנגלית מתקדמים ב'");
		c.setID("11060");
		s.add(c);
		c=new Course();
		c.setName("מתמטיקה דיסקרטית 1");
		c.setID("61743");
		s.add(c);
		c=new Course();
		c.setName("ארגון ותכנון המחשב");
		c.setID("61744");
		s.add(c);
		c=new Course();
		c.setName("מבוא לתכנות מערכות");
		c.setID("61745");
		s.add(c);
		d.add(s);

	}



}
