package main.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import main.controller.workslicecontroller.WorksliceController;
import main.observer.Subject;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Model in the MVC Pattern
 * Holds all data used in GUI application
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public interface GUIModel extends Subject {

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
     * Remove workslice with given {@link WorksliceController}
     * @param controller the given controller
     */
    void removeWorkSlice(WorksliceController controller);

    /**
     * get observable workslice list
     * @return the observable workslice list
     */
    ObservableList<Node> getWorkSliceList();

    /**
     * get List of workslice controllers
     * @return the list of workslice controllers
     */
    List<WorksliceController> getWorkSliceControllers();

    /**
     * get the total worktime of all workslices
     * @return the total worktime of all workslices
     */
    double getTotalWorkTime();

    /**
     * set the total worktime of all workslices
     */
    void setTotalWorkTime(double totalWorkTime);

    /**
     * format given double number of minutes into usable String format
     * @param time the time value that is to be formatted
     * @return the usable String format
     */
    String formatTime(double time);
}
