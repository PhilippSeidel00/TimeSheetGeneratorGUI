package main.controller.componentcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.model.GUIModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls a Workslice
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class WorksliceController implements ComponentController {

    private final GUIModel model;

    public WorksliceController(GUIModel model) {
        this.model = model;
    }

    @FXML
    private TextField occupationField;

    @FXML
    private TextField worktimeField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteButton;

    @FXML
    private CheckBox vacationCheck;

    public void close() {
        model.removeWorkSlice(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //this controller comes pre-initialized especially for you :)
    }
}
