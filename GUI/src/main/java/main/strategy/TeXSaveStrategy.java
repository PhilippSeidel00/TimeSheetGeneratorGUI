package main.strategy;

import data.TimeSheet;
import io.FileController;
import io.LatexGenerator;
import javafx.stage.FileChooser;
import main.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * builds a TeX file from a given {@link TimeSheet}
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class TeXSaveStrategy extends FileSaveStrategy {

    private static final String EXTENTIONFILTER_ARG1 = "TEX files (*.tex)";
    private static final String EXTENTIONFILTER_ARG2 = "*.tex";

    @Override
    byte[] getFileContent(TimeSheet timeSheet) throws IOException {
        var classLoader = Main.class.getClassLoader();
        var latexTemplate = FileController.readInputStreamToString(classLoader.getResourceAsStream("MiLoG_Template.tex"));
        var generator = new LatexGenerator(timeSheet, latexTemplate);
        return generator.generate().getBytes();
    }

    @Override
    FileChooser.ExtensionFilter getExtensionFiler() {
        return new FileChooser.ExtensionFilter(EXTENTIONFILTER_ARG1, EXTENTIONFILTER_ARG2);
    }
}
