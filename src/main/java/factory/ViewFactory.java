package factory;

import view.*;

public class ViewFactory {

    private static ViewFactory instance;

    private LoginView loginView;
    private OptionsView optionsView;
    private ManageClientsView manageClientsView;
    private ManageAccountsView manageAccountsView;
    private PayBillView payBillView;

    public static ViewFactory instance(){
        if(instance==null){
            instance = new ViewFactory();
        }

        return instance;
    }

    private ViewFactory(){
        loginView = new LoginView();
        optionsView = new OptionsView();
        manageClientsView = new ManageClientsView();
        manageAccountsView = new ManageAccountsView();
        payBillView = new PayBillView();
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public OptionsView getOptionsView() {
        return optionsView;
    }

    public ManageClientsView getManageClientsView() {
        return manageClientsView;
    }

    public ManageAccountsView getManageAccountsView() {
        return manageAccountsView;
    }

    public PayBillView getPayBillView() {
        return payBillView;
    }
}
