package controller;

import service.Service;
import view.OptionsView;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class OptionsController extends Controller{

    private OptionsView optionsView;

    public OptionsController(Map<String, Controller> controllerMap, Map<String, View> viewMap, Map<String, Service> serviceMap) {
        super(controllerMap, viewMap, serviceMap);
        try {
            this.optionsView = (OptionsView) viewMap.get("optionsView");
        } catch (Exception e) {
            e.printStackTrace();
        }

        optionsView.setManageClientsButtonListener(new ManageClientsButtonListener());
        optionsView.setManageAccountsButtonListener(new ManageAccountsButtonListener());
        optionsView.setTransferMoneyButtonListener(new TransferMoneyButtonListener());
        optionsView.setPayBillButtonListener(new PayBillButtonListener());
        optionsView.setManageEmployeesButtonListener(new ManageEmployeesButtonListener());
        optionsView.setGenerateActivityReportButtonListener(new GenerateActivityReportButtonListener());
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
    }

    private class ManageClientsButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ManageAccountsButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

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
