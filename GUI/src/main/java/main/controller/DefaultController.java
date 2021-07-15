package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.model.GUIModel;
import se.alipsa.ymp.YearMonthPicker;
import se.alipsa.ymp.YearMonthPickerCombo;


/**
 * Default Controller
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class DefaultController implements GUIController {

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
    private YearMonthPickerCombo yearMonthPicker;

    @FXML
    private CheckBox ubCheck;

    @FXML
    private CheckBox gfCheck;

    @FXML
    private CheckBox saveCheck;


    public DefaultController(GUIModel model) {
        this.model = model;
    }

    public void printAllData() {
        System.out.printf(
                "surname: %s, " +
                "name: %s, " +
                "id: %s, " +
                "organisation: %s, " +
                "workTime: %s, " +
                "wage: %e, " +
                "ub: %b, " +
                "gf: %b, " +
                "save: %b\n",
                surnameField.getCharacters().toString(),
                nameField.getCharacters().toString(),
                idField.getCharacters().toString(),
                organisationField.getCharacters().toString(),
                Integer.parseInt(worktimeField.getCharacters().toString()),
                Float.parseFloat(wageField.getCharacters().toString()),
                ubCheck.isSelected(),
                gfCheck.isSelected(),
                saveCheck.isSelected());
    }
}
