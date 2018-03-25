package controller;

import service.Service;
import view.GenerateReportView;
import view.View;

import java.util.Map;

public class GenerateReportController extends Controller {

    private GenerateReportView generateReportView;

    public GenerateReportController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try{
            generateReportView = (GenerateReportView) viewMap.get("generateReportView");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        generateReportView.setVisible(true);
    }
}
