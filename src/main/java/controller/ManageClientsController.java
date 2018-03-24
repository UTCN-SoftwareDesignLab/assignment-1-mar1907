package controller;

import service.Service;
import view.ManageClientsView;
import view.View;

import java.util.Map;

public class ManageClientsController extends Controller {

    private ManageClientsView manageClientsView;

    public ManageClientsController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try {
            this.manageClientsView = (ManageClientsView) viewMap.get("manageClientsView");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        manageClientsView.setVisible(true);
    }
}
