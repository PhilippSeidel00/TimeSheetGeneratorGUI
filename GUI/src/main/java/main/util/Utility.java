package main.util;

import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Holds some utility methods
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public abstract class Utility {

    public static FXMLLoader buildLoader(String fxmlLocation, ResourceBundle resourceBundle, Object controller)
            throws MalformedURLException {
        var url = new File(fxmlLocation).toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        loader.setResources(resourceBundle);
        loader.setController(controller);
        return loader;
    }
}
