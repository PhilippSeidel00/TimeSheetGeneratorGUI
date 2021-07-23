package main.controller.workslicecontroller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.model.GUIModel;
import main.view.components.TimeSpinner;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Controls a Workslice
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class DefaultWorksliceController implements WorksliceController {

    private final String workTimeErrorString;
    private final String worktimeFormat;

    private double workTime;
    private boolean workTimeError;

    private final GUIModel model;

    public DefaultWorksliceController(GUIModel model) {
        this.model = model;
        workTimeErrorString = model.getResourceBundle().getString("workTimeErrorText");
        this.worktimeFormat = model.getTimeFormat();
    }

    @FXML
    private TextField occupationField;

    @FXML
    private TextField worktimeField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TimeSpinner startSpinner;

    @FXML
    private TimeSpinner endSpinner;

    @FXML TextField pauseField;

    @FXML
    private Button deleteButton;

    @FXML
    private CheckBox vacationCheck;

    public void close() {
        model.removeWorkSlice(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setWorktimeField(startSpinner.getValue(), endSpinner.getValue());
        startSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            setWorktimeField(newValue, endSpinner.getValue());
            });
        endSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            setWorktimeField(startSpinner.getValue(), newValue);
            });
    }

    @Override
    public LocalDate getDate() {
        return datePicker.getValue();
    }

    @Override
    public LocalTime getStart() {
        return startSpinner.getValue();
    }

    @Override
    public LocalTime getEnd() {
        return endSpinner.getValue();
    }

    @Override
    public float getPause() {
        return Float.parseFloat(pauseField.getCharacters().toString().equals("") ?
                "-1" : pauseField.getCharacters().toString());
    }

    @Override
    public String getOccupation() {
        return occupationField.getCharacters().toString();
    }

    @Override
    public boolean isVacation() {
        return vacationCheck.isSelected();
    }

    @Override
    public double getWorkTime() {
        return workTime;
    }

    @Override
    public boolean isValid() {
        return !workTimeError;
    }

    private void setWorktimeField(LocalTime startTime, LocalTime endTime) {
        var oldTotalWorkingTime = model.getTotalWorkTime() - workTime;
        workTime = Duration.between(startTime, endTime).toMinutes() / 60d;
        if (workTime < 0) {
            setWorkTimeError(true);
        } else {
            setWorkTimeError(false);
            var workTimeString = String.valueOf(workTime);
            workTimeString = workTimeString.substring(0,
                    Math.min(workTimeString.length(), model.getWorkTimeStringLength()));
            worktimeField.setText(String.format(worktimeFormat, workTimeString));
            model.setTotalWorkTime(oldTotalWorkingTime + workTime);
        }
    }

    private void setWorkTimeError(boolean isError) {
        if (isError == workTimeError) return;
        String style = "-fx-text-fill: ";
        if (isError) {
            style += "red;";
            worktimeField.setText(workTimeErrorString);
        } else {
            style += "black;";
        }
        worktimeField.setStyle(style);
        workTimeError = isError;
    }
}
