package main.model;

import java.util.Locale;
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
     * set current {@link java.util.Locale}
     *
     * @param locale new current locale
     */
    void setLocale(Locale locale);

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
}
