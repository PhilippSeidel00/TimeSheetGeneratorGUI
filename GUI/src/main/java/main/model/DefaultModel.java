package main.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import main.controller.workslicecontroller.WorksliceController;
import main.controller.workslicecontroller.DefaultWorksliceController;
import main.observer.Observer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class DefaultModel implements GUIModel {

    private static final String WORKSLICE_FXML_LOCATION = "/fxml/workslice.fxml";
    private static final String RESOURCE_BUNDLE_BASE_NAME = "MessagesBundle";
    private static final String WORKTIME_FORMAT = "%.2f h"; //format for worktime strings

    //TODO: find better way of storing workslices
    private final Map<WorksliceController, Node> worksliceMap;
    private final ObservableList<Node> workslices;
    private final List<Observer> subscribers;


    private ResourceBundle resourceBundle;
    private double workTime = 0;


    public DefaultModel(Locale locale) throws FileNotFoundException {
        this.resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, locale);
        this.worksliceMap = new HashMap<>();
        this.workslices = FXCollections.observableArrayList();
        this.subscribers = new ArrayList<>();
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
        var controller = new DefaultWorksliceController(this);
        loader.setResources(resourceBundle);
        loader.setController(controller);
        Node workslice = loader.load();
        controller.initialize(null, null);
        worksliceMap.put(controller, workslice);
        workslices.add(workslice);
    }

    @Override
    public void removeWorkSlice(WorksliceController controller) {
        workslices.remove(worksliceMap.get(controller));
        worksliceMap.remove(controller);
    }

    @Override
    public ObservableList<Node> getWorkSliceList() {
        return workslices;
    }

    @Override
    public List<WorksliceController> getWorkSliceControllers() {
        return new ArrayList<>(this.worksliceMap.keySet());
    }

    @Override
    public double getTotalWorkTime() {
        return this.workTime;
    }

    @Override
    public void setTotalWorkTime(double totalWorkTime) {
        this.workTime = totalWorkTime;
        notifyObservers();
    }

    @Override
    public String formatTime(double time) {
        return String.format(WORKTIME_FORMAT, time);
    }

    @Override
    public void subscribe(Observer observer) {
        this.subscribers.add(observer);
    }

    private void notifyObservers() {
        this.subscribers.forEach(Observer::update);
    }
}
