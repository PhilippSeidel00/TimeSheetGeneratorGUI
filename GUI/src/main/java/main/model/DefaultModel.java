package main.model;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import main.controller.componentcontroller.ComponentController;
import main.controller.componentcontroller.WorksliceController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class DefaultModel implements GUIModel {

    private static final String WORKSLICE_FXML_LOCATION = "/fxml/workslice.fxml";
    private static final String RESOURCE_BUNDLE_BASE_NAME = "MessagesBundle";

    //TODO: find better way of storing workslices
    private final Map<ComponentController, Node> worksliceMap;
    private final ObservableList<Node> workslices;
    private ResourceBundle resourceBundle;


    public DefaultModel(Locale locale) throws FileNotFoundException {
        this.resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, locale);
        this.worksliceMap = new HashMap<>();
        this.workslices = FXCollections.observableArrayList();
    }

    @Override
    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    @Override
    public void addWorkSlice() throws IOException {
        var url = getClass().getResource(WORKSLICE_FXML_LOCATION);
        if (url == null) throw new FileNotFoundException();
        var loader = new FXMLLoader(url);
        var controller = new WorksliceController(this);
        loader.setResources(resourceBundle);
        loader.setController(controller);
        Node workslice = loader.load();
        worksliceMap.put(controller, workslice);
        workslices.add(workslice);
    }

    @Override
    public void removeWorkSlice(ComponentController controller) {
        workslices.remove(worksliceMap.get(controller));
        worksliceMap.remove(controller);
    }

    @Override
    public ObservableList<Node> getWorkSliceList() {
        return workslices;
    }

    @Override
    public List<ComponentController> getWorkSliceControllers() {
        return new ArrayList<>(this.worksliceMap.keySet());
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        workslices.addListener(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        workslices.removeListener(invalidationListener);
    }
}
