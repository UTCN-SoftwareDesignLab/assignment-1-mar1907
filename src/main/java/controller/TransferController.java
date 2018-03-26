package controller;

import model.validation.Notification;
import service.Service;
import service.log.LogService;
import service.transfer.TransferService;
import view.TransferView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class TransferController extends Controller {

    private static final String TRANSFER_VIEW = "transferView";
    private static final String TRANSFER_SERVICE = "transferService";
    private static final String LOG_SERVICE = "logService";

    private TransferView transferView;
    private TransferService transferService;
    private LogService logService;

    public TransferController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try{
            this.transferView = (TransferView) viewMap.get(TRANSFER_VIEW);
            this.transferService = (TransferService) serviceMap.get(TRANSFER_SERVICE);
            this.logService = (LogService) serviceMap.get(LOG_SERVICE);
        } catch (Exception e){
            e.printStackTrace();
        }

        transferView.addTransferActionListener(new TransferActionListener());
    }

    @Override
    public void display() {
        transferView.setVisible(true);
    }

    private class TransferActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            Long id1, id2;
            int amount;

            try {
                id1 = Long.parseLong(transferView.getAccount1id());
                id2 = Long.parseLong(transferView.getAccount2id());
                amount = Integer.parseInt(transferView.getTAmount());
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(transferView.getContentPane(), "Improper numbers entered!");
                return;
            }

            Notification<Boolean> registerNotification = transferService.transfer(id1,id2,amount);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(transferView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(transferView.getContentPane(), "Transfer not successful");
                } else {
                    JOptionPane.showMessageDialog(transferView.getContentPane(), "Transfer successful!");
                    logService.saveLog("Transfer money");
                }
            }
        }
    }
}
