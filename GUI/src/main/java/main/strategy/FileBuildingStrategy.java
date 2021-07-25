package main.strategy;

import data.TimeSheet;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

/**
 * Strategy to build a file from a provided {@link TimeSheet}, as known from the Strategy Pattern
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public interface FileBuildingStrategy {

    /**
     * build a file from a given {@link TimeSheet}
     * @param timeSheet the timesheet providing the data for the file
     * @return the build file
     */
    File buildFile(TimeSheet timeSheet) throws IOException;
}
