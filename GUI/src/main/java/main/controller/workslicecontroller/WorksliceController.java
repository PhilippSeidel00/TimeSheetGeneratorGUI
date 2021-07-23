package main.controller.workslicecontroller;

import main.controller.Controller;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Controls a javafx workslice
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public interface WorksliceController extends Controller {

    /**
     * get the date of the workslice
     * @return the date of the workslice
     */
    LocalDate getDate();

    /**
     * get start time of workslice
     * @return the start time of the workslice
     */
    LocalTime getStart();

    /**
     * get end time of workslice
     * @return the end time of the workslice
     */
    LocalTime getEnd();

    /**
     * get pause time of workslice
     * @return the pause time of the workslice
     */
    float getPause();

    /**
     * get occupation of workslice
     * @return the occupation of the workslice
     */
    String getOccupation();

    /**
     * indicates the vacation status of the workslice
     * @return the vacation status of the workslice
     */
    boolean isVacation();

    /**
     * get worktime of workslice in minutes
     * @return the work time of workslice
     */
    double getWorkTime();

    /**
     * returns the validity of the workslice
     * @return the validity of the workslice
     */
    boolean isValid();
}
