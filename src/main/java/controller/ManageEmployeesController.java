package controller;

import model.validation.Notification;
import service.Service;
import service.employee.EmployeeService;
import view.ManageEmployeesView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ManageEmployeesController  extends Controller{

    private ManageEmployeesView manageEmployeesView;
    private EmployeeService employeeService;

    public ManageEmployeesController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);
        try {
            manageEmployeesView = (ManageEmployeesView) viewMap.get("manageEmployeesView");
            employeeService = (EmployeeService) serviceMap.get("employeeService");
        } catch (Exception e){
            e.printStackTrace();
        }

        manageEmployeesView.addViewAllActionListene(new ViewAllActionListener());
        manageEmployeesView.addCreateNewActionListener(new CreateNewActionListener());
        manageEmployeesView.addUpdateActionListener(new UpdateActionListener());
        manageEmployeesView.addDeleteActionListener(new DeleteActionListener());
    }

    @Override
    public void display() {
        manageEmployeesView.setVisible(true);
    }

    private class ViewAllActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            manageEmployeesView.sendData(employeeService.getEmployeeData());
        }
    }

    private class CreateNewActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = manageEmployeesView.getEName();
            String password = manageEmployeesView.getEPass();
            boolean admin = manageEmployeesView.isAdmin();

            Notification<Boolean> registerNotification = employeeService.addEmployee(name,password,admin);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), "Employee addition not successful");
                } else {
                    JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), "Employee addition successful!");
                }
            }
        }
    }

    private class UpdateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            long id = 0;
            try{
                id = Long.parseLong(manageEmployeesView.getID());
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), "Improper numbers entered");
                return;
            }
            String name = manageEmployeesView.getEName();
            String password = manageEmployeesView.getEPass();
            boolean admin = manageEmployeesView.isAdmin();

            Notification<Boolean> registerNotification = employeeService.updateEmployee(id,name,password,admin);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), "Employee update not successful");
                } else {
                    JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), "Employee update successful!");
                }
            }
        }
    }

    private class DeleteActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            long id = 0;
            try{
                id = Long.parseLong(manageEmployeesView.getID());
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), "Improper numbers entered");
                return;
            }

            Notification<Boolean> registerNotification = employeeService.delete(id);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), "Employee delete not successful");
                } else {
                    JOptionPane.showMessageDialog(manageEmployeesView.getContentPane(), "Employee delete successful!");
                }
            }
        }
    }
}
