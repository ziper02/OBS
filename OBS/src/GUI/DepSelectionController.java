package GUI;

import entity.Department;
import javafx.fxml.FXML;

public class DepSelectionController {


    @FXML
    void BioButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText(Department.department.get(4).getName());
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get(4));
    }

    @FXML
    void IndusButtonPress()
    {
        Main.multiSelectionController.TitleButton.setText(Department.department.get(2).getName());
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get(2));
    }

    @FXML
    void InformButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText(Department.department.get(3).getName());
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get(3));
    }

    @FXML
    void MathButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText(Department.department.get(5).getName());
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get(5));
    }

    @FXML
    void MechButtonPress()
    {
        Main.multiSelectionController.TitleButton.setText(Department.department.get(1).getName());
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get(1));
    }

    @FXML
    void PhyButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText(Department.department.get(6).getName());
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get(6));
    }

    @FXML
    void SoftButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText(Department.department.get(0).getName());
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get(0));
    }

}
