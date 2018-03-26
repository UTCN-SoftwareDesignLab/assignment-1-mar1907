package controller;

import model.validation.Notification;
import service.Service;
import service.bill.BillService;
import service.log.LogService;
import view.PayBillView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class PayBillController extends Controller {

    private static final String PAY_BILL_VIEW = "payBillView";
    private static final String BILL_SERVICE = "billService";
    private static final String LOG_SERVICE = "logService";

    private PayBillView payBillView;
    private BillService billService;
    private LogService logService;

    public PayBillController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try{
            payBillView = (PayBillView) viewMap.get(PAY_BILL_VIEW);
            billService = (BillService) serviceMap.get(BILL_SERVICE);
            logService = (LogService) serviceMap.get(LOG_SERVICE);
        } catch (Exception e){
            e.printStackTrace();
        }

        payBillView.addPayBillActionListener(new PayBillActionListener());
    }

    @Override
    public void display() {
        payBillView.setVisible(true);
    }

    private class PayBillActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id;
            String text;
            int amount;

            try {
                id = Long.parseLong(payBillView.getAccountId());
                text = payBillView.getText();
                amount = Integer.parseInt(payBillView.getPAmount());
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(payBillView.getContentPane(), "Improper numbers entered!");
                return;
            }

            Notification<Boolean> registerNotification = billService.payBill(id,amount,text);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(payBillView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(payBillView.getContentPane(), "Payment not successful");
                } else {
                    JOptionPane.showMessageDialog(payBillView.getContentPane(), "Payment successful!");
                    logService.saveLog("Paid bill");
                }
            }
        }
    }
}
