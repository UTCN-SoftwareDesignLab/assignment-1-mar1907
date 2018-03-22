package controller;

import view.OptionsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsController {

    private OptionsView optionsView;

    public OptionsController(OptionsView optionsView) {
        this.optionsView = optionsView;
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
