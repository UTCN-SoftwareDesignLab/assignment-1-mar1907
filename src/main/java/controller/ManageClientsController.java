package controller;

import model.validation.Notification;
import service.Service;
import service.client.ClientService;
import view.ManageClientsView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class ManageClientsController extends Controller {

    private ManageClientsView manageClientsView;
    private ClientService clientService;

    public ManageClientsController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try {
            this.manageClientsView = (ManageClientsView) viewMap.get("manageClientsView");
            this.clientService = (ClientService) serviceMap.get("clientService");
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
            Long id = Long.parseLong(manageClientsView.getCId());

            Notification<Boolean> registerNotification = clientService.updateClient(id,name,idCard,cnp);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(manageClientsView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(manageClientsView.getContentPane(), "Client update not successful");
                } else {
                    JOptionPane.showMessageDialog(manageClientsView.getContentPane(), "Client update successful!");
                }
            }
        }
    }
}
