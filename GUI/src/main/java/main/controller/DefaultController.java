package main.controller;

import main.model.GUIModel;

/**
 * Default Controller
 *
 * @author Philipp Seidel
 * @version 0.1
 */
public class DefaultController implements GUIController {

    private final GUIModel model;

    public DefaultController(GUIModel model) {
        this.model = model;
    }
}
