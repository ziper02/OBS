package GUI;

import java.awt.*;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Scanner;



public class LoadingPanelController 
{


    @FXML
    private ProgressIndicator PBar;

    private static FXMLLoader loader;
	private static Parent root;
	private static Scene scene;
	
    @FXML
    void initialize()
    {
    	loader=new FXMLLoader(getClass().getResource("/GUI/ScheduleController.fxml")); // load the FXML file
    	PBar.setVisible(true);
    	Scanner init=new Scanner();
    	init.start();
    }
    
    @FXML
    public static void LoadMainPanel() 
    {
    	Platform.runLater(()->
		{  
			try 
			{
				root = loader.load();
				
			} 
			catch (IOException e1) 
			{
					e1.printStackTrace();
			}
			Main.scheduleController=loader.getController();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			if(width==1280 && height==720)
				scene=new Scene(root, 900, 650);
			else
				scene=new Scene(root, 1639, 1016);
			Stage stage=new Stage(StageStyle.DECORATED);
			stage.setScene( scene );
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("/icons/date_and_time_clock-512.png")));
			stage.setTitle( "Schedule" );
			Main.primaryStage.close();
			stage.setOnCloseRequest(e-> System.exit(0));
			Main.primaryStage=stage;
			Main.primaryStage.show();	
		});
    }
}
