package controller;

import service.Service;
import view.ManageEmployeesView;
import view.View;

import java.util.Map;

public class ManageEmployeesController  extends Controller{

    private ManageEmployeesView manageEmployeesView;

    public ManageEmployeesController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);
        try {
            manageEmployeesView = (ManageEmployeesView) viewMap.get("manageEmployeesView");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        manageEmployeesView.setVisible(true);
    }
}
