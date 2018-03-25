package controller;

import model.Employee;
import model.validation.Notification;
import repository.employee.AuthenticationException;
import service.Service;
import service.employee.AuthenticationService;
import service.options.OptionsService;
import view.LoginView;
import view.OptionsView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class LoginController extends Controller{
    private LoginView loginView;
    private AuthenticationService authenticationService;
    private OptionsService optionsService;
    private OptionsController optionsController;

    public LoginController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);
        try{
            this.loginView = (LoginView) viewMap.get("loginView");
            this.optionsService = (OptionsService) serviceMap.get("optionsService");
            this.authenticationService = (AuthenticationService) serviceMap.get("authenticationService");
            this.optionsController = (OptionsController) controllerMap.get("optionsController");
        } catch (Exception e){
            e.printStackTrace();
        }

        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
    }

    @Override
    public void display() {
        loginView.setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Employee> loginNotification = null;
            try {
                loginNotification = authenticationService.login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {
                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    //JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
                    loginView.dispose();
                    optionsController.setButtonDisplayList(optionsService.getOptions(loginNotification.getResult()));
                    optionsController.display();
                }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                }
            }
        }
    }


}
