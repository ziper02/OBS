package util;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public abstract class GUI 
{

	
	
	public static void alertErrorWithOption(String headerText,String title,String btnName) 
    { 
		Platform.runLater(()->
		{ 
			Alert alert = new Alert(Alert.AlertType.ERROR);
			ButtonType bttexit = new ButtonType(btnName, ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().clear();
			alert.setHeaderText(headerText);
			alert.setTitle(title);
			alert.getButtonTypes().addAll(bttexit);
			Optional<ButtonType> result = alert.showAndWait();
		}); 
    }
	
	public static void infoAlert(String headerText,String title,String btnName)  
	{
		Platform.runLater(()->
		{   
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			ButtonType bttCountiue = new ButtonType(btnName, ButtonBar.ButtonData.FINISH);
			alert.getButtonTypes().clear();
			alert.setHeaderText(headerText);
			alert.setTitle(title);
			alert.getButtonTypes().addAll(bttCountiue);
			Optional<ButtonType> result = alert.showAndWait();
		});
	}
}
