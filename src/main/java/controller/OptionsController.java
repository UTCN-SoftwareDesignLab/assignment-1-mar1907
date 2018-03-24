package controller;

import service.Service;
import view.OptionsView;
import view.PayBillView;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class OptionsController extends Controller{

    private OptionsView optionsView;
    private ManageClientsController manageClientsController;
    private ManageAccountsController manageAccountsController;
    private PayBillController payBillController;

    public OptionsController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);
        try {
            this.optionsView = (OptionsView) viewMap.get("optionsView");
            this.manageClientsController = (ManageClientsController) controllerMap.get("manageClientsController");
            this.manageAccountsController = (ManageAccountsController) controllerMap.get("manageAccountsController");
            this.payBillController = (PayBillController) controllerMap.get("payBillController");
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

        }
    }

    private class GenerateActivityReportButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
