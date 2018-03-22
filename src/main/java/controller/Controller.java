package controller;

import service.Service;
import view.View;

import java.util.Map;

public abstract class Controller {

    private final Map<String, Controller> controllerMap;
    private final Map<String, View> viewMap;
    private final Map<String, Service> serviceMap;

    public Controller(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        this.controllerMap = controllerMap;
        this.viewMap = viewMap;
        this.serviceMap = serviceMap;
    }

    public abstract void display();
}
