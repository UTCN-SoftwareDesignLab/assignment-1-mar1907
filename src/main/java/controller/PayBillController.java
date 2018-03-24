package controller;

import service.Service;
import view.PayBillView;
import view.View;

import java.util.Map;

public class PayBillController extends Controller {

    private PayBillView payBillView;

    public PayBillController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try{
            payBillView = (PayBillView) viewMap.get("payBillView");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        payBillView.setVisible(true);
    }
}
