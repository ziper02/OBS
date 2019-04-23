package GUI;

import java.io.IOException;
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
		    primaryStage.setOnCloseRequest(e->{ System.exit(0);});
		    primaryStage.show();
	  }

	public static void main(String[] args) 
	{
		launch(args);
	}

}
