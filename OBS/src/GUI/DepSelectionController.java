package GUI;

import entity.Department;
import javafx.fxml.FXML;

public class DepSelectionController {


    @FXML
    void BioButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText("הנדסת ביוטכנולוגיה");
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get("הנדסת ביוטכנולוגיה"));
    }

    @FXML
    void IndusButtonPress()
    {
        Main.multiSelectionController.TitleButton.setText("הנדסת תעשייה וניהול");
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get("הנדסת תעשייה וניהול"));
    }

    @FXML
    void InformButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText("הנדסת מערכות מידע");
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get("הנדסת מערכות מידע"));
    }

    @FXML
    void MathButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText("מתמטיקה יישומית");
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get("מתמטיקה יישומית"));
    }

    @FXML
    void MechButtonPress()
    {
        Main.multiSelectionController.TitleButton.setText("הנדסת מכונות");
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get("הנדסת מכונות"));
    }

    @FXML
    void PhyButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText("פיזיקה והנדסה אופטית");
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get("פיזיקה והנדסה אופטית"));
    }

    @FXML
    void SoftButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText("הנדסת תוכנה");
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get("הנדסת תוכנה"));
    }

    @FXML
    void EleButtonPressed()
    {
        Main.multiSelectionController.TitleButton.setText("הנדסת חשמל");
        Main.multiSelectionController.TitleButton.setStyle("-fx-background-color: #87cefa");
        Main.multiSelectionController.loadUI(Department.department.get("הנדסת חשמל"));
    }


}
