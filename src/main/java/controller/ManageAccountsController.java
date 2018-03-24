package controller;

import service.Service;
import view.ManageAccountsView;
import view.View;

import java.util.Map;

public class ManageAccountsController extends Controller {

    private ManageAccountsView manageAccountsView;

    public ManageAccountsController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try{
            manageAccountsView = (ManageAccountsView) viewMap.get("manageAccountsView");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        manageAccountsView.setVisible(true);
    }
}
