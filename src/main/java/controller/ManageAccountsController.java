package controller;

import model.validation.Notification;
import service.Service;
import service.account.AccountService;
import view.ManageAccountsView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ManageAccountsController extends Controller {

    private ManageAccountsView manageAccountsView;
    private AccountService accountService;

    public ManageAccountsController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try{
            manageAccountsView = (ManageAccountsView) viewMap.get("manageAccountsView");
            accountService = (AccountService) serviceMap.get("accountService");
        } catch (Exception e){
            e.printStackTrace();
        }

        manageAccountsView.addViewAllActionListener(new ViewAllActionListener());
        manageAccountsView.addNewActionListener(new NewActionListener());
        manageAccountsView.addUpdateActionListener(new UpdateActionListener());
        manageAccountsView.addDeleteActionListener(new DeleteActionListener());
    }

    @Override
    public void display() {
        manageAccountsView.setVisible(true);
    }

    private class ViewAllActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = accountService.getAccountData();
            manageAccountsView.sendText(text);
        }
    }

    private class NewActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int amount = Integer.parseInt(manageAccountsView.getAAmount());
                long clientId = Long.parseLong(manageAccountsView.getClientId());
                boolean isSaving = manageAccountsView.isSaving();
                double interest = isSaving?Double.parseDouble(manageAccountsView.getInterest()):0;

                Notification<Boolean> registerNotification = accountService.insertAccount(amount,clientId,isSaving,interest);
                if (registerNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), registerNotification.getFormattedErrors());
                } else {
                    if (!registerNotification.getResult()) {
                        JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), "Could not add account");
                    } else {
                        JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), "Account added!");
                    }
                }
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), "Invalid numbers entered!");
            }
        }
    }

    private class UpdateActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                long id = Long.parseLong(manageAccountsView.getAId());
                int amount = Integer.parseInt(manageAccountsView.getAAmount());
                long clientId = Long.parseLong(manageAccountsView.getClientId());
                boolean isSaving = manageAccountsView.isSaving();
                double interest = isSaving?Double.parseDouble(manageAccountsView.getInterest()):0;

                Notification<Boolean> registerNotification = accountService.updateAccount(id,amount,clientId,isSaving,interest);
                if (registerNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), registerNotification.getFormattedErrors());
                } else {
                    if (!registerNotification.getResult()) {
                        JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), "Could not update account");
                    } else {
                        JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), "Account updated!");
                    }
                }
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), "Invalid numbers entered!");
            }
        }
    }

    private class DeleteActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                long id = Long.parseLong(manageAccountsView.getAId());

                Notification<Boolean> registerNotification = accountService.delete(id);
                if (registerNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), registerNotification.getFormattedErrors());
                } else {
                    if (!registerNotification.getResult()) {
                        JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), "Could not delete account");
                    } else {
                        JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), "Account deleted!");
                    }
                }
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(manageAccountsView.getContentPane(), "Invalid numbers entered!");
            }
        }
    }
}
