package controller;

import model.validation.Notification;
import service.Service;
import service.client.ClientService;
import service.log.LogService;
import view.ManageClientsView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ManageClientsController extends Controller {

    private static final String MANAGE_CLIENTS_VIEW = "manageClientsView";
    private static final String CLIENT_SERVICE = "clientService";
    private static final String LOG_SERVICE = "logService";

    private ManageClientsView manageClientsView;
    private ClientService clientService;
    private LogService logService;

    public ManageClientsController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try {
            this.manageClientsView = (ManageClientsView) viewMap.get(MANAGE_CLIENTS_VIEW);
            this.clientService = (ClientService) serviceMap.get(CLIENT_SERVICE);
            this.logService = (LogService) serviceMap.get(LOG_SERVICE);
        } catch (Exception e){
            e.printStackTrace();
        }

        this.manageClientsView.addViewClientsActionListener(new ViewClientsActionListener());
        this.manageClientsView.addNewClientActionListener(new NewClientActionListener());
        this.manageClientsView.addUpdateClientActionListener(new UpdateClientActionListener());
    }

    @Override
    public void display() {
        manageClientsView.setVisible(true);
    }

    public class ViewClientsActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String clientsData = clientService.getClientsData();
            manageClientsView.sendClientsData(clientsData);
            logService.saveLog("View clients");
        }
    }

    public class NewClientActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = manageClientsView.getCName();
            String idCard = manageClientsView.getIdCard();
            String cnp = manageClientsView.getCNP();

            Notification<Boolean> registerNotification = clientService.insertClient(name,idCard,cnp);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(manageClientsView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(manageClientsView.getContentPane(), "Client addition not successful");
                } else {
                    JOptionPane.showMessageDialog(manageClientsView.getContentPane(), "Client addition successful!");
                    logService.saveLog("Added new client");
                }
            }
        }
    }

    public class UpdateClientActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = manageClientsView.getCName();
            String idCard = manageClientsView.getIdCard();
            String cnp = manageClientsView.getCNP();
            Long id;
            try {
                id = Long.parseLong(manageClientsView.getCId());
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(manageClientsView.getContentPane(), "Improper number entered");
                return;
            }

            Notification<Boolean> registerNotification = clientService.updateClient(id,name,idCard,cnp);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(manageClientsView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(manageClientsView.getContentPane(), "Client update not successful");
                } else {
                    JOptionPane.showMessageDialog(manageClientsView.getContentPane(), "Client update successful!");
                    logService.saveLog("Updated client");
                }
            }
        }
    }
}
