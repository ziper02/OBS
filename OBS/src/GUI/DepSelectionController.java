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
        Main.mutliSelectionController.TitleButton.setText(Department.department.get(4).getName());
        Main.mutliSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.mutliSelectionController.loadUI(Department.department.get(4));
    }

    @FXML
    void IndusButtonPress(ActionEvent event)
    {
        Main.mutliSelectionController.TitleButton.setText(Department.department.get(2).getName());
        Main.mutliSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.mutliSelectionController.loadUI(Department.department.get(2));
    }

    @FXML
    void InformButtonPressed(ActionEvent event)
    {
        Main.mutliSelectionController.TitleButton.setText(Department.department.get(3).getName());
        Main.mutliSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.mutliSelectionController.loadUI(Department.department.get(3));
    }

    @FXML
    void MathButtonPressed(ActionEvent event)
    {
        Main.mutliSelectionController.TitleButton.setText(Department.department.get(5).getName());
        Main.mutliSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.mutliSelectionController.loadUI(Department.department.get(5));
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
        Main.mutliSelectionController.TitleButton.setText(Department.department.get(6).getName());
        Main.mutliSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.mutliSelectionController.loadUI(Department.department.get(6));
    }

    @FXML
    void SoftButtonPressed(ActionEvent event)
    {
        Main.mutliSelectionController.TitleButton.setText(Department.department.get(0).getName());
        Main.mutliSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.mutliSelectionController.loadUI(Department.department.get(0));
    }

}
