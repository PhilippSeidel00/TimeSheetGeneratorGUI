package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main Application class. Entry point for GUI.
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class GUI extends Application {

    private static ResourceBundle resourceBundle;
    private static Locale currentLocale;

    private static final String DEFAULT_LANGUAGE = "en";
    private static final String DEFAULT_COUNTRY = "US";
    private static final String RESOURCE_BUNDLE_BASE_NAME = "MessagesBundle";
    private static final String MAIN_FXML_LOCATION = "/fxml/GUI.fxml";
    private static final String ICON_LOCATION = "/icon/gui_icon.png";

    public static void main(String[] args) {
        String language = DEFAULT_LANGUAGE;
        String country = DEFAULT_COUNTRY;
        //read language and country code if provided otherwise use default
        if (args.length >= 2) {
            language = args[0];
            country = args[1];
        }
        currentLocale = new Locale(language, country);
        resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, currentLocale);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        initStage(stage);
        stage.setScene(new Scene(loadRoot()));
        stage.show();
    }

    private Parent loadRoot() throws IOException {
        URL url = getClass().getResource(MAIN_FXML_LOCATION);
        if (url == null) throw new FileNotFoundException();
        return FXMLLoader.load(url, resourceBundle);
    }

    private void initStage(Stage stage) {
        stage.setTitle(resourceBundle.getString("mainTitle"));
        stage.setResizable(false);
        stage.getIcons().add(new Image(ICON_LOCATION));
    }
}
