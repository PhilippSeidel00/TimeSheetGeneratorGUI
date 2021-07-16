package main.controller.guicontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.model.GUIModel;
import se.alipsa.ymp.YearMonthPickerCombo;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Default Controller
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class DefaultGUIController implements GUIController {

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

    @FXML
    private VBox worksliceBox;

    public DefaultGUIController(GUIModel model) {
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
                Integer.parseInt(worktimeField.getCharacters().toString().equals("") ?
                        "-1" : worktimeField.getCharacters().toString()),
                Float.parseFloat(wageField.getCharacters().toString().equals("") ?
                        "-1" : wageField.getCharacters().toString()),
                ubCheck.isSelected(),
                gfCheck.isSelected(),
                saveCheck.isSelected());
    }
    //for testing only (playground)
    public void test() throws FileNotFoundException, IOException {
        var url = getClass().getResource("/fxml/workslice.fxml");
        if (url == null) throw new FileNotFoundException();
        FXMLLoader loader = new FXMLLoader(url);
        loader.setResources(model.getResourceBundle());
        loader.setController(new Object());
        worksliceBox.getChildren().add(loader.load());
    }
}
