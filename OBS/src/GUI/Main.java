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


		s=new Semester("סמסטר ג'",false);
		c=new Course();
		c.setName("טורים,התמרות ומשוואת דיפרנציאליות");
		c.setID("11129");
		s.add(c);
		c=new Course();
		c.setName("לוגיקה");
		c.setID("61746");
		s.add(c);
		c=new Course();
		c.setName("מבני נתונים");
		c.setID("61747");
		s.add(c);
		c=new Course();
		c.setName("ארכיטקטורה ומבנה המחשב");
		c.setID("61748");
		s.add(c);
		c=new Course();
		c.setName("מתמתיקה דיסקרטית 2");
		c.setID("61749");
		s.add(c);
		c=new Course();
		c.setName("מבוא להנדסת תוכנה");
		c.setID("61750");
		s.add(c);
		d.add(s);

		s=new Semester("סמסטר ד'",false);
		c=new Course();
		c.setName("מכניקה להנדסת תוכנה");
		c.setID("11158");
		s.add(c);
		c=new Course();
		c.setName("תכנות מונחה עצמים");
		c.setID("61751");
		s.add(c);
		c=new Course();
		c.setName("מערכות הפעלה");
		c.setID("61752");
		s.add(c);
		c=new Course();
		c.setName("אלגורתמים");
		c.setID("61753");
		s.add(c);
		c=new Course();
		c.setName("מערכות מסדי נתונים מ");
		c.setID("61755");
		s.add(c);
		d.add(s);

		s=new Semester("סמסטר ה'",false);
		c=new Course();
		c.setName("אנגלית טכנית יישומית - תכנה");
		c.setID("11069");
		s.add(c);
		c=new Course();
		c.setName("שיטות הנדסיות לפיתוח תכנה");
		c.setID("61756");
		s.add(c);
		c=new Course();
		c.setName("מבוא לבדיקות תוכנה");
		c.setID("61756");
		s.add(c);
		c=new Course();
		c.setName("אוטומטים וחישוביות");
		c.setID("61759");
		s.add(c);
		c=new Course();
		c.setName("הסתברות להנדסת תוכנה");
		c.setID("61760");
		s.add(c);
		c=new Course();
		c.setName("ממשק אדם מחשב");
		c.setID("61769");
		s.add(c);
		d.add(s);

		s=new Semester("סמסטר ו'",false);
		c=new Course();
		c.setName("חשמל ומגנטיות להנדסת תכנה");
		c.setID("11159");
		s.add(c);
		c=new Course();
		c.setName("מבנה מערכות הפעלה וזמן אמת");
		c.setID("61758");
		s.add(c);
		c=new Course();
		c.setName("כריית נתונים ומערכות לומדות");
		c.setID("61761");
		s.add(c);
		c=new Course();
		c.setName("תורת הקומפלציה");
		c.setID("61763");
		s.add(c);
		c=new Course();
		c.setName("גרפיקה ממוחשבת");
		c.setID("61764");
		s.add(c);
		d.add(s);

		s=new Semester("סמסטר ז'",false);
		c=new Course();
		c.setName("רשתות מחשבים");
		c.setID("61765");
		s.add(c);
		c=new Course();
		c.setName("פרויקט בהנדסת תכנה שלב א'");
		c.setID("61766");
		s.add(c);
		c=new Course();
		c.setName("אבטחת מידע וקריפטולוגיה");
		c.setID("61767");
		s.add(c);
		c=new Course();
		c.setName("תכנות מקבילי ומבוזר וטכנולוגית ענן");
		c.setID("61768");
		s.add(c);
		c=new Course();
		c.setName("ממשק אדם מחשב מ'");
		c.setID("61770");
		s.add(c);
		d.add(s);

		s=new Semester("סמסטר ח'",false);
		c=new Course();
		c.setName("פרויקט בהנדסת תכנה שלב ב'");
		c.setID("61765");
		s.add(c);
		d.add(s);


		s=new Semester("אשכול מדעים",true);
		c=new Course();
		c.setName("פיזיקה מודרנית");
		c.setID("11198");
		s.add(c);
		c=new Course();
		c.setName("מבוא לביולוגיה מולקולרית");
		c.setID("41942");
		s.add(c);
		c=new Course();
		c.setName("תורת המשחקים");
		c.setID("61957");
		s.add(c);
		c=new Course();
		c.setName("תורת המידע");
		c.setID("61958");
		s.add(c);
		c=new Course();
		c.setName("מחשבים קוונטים");
		c.setID("61989");
		s.add(c);
		c=new Course();
		c.setName("תכנות מדעי");
		c.setID("61991");
		s.add(c);
		c=new Course();
		c.setName("מבוא לחישה ולמידה");
		c.setID("61992");
		s.add(c);
		d.add(s);

		s=new Semester("אשכול אלגורתמים",true);
		c=new Course();
		c.setName("אנליזה נומרית");
		c.setID("61959");
		s.add(c);
		c=new Course();
		c.setName("מבוא לאופטמיזציה");
		c.setID("61960");
		s.add(c);
		c=new Course();
		c.setName("אחזור מידע");
		c.setID("61961");
		s.add(c);
		c=new Course();
		c.setName("גיאומטריה חישובית ומידול");
		c.setID("61962");
		s.add(c);
		c=new Course();
		c.setName("בינה מלאכותית");
		c.setID("61963");
		s.add(c);
		c=new Course();
		c.setName("ויזואליזציה של המידע");
		c.setID("61964");
		s.add(c);
		c=new Course();
		c.setName("ניתוח של נתונתי הרשתות");
		c.setID("61965");
		s.add(c);
		d.add(s);

		s=new Semester("אשכול סמינרים",true);
		c=new Course();
		c.setName("סמינר מערכות לומדות");
		c.setID("61966");
		s.add(c);
		c=new Course();
		c.setName("סמינר באלגוריתמים אקראיים");
		c.setID("61967");
		s.add(c);
		c=new Course();
		c.setName("סמינר באלגורתמים מתקדמים");
		c.setID("61968");
		s.add(c);
		c=new Course();
		c.setName("סמינר באימות תכנה");
		c.setID("61969");
		s.add(c);
		c=new Course();
		c.setName("סמינר באוטומטים");
		c.setID("61970");
		s.add(c);
		d.add(s);

		s=new Semester("אשכול עיבוד אותות ורשתות",true);
		c=new Course();
		c.setName("עיבוד תמונה ספרתי");
		c.setID("61971");
		s.add(c);
		c=new Course();
		c.setName("עיבוד אותות ספרתי DSP");
		c.setID("61972");
		s.add(c);
		c=new Course();
		c.setName("תקשורת אלחוטית ורשתות מחשבים");
		c.setID("61973");
		s.add(c);
		c=new Course();
		c.setName("בדיקת מערכות ספרתיות");
		c.setID("61974");
		s.add(c);
		c=new Course();
		c.setName("דחיסת נתונים");
		c.setID("61975");
		s.add(c);
		c=new Course();
		c.setName("ביולוגיה חישובית");
		c.setID("61976");
		s.add(c);
		d.add(s);

		s=new Semester("אשכול הנדסת תכנה",true);
		c=new Course();
		c.setName("מסדי נתונים מבוזרים");
		c.setID("61834");
		s.add(c);
		c=new Course();
		c.setName("טכנולוגיית WEB מתקדם");
		c.setID("61977");
		s.add(c);
		c=new Course();
		c.setName("אימות תכנה וחומרה");
		c.setID("61978");
		s.add(c);
		c=new Course();
		c.setName("מחשוב ענן");
		c.setID("61979");
		s.add(c);
		c=new Course();
		c.setName("שפות תכנות");
		c.setID("61980");
		s.add(c);
		c=new Course();
		c.setName("הנדסת דרישות");
		c.setID("61981");
		s.add(c);
		d.add(s);

		s=new Semester("אשכול מעבדות",true);
		c=new Course();
		c.setName("מעבדה במידול מערכות אקולוגיות");
		c.setID("61982");
		s.add(c);
		c=new Course();
		c.setName("מעבדה בתכנות מקבילי והטרוגני");
		c.setID("61983");
		s.add(c);
		c=new Course();
		c.setName("מעבדה באופטימיזיציה");
		c.setID("61984");
		s.add(c);
		c=new Course();
		c.setName("מעבדה בפיתוח יישמים באנדרואיד");
		c.setID("61985");
		s.add(c);
		c=new Course();
		c.setName("מעבדה בסחר אלקטורני");
		c.setID("61986");
		s.add(c);
		c=new Course();
		c.setName("מעבדה בכריית נתונים");
		c.setID("61987");
		s.add(c);
		c=new Course();
		c.setName("מעבדה בעיצוב תבניות בתכנה");
		c.setID("61988");
		s.add(c);
		c=new Course();
		c.setName("מעבדה בטכנולוגיות תכנות צד לקוח ושרת");
		c.setID("61990");
		s.add(c);
		d.add(s);


	}



}
