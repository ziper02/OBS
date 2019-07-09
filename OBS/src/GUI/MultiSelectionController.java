package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import entity.Course;
import entity.Department;
import entity.Semester;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class MultiSelectionController
{

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
    private FlowPane TitleSelected;

    @FXML
    private FlowPane Semester5;

    @FXML
    private JFXButton TitleSemester5;

    @FXML
    private FlowPane Semester4;

    @FXML
    private JFXButton TitleSemester4;

    @FXML
    private FlowPane Semester3;

    @FXML
    private JFXButton TitleSemester3;

    @FXML
    private FlowPane Semester2;

    @FXML
    private JFXButton TitleSemester2;

    @FXML
    private FlowPane Semester1;

    @FXML
    private JFXButton TitleSemester1;

    @FXML
    private FlowPane SemesterExtra2;

    @FXML
    private JFXButton TitleSemesterExtra2;

    @FXML
    private FlowPane SemesterExtra1;

    @FXML
    private JFXButton TitleSemesterExtra1;

    @FXML
    private FlowPane Semester8;

    @FXML
    private JFXButton TitleSemester8;

    @FXML
    private FlowPane Semester7;

    @FXML
    private JFXButton TitleSemester7;

    @FXML
    private FlowPane Semester6;

    @FXML
    private JFXButton TitleSemester6;

    @FXML
    private FlowPane SemesterExtra7;

    @FXML
    private JFXButton TitleSemesterExtra7;

    @FXML
    private FlowPane SemesterExtra6;

    @FXML
    private JFXButton TitleSemesterExtra6;

    @FXML
    private FlowPane SemesterExtra5;

    @FXML
    private JFXButton TitleSemesterExtra5;

    @FXML
    private FlowPane SemesterExtra4;

    @FXML
    private JFXButton TitleSemesterExtra4;

    @FXML
    private FlowPane SemesterExtra3;

    @FXML
    private JFXButton TitleSemesterExtra3;

    @FXML
    private Button BackButton;

    @FXML
    private Button AccepetButton;

    @FXML
    private JFXButton TitleButton;

    ArrayList<JFXButton> Titles=new ArrayList<JFXButton>();
    ArrayList<FlowPane> FlowPanes=new ArrayList<FlowPane>();

    @FXML
    void initialize()
    {
        createArraysForUI();
        BackButton.setVisible(true);
        AccepetButton.setVisible(false);
        TitleButton.setVisible(false);
        for(FlowPane f: FlowPanes)
            f.setVisible(false);
    }


    private void createArraysForUI()
    {
        Titles.add(TitleSemester1);
        Titles.add(TitleSemester2);
        Titles.add(TitleSemester3);
        Titles.add(TitleSemester4);
        Titles.add(TitleSemester5);
        Titles.add(TitleSemester6);
        Titles.add(TitleSemester7);
        Titles.add(TitleSemester8);
        Titles.add(TitleSemesterExtra1);
        Titles.add(TitleSemesterExtra2);
        Titles.add(TitleSemesterExtra3);
        Titles.add(TitleSemesterExtra4);
        Titles.add(TitleSemesterExtra5);
        Titles.add(TitleSemesterExtra6);
        Titles.add(TitleSemesterExtra7);
        FlowPanes.add(Semester1);
        FlowPanes.add(Semester2);
        FlowPanes.add(Semester3);
        FlowPanes.add(Semester4);
        FlowPanes.add(Semester5);
        FlowPanes.add(Semester6);
        FlowPanes.add(Semester7);
        FlowPanes.add(Semester8);
        FlowPanes.add(SemesterExtra1);
        FlowPanes.add(SemesterExtra2);
        FlowPanes.add(SemesterExtra3);
        FlowPanes.add(SemesterExtra4);
        FlowPanes.add(SemesterExtra5);
        FlowPanes.add(SemesterExtra6);
        FlowPanes.add(SemesterExtra7);
    }




    @FXML
    void BioButtonPressed(ActionEvent event)
    {

    }

    @FXML
    void IndusButtonPress(ActionEvent event)
    {

    }

    @FXML
    void MathButtonPressed(ActionEvent event)
    {

    }

    @FXML
    void MechButtonPress(ActionEvent event)
    {

    }

    @FXML
    void PhyButtonPressed(ActionEvent event)
    {

    }

    @FXML
    void SoftButtonPressed(ActionEvent event)
    {
        TitleButton.setText(Department.department.get(0).getName());
        TitleButton.setStyle("-fx-background-color: #87cefa");
        loadUI(Department.department.get(0));
    }

    private void loadUI(Department d)
    {
        int i=0;
        for(FlowPane f: FlowPanes)
        {
            f.getChildren().clear();
            Titles.get(i).setVisible(true);
            f.getChildren().add(Titles.get(i));
            i++;
        }
        i=0;
        BackButton.setVisible(true);
        AccepetButton.setVisible(true);
        TitleButton.setVisible(true);
        ArrayList<Semester> Semesters=d.getSemesters();
        for(Semester semester: Semesters)
        {
            ArrayList<Course> courses=semester.getCourses();
            Titles.get(i).setText(semester.getName());
            FlowPanes.get(i).setAlignment(Pos.TOP_RIGHT);
            FlowPanes.get(i).setVisible(true);
            for(Course course: courses)
            {
                JFXCheckBox cb=new JFXCheckBox();
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                Label label= new Label(course.getName());
                label.setAlignment(Pos.CENTER_RIGHT);
                hBox.getChildren().addAll(label, cb);
                hBox.setSpacing(10);
                hBox.setPrefWidth(185);
                hBox.setPrefHeight(20);
                hBox.setAccessibleHelp(course.getID());
                FlowPanes.get(i).getChildren().add(hBox);
            }
            i++;
        }
        for(;i<FlowPanes.size();i++)
            FlowPanes.get(i).setVisible(false);
    }


    @FXML
    void AccepetButtonPressed(ActionEvent event)
    {

    }

    @FXML
    void BackButtonPressed(ActionEvent event)
    {
        Main.scheduleController.LeftPane.getChildren().remove(0);
        Main.scheduleController.LeftPane.getChildren().add(Main.scheduleController.saveSchedule);
        if(Main.scheduleController.selection==3)
            Main.scheduleController.selection=(-1);
    }
}
