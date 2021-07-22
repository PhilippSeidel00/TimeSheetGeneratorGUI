package main.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import main.controller.componentcontroller.ComponentController;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Model in the MVC Pattern
 * Holds all data used in GUI application
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public interface GUIModel extends Observable {

    /**
     * set current {@link java.util.ResourceBundle}
     * @param resourceBundle new resourceBundle
     */
    void setResourceBundle(ResourceBundle resourceBundle);

    /**
     * get current {@link ResourceBundle}
     * @return current resourceBundle
     */
    ResourceBundle getResourceBundle();

    /**
     * Add new blank workslice to list of known workslices
     */
    void addWorkSlice() throws IOException;

    /**
     * Remove workslice with given {@link ComponentController}
     * @param controller the given controller
     */
    void removeWorkSlice(ComponentController controller);

    /**
     * get observable workslice list
     * @return the observable workslice list
     */
    ObservableList<Node> getWorkSliceList();

    /**
     * get List of workslice controllers
     * @return the list of workslice controllers
     */
    List<ComponentController> getWorkSliceControllers();
}
