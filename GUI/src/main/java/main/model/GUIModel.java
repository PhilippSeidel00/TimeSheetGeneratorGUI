package main.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import main.controller.workslicecontroller.WorksliceController;

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
public interface GUIModel {

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
     * get the used time format
     * @return the used time format
     */
    String getTimeFormat();

    /**
     * get the length a worktime string should be shortened to
     * @return the length
     */
    int getWorkTimeStringLength();
}
