package GUI;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Scanner;

public class Main extends Application
{
	public static  ScheduleController scheduleController;
	
	  public void start(Stage primaryStage) throws IOException 
	  {
		    // constructing our scene
		  	FXMLLoader loader=new FXMLLoader(getClass().getResource("ScheduleController.fxml")); // load the FXML file
	        Parent root = (Parent) loader.load();
		    //AnchorPane pane = FXMLLoader.load( url );
		    scheduleController=loader.getController();
		    //Scene scene = new Scene( pane );
		    Scene scene=new Scene(root, 1639, 1016);
		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/icons/date_and_time_clock-512.png")));
		    // setting the stage
		    primaryStage.setScene( scene );
		    primaryStage.setTitle( "Schedule" );
		    primaryStage.setOnCloseRequest(e->{ System.exit(0);});
		    primaryStage.show();
	  }

	public static void main(String[] args) 
	{
		Scanner.setScanner();
		launch(args);
	}

}
