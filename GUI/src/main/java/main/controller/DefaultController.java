package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.model.GUIModel;


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
    private DatePicker monthYearPicker;

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
                "workTime: %d, " +
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
