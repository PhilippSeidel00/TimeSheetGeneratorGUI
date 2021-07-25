package main.strategy;

import data.TimeSheet;
import io.FileController;
import io.LatexGenerator;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * abstract file saving strategy, saves a file of a undefined type
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public abstract class FileSaveStrategy implements FileBuildingStrategy {


    @Override
    public File buildFile(TimeSheet timeSheet) throws IOException {
        var file = getFile();
        if (file == null) throw new IOException("please provide a valid file");
        Files.write(Paths.get(file.getPath()), getFileContent(timeSheet));
        return file;
    }

    protected File getFile() {
        var fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(getExtensionFiler());
        return fileChooser.showSaveDialog(new Stage());
    }

    abstract byte[] getFileContent(TimeSheet timeSheet) throws IOException;

    abstract FileChooser.ExtensionFilter getExtensionFiler();
}
