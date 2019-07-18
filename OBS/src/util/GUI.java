package util;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

import java.util.Optional;

public abstract class GUI 
{

	
	
	public static void alertErrorWithOption(String headerText,String title,String btnName) 
    { 
		Platform.runLater(()->
		{ 
			Alert alert = new Alert(Alert.AlertType.ERROR);
			ButtonType bttexit = new ButtonType(btnName, ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().clear();
			alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			alert.setHeaderText(headerText);
			alert.setTitle(title);
			alert.getButtonTypes().addAll(bttexit);
			alert.showAndWait();
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
			alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			alert.setTitle(title);
			alert.getButtonTypes().addAll(bttCountiue);
			alert.showAndWait();
		});
	}
	
	public static Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();

	    for (Node node : childrens) {
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) { // gridPane->GridPane
	            result = node;
	            break;
	        }
	    }

	    return result;
	}

	public static void IDcourseTFChanged(TextField IDcourseTF)
	{

			if (IDcourseTF.getLength() > 5 || IDcourseTF.getText().matches("[0-9]+")) {
				IDcourseTF.setText("");
				return;
			}
	}

	public static void alertErrorWithOptionWithExit(String headerText,String title,String btnName)
	{
		Platform.runLater(()->
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			ButtonType bttexit = new ButtonType(btnName, ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().clear();
			alert.getDialogPane().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			alert.setHeaderText(headerText);
			alert.setTitle(title);
			alert.getButtonTypes().addAll(bttexit);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
			{
				System.exit(0);
			}
		});
	}


	public static void disableButtons(Pane p)
	{
		for(Node n:p.getChildren())
		{
			if(n instanceof Pane)
				disableButtons((Pane)n);
			else if(n instanceof Button)
				n.setDisable(true);
		}
	}

}
