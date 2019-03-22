package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ScheduleController {

    @FXML
    private MenuButton MenuFile;

    @FXML
    private MenuItem newGridBtn;

    @FXML
    private MenuItem saveBtn;

    @FXML
    private VBox courseVbox;

    @FXML
    private TextField courseTF;

    @FXML
    private RadioButton lectRB;

    @FXML
    private ToggleGroup typeGroup;

    @FXML
    private RadioButton execRB;

    @FXML
    private RadioButton labRB;

    @FXML
    private RadioButton wsRB;

    @FXML
    private VBox lectVbox;

    @FXML
    private Label lectLabel;

    @FXML
    private TextField lectTF;

    @FXML
    private Label classLabel;

    @FXML
    private TextField classTF;

    @FXML
    private Label dayLabel;

    @FXML
    private ComboBox<?> dayCB;

    @FXML
    private Label startTimeLabel;

    @FXML
    private ComboBox<?> startTimeCB;

    @FXML
    private Label endTimeLabel;

    @FXML
    private ComboBox<?> endTimeCB;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button endBtn;

    @FXML
    private Label colorLabel;

    @FXML
    private ColorPicker colorCP;

    @FXML
    private GridPane ScheduleGrid;

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void dayHiding(ActionEvent event) {

    }

    @FXML
    void deleted(ActionEvent event) {

    }

    @FXML
    void end(ActionEvent event) {

    }

    @FXML
    void endHiding(ActionEvent event) {

    }

    @FXML
    void execAction(ActionEvent event) {

    }

    @FXML
    void keyTypedClassTF(KeyEvent event) {

    }

    @FXML
    void keyTypedCourseTF(KeyEvent event) {

    }

    @FXML
    void keyTypedLectTF(KeyEvent event) {

    }

    @FXML
    void labAction(ActionEvent event) {

    }

    @FXML
    void lectAction(ActionEvent event) {

    }

    @FXML
    void loadFromFile(ActionEvent event) {

    }

    @FXML
    void newCourse(ActionEvent event) {

    }

    @FXML
    void newGrid(ActionEvent event) {

    }

    @FXML
    void saveToFile(ActionEvent event) {

    }

    @FXML
    void startHiding(ActionEvent event) {

    }

    @FXML
    void wsAction(ActionEvent event) {

    }

}
