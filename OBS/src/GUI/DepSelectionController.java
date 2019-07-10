package GUI;

import entity.Department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DepSelectionController {

    @FXML
    private Button SoftButton;

    @FXML
    private Button MechButton;

    @FXML
    private Button IndusButton;

    @FXML
    private Button BioButton;

    @FXML
    private Button MathButton;

    @FXML
    private Button PhyButton;

    @FXML
    private Button InformButton;

    @FXML
    void BioButtonPressed(ActionEvent event)
    {

    }

    @FXML
    void IndusButtonPress(ActionEvent event)
    {

    }

    @FXML
    void InformButtonPressed(ActionEvent event)
    {

    }

    @FXML
    void MathButtonPressed(ActionEvent event)
    {

    }

    @FXML
    void MechButtonPress(ActionEvent event)
    {
        Main.mutliSelectionController.TitleButton.setText(Department.department.get(1).getName());
        Main.mutliSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.mutliSelectionController.loadUI(Department.department.get(1));
    }

    @FXML
    void PhyButtonPressed(ActionEvent event)
    {

    }

    @FXML
    void SoftButtonPressed(ActionEvent event)
    {
        Main.mutliSelectionController.TitleButton.setText(Department.department.get(0).getName());
        Main.mutliSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.mutliSelectionController.loadUI(Department.department.get(0));
    }

}
