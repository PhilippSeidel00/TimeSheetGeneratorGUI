package main.controller.guicontroller;

import data.*;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import main.model.GUIModel;
import main.view.components.MoneySpinner;
import main.view.components.TimeSpinner;
import se.alipsa.ymp.YearMonthPickerCombo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Default Controller
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class DefaultGUIController implements GUIController {

    private final GUIModel model;

    private double currentTotalWorkTime;
    private double currentPredTransfer;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField organisationField;

    @FXML
    private Spinner<Integer> agreedWorktimeSpinner;

    @FXML
    private MoneySpinner wageSpinner;

    @FXML
    private TextField currentWorktimeField;

    @FXML
    private Spinner<Integer> carryInSpinner;

    @FXML
    private TextField carryOutField;

    @FXML
    private YearMonthPickerCombo yearMonthPicker;

    @FXML
    private CheckBox ubCheck;

    @FXML
    private CheckBox gfCheck;

    @FXML
    private CheckBox saveCheck;

    @FXML
    private VBox workSliceBox;


    public DefaultGUIController(GUIModel model) throws FileNotFoundException {
        this.model = model;
        this.currentTotalWorkTime = model.getTotalWorkTime();
        this.currentPredTransfer = 0;
    }

    /**
     * print all input data provided by the user
     */
    public void printAllData() {
        //print static userdata
        System.out.printf( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "surname: %s, " +
                "name: %s, " +
                "id: %s, " +
                "organisation: %s, " +
                "workTime: %d, " +
                "wage: %d, " +
                "ub: %b, " +
                "gf: %b, " +
                        "YearMonth: %s, " +
                        "carry in: %d, " +
                        "carry out: %d, " +
                "save: %b, " +
                        "total worktime: %f\n" +
                        "workslices:\n",
                surnameField.getCharacters().toString(),
                nameField.getCharacters().toString(),
                idField.getCharacters().toString(),
                organisationField.getCharacters().toString(),
                agreedWorktimeSpinner.getValue(),
                wageSpinner.getBalance(),
                ubCheck.isSelected(),
                gfCheck.isSelected(),
                yearMonthPicker.getValue(),
                carryInSpinner.getValue(),
                Integer.parseInt(carryOutField.getCharacters().toString().equals("") ?
                        "0" : carryOutField.getCharacters().toString()),
                saveCheck.isSelected(),
                currentTotalWorkTime);
        //print data in workslices
        if (model.getWorkSliceControllers().isEmpty()) {
            System.out.println("no workslices found.");
        } else {
            model.getWorkSliceControllers().forEach(c ->
                System.out.printf("occupation: %s, " +
                                "date: %s, " +
                                "start: %s, " +
                                "end: %s, " +
                                "pause: %e, " +
                                "vacation: %b, " +
                                "worktime: %e, " +
                                "valid: %b\n",
                        c.getOccupation(),
                        c.getDate(),
                        c.getStart(),
                        c.getEnd(),
                        c.getPause(),
                        c.isVacation(),
                        c.getWorkTime(),
                        c.isValid())
            );
        }

    }

    /**
     * called by the '+'-button,
     * adds a blank workslice to the workslice list
     */
    public void addWorkSlice() {
        try {
            model.addWorkSlice();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * called when user chooses to export the file to TeX format
     */
    public void exportToTeX() {
        System.out.println("exporting...");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindContent(workSliceBox.getChildren(), model.getWorkSliceList());
        model.subscribe(this);
        this.agreedWorktimeSpinner.setValueFactory(getNewSimpleSpinnerFactory());
        this.carryInSpinner.setValueFactory(getNewSimpleSpinnerFactory());
    }

    @Override
    public void update() {
        this.currentTotalWorkTime = model.getTotalWorkTime();
        this.currentWorktimeField.setText(model.formatTime(currentTotalWorkTime));
        this.currentPredTransfer = agreedWorktimeSpinner.getValue() - currentTotalWorkTime;
        if (currentPredTransfer < 0) currentPredTransfer = 0;
        this.carryOutField.setText(model.formatTime(this.currentPredTransfer));
    }

    private SpinnerValueFactory<Integer> getNewSimpleSpinnerFactory() {
        return new SpinnerValueFactory<>() {
            int value = 0;
            {
                setValue(value);
            }
            @Override
            public void decrement(int i) {
                if (value != 0) setValue(--value);
            }
            @Override
            public void increment(int i) {
                setValue(++value);
            }
        };
    }

    private TimeSheet constructTimeSheet() throws IllegalArgumentException {
        var employee = new Employee(nameField.getCharacters().toString(), Integer.parseInt(idField.getCharacters().toString()));

        var workingArea = WorkingArea.parse((ubCheck.isSelected() || gfCheck.isSelected()) ?
                ((ubCheck.isSelected()) ? "ub" : "gf") : "");

        var profession = new Profession(organisationField.getCharacters().toString(),
                workingArea,
                new TimeSpan(agreedWorktimeSpinner.getValue(), 0),
                ( (double) wageSpinner.getBalance()) / 100d);

        var predTransfer = new TimeSpan(carryInSpinner.getValue(), 0); //TODO: change spinnertype so that fractions can be carried in

        var succTransfer = new TimeSpan((int) Math.floor(currentPredTransfer), (int) ((currentPredTransfer % 1) * 60));

        return new TimeSheet(employee, profession, yearMonthPicker.getValue(), constructEntries(), succTransfer, predTransfer);
    }

    private Entry[] constructEntries() {
        //TODO: implement
        return null;
    }
}
