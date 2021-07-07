import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;
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
    private static final Dimension DEFAULT_DIMENSION =
            new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
                          Toolkit.getDefaultToolkit().getScreenSize().height / 3);

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
        stage.setTitle(resourceBundle.getString("mainTitle"));
        stage.setResizable(false);
        stage.show();
    }
}
