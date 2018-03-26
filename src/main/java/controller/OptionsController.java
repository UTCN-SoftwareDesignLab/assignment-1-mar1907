package controller;

import service.Service;
import view.OptionsView;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class OptionsController extends Controller{

    private static final String OPTIONS_VIEW = "optionsView";
    private static final String MANAGE_CLIENTS_CONTROLLER = "manageClientsController";
    private static final String MANAGE_ACCOUNTS_CONTROLLER = "manageAccountsController";
    private static final String PAY_BILL_CONTROLLER = "payBillController";
    private static final String TRANSFER_CONTROLLER = "transferController";
    private static final String MANAGE_EMPLOYEES_CONTROLLER = "manageEmployeesController";
    private static final String GENERATE_REPORT_CONTROLLER = "generateReportController";

    private OptionsView optionsView;
    private ManageClientsController manageClientsController;
    private ManageAccountsController manageAccountsController;
    private PayBillController payBillController;
    private TransferController transferController;
    private ManageEmployeesController manageEmployeesController;
    private GenerateReportController generateReportController;

    public OptionsController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);
        try {
            this.optionsView = (OptionsView) viewMap.get(OPTIONS_VIEW);
            this.manageClientsController = (ManageClientsController) controllerMap.get(MANAGE_CLIENTS_CONTROLLER);
            this.manageAccountsController = (ManageAccountsController) controllerMap.get(MANAGE_ACCOUNTS_CONTROLLER);
            this.payBillController = (PayBillController) controllerMap.get(PAY_BILL_CONTROLLER);
            this.transferController = (TransferController) controllerMap.get(TRANSFER_CONTROLLER);
            this.manageEmployeesController = (ManageEmployeesController) controllerMap.get(MANAGE_EMPLOYEES_CONTROLLER);
            this.generateReportController = (GenerateReportController) controllerMap.get(GENERATE_REPORT_CONTROLLER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public OptionsController(OptionsView optionsView) {
        this.optionsView = optionsView;
        optionsView.setManageClientsButtonListener(new ManageClientsButtonListener());
        optionsView.setManageAccountsButtonListener(new ManageAccountsButtonListener());
        optionsView.setTransferMoneyButtonListener(new TransferMoneyButtonListener());
        optionsView.setPayBillButtonListener(new PayBillButtonListener());
        optionsView.setManageEmployeesButtonListener(new ManageEmployeesButtonListener());
        optionsView.setGenerateActivityReportButtonListener(new GenerateActivityReportButtonListener());
    }*/

    @Override
    public void display() {
        optionsView.setVisible(true);
    }

    public void setButtonDisplayList(List<Boolean> bdl){
        optionsView.setButtons(bdl);
        optionsView.setManageClientsButtonListener(new ManageClientsButtonListener());
        optionsView.setManageAccountsButtonListener(new ManageAccountsButtonListener());
        optionsView.setTransferMoneyButtonListener(new TransferMoneyButtonListener());
        optionsView.setPayBillButtonListener(new PayBillButtonListener());
        optionsView.setManageEmployeesButtonListener(new ManageEmployeesButtonListener());
        optionsView.setGenerateActivityReportButtonListener(new GenerateActivityReportButtonListener());
    }

    private class ManageClientsButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            manageClientsController.display();
        }
    }

    private class ManageAccountsButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            manageAccountsController.display();
        }
    }

    private class TransferMoneyButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            transferController.display();
        }
    }

    private class PayBillButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            payBillController.display();
        }
    }

    private class ManageEmployeesButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            manageEmployeesController.display();
        }
    }

    private class GenerateActivityReportButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            generateReportController.display();
        }
    }
}
