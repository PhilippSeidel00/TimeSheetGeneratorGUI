package main.controller.guicontroller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.model.GUIModel;
import se.alipsa.ymp.YearMonthPickerCombo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;


/**
 * Default Controller
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class DefaultGUIController implements GUIController {

    //number of months prior and after current month that are shown in the year/month picker
    private static final int MONTH_RANGE = 8;

    private final GUIModel model;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField organisationField;

    @FXML
    private TextField worktimeField;

    @FXML
    private TextField wageField;

    @FXML
    private TextField currentWorktimeField;

    @FXML
    private TextField carryInField;

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
    }

    /**
     * print all input data provided by the user
     */
    public void printAllData() {
        //print static userdata
        System.out.printf(
                "surname: %s, " +
                "name: %s, " +
                "id: %s, " +
                "organisation: %s, " +
                "workTime: %s, " +
                "wage: %e, " +
                "ub: %b, " +
                "gf: %b, " +
                        "carry in: %d, " +
                        "carry out: %d, " +
                "save: %b\n" +
                        "workslices:\n",
                surnameField.getCharacters().toString(),
                nameField.getCharacters().toString(),
                idField.getCharacters().toString(),
                organisationField.getCharacters().toString(),
                Integer.parseInt(worktimeField.getCharacters().toString().equals("") ?
                        "-1" : worktimeField.getCharacters().toString()),
                Float.parseFloat(wageField.getCharacters().toString().equals("") ?
                        "-1" : wageField.getCharacters().toString()),
                ubCheck.isSelected(),
                gfCheck.isSelected(),
                Integer.parseInt(carryInField.getCharacters().toString().equals("") ?
                        "-1" : carryInField.getCharacters().toString()),
                Integer.parseInt(carryOutField.getCharacters().toString().equals("") ?
                        "-1" : carryOutField.getCharacters().toString()),
                saveCheck.isSelected());
        //print data in workslices
        if (model.getWorkSliceControllers().isEmpty()) {
            System.out.println("no workslices found.");
        } else {
            model.getWorkSliceControllers().forEach(c -> {
                System.out.printf("occupation: %s, " +
                                "date: %s, " +
                                "start: %s, " +
                                "end: %s, " +
                                "pause: %e, " +
                                "vacation: %b, " +
                                "worktime: %e\n",
                        "test",
                        "test",
                        "test",
                        "test",
                        -1f,
                        false,
                        -1f);
            });
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bindings.bindContent(workSliceBox.getChildren(), model.getWorkSliceList());
    }
}
