package controller;

import model.validation.Notification;
import service.Service;
import service.employee.EmployeeService;
import service.log.LogService;
import view.ManageEmployeesView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ManageEmployeesController  extends Controller{

    private static final String MANAGE_EMPLOYEES_VIEW = "manageEmployeesView";
    private static final String EMPLOYEE_SERVICE = "employeeService";
    private static final String LOG_SERVICE = "logService";

    private ManageEmployeesView manageEmployeesView;
    private EmployeeService employeeService;
    private LogService logService;

    public ManageEmployeesController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);
        try {
            manageEmployeesView = (ManageEmployeesView) viewMap.get(MANAGE_EMPLOYEES_VIEW);
            employeeService = (EmployeeService) serviceMap.get(EMPLOYEE_SERVICE);
            logService = (LogService) serviceMap.get(LOG_SERVICE);
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
            logService.saveLog("View employees");
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
                    logService.saveLog("Added new employee");
                }
            }
        }
    }

    private class UpdateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            long id;
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
                    logService.saveLog("Updated employee");
                }
            }
        }
    }

    private class DeleteActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            long id;
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
                    logService.saveLog("Deleted employee");
                }
            }
        }
    }
}
