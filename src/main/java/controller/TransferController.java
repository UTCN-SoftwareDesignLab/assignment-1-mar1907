package controller;

import service.Service;
import view.TransferView;
import view.View;

import java.util.Map;

public class TransferController extends Controller {

    private TransferView transferView;

    public TransferController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try{
            this.transferView = (TransferView) viewMap.get("transferView");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        transferView.setVisible(true);
    }
}
