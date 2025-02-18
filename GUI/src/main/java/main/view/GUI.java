package main.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import main.controller.guicontroller.DefaultGUIController;
import main.controller.guicontroller.GUIController;
import main.model.DefaultModel;
import main.model.GUIModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Entry point for GUI.
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class GUI extends Application {
    private static final String DEFAULT_LANGUAGE = "en";
    private static final String DEFAULT_COUNTRY = "US";
    private static final String MAIN_FXML_LOCATION = "/fxml/GUI.fxml";
    private static final String MAIN_CSS_LOCATION = "/css/styles.css";
    private static final String ICON_LOCATION = "/icon/gui_icon.png";

    private static GUIModel model;
    private static GUIController controller;
    private static FXMLLoader loader;

    public static void main(String[] args) {
        String language = DEFAULT_LANGUAGE;
        String country = DEFAULT_COUNTRY;
        //read language and country code if provided otherwise use default
        if (args.length >= 2) {
            language = args[0];
            country = args[1];
        }
        Locale startLocale = new Locale(language, country);

        try {
            model = new DefaultModel(startLocale);
            controller = new DefaultGUIController(model);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        initStage(stage);
        initLoader();
        JMetro jMetro = new JMetro(Style.LIGHT);
        Parent root = loader.load();
        controller.initialize(null, null);
        Scene scene = new Scene(root);
        styleScene(scene);
        jMetro.setParent(root);
        stage.setScene(scene);
        jMetro.setScene(scene);
        stage.show();
    }

    private void initLoader() throws IOException {
        var url = getClass().getResource(MAIN_FXML_LOCATION);
        if (url == null) throw new FileNotFoundException();
        loader = new FXMLLoader(url);
        loader.setResources(model.getResourceBundle());
        loader.setController(controller);
    }

    private void initStage(Stage stage) {
        stage.setTitle(model.getResourceBundle().getString("mainTitle"));
        stage.setResizable(false);
        stage.getIcons().add(new Image(ICON_LOCATION));
    }


    private void styleScene(Scene scene) throws FileNotFoundException {
        var url = getClass().getResource(MAIN_CSS_LOCATION);
        if (url == null) throw new FileNotFoundException();
        var css = url.toExternalForm();
        scene.getStylesheets().add(css);
    }

}
