package controller;

import model.validation.Notification;
import service.Service;
import service.log.LogService;
import view.GenerateReportView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GenerateReportController extends Controller {

    private static final String GENERATE_REPORT_VIEW = "generateReportView";
    private static final String LOG_SERVICE = "logService";

    private GenerateReportView generateReportView;
    private LogService logService;

    public GenerateReportController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);

        try{
            generateReportView = (GenerateReportView) viewMap.get(GENERATE_REPORT_VIEW);
            logService = (LogService) serviceMap.get(LOG_SERVICE);
        } catch (Exception e){
            e.printStackTrace();
        }
        generateReportView.addGenereateReportActionListener(new GenerateReportActionListener());
    }

    @Override
    public void display() {
        generateReportView.setVisible(true);
    }

    private class GenerateReportActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                long id = Long.parseLong(generateReportView.getCId());
                String from = generateReportView.getFrom();
                String to = generateReportView.getTo();

                Notification<String> registerNotification = logService.getLogs(id,from,to);
                if (registerNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(generateReportView.getContentPane(), registerNotification.getFormattedErrors());
                } else {
                    generateReportView.sendData(registerNotification.getResult());
                    logService.saveLog("Viewed log");
                }
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(generateReportView.getContentPane(), "Improper numbers entered!");
            }
        }
    }
}
