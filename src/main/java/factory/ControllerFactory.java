package factory;

import controller.*;
import service.Service;
import view.ManageEmployeesView;
import view.View;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private ServiceFactory serviceFactory;
    private ViewFactory viewFactory;

    private LoginController loginController;

    private static ControllerFactory instance;

    public static ControllerFactory instance(){
        if(instance==null){
            instance = new ControllerFactory();
        }

        return instance;
    }

    private ControllerFactory(){
        serviceFactory = ServiceFactory.instance();
        viewFactory = ViewFactory.instance();
        loginController = createLoginController();
    }

    private LoginController createLoginController(){
        Map<String, Service> serviceMap = new HashMap<>();
        serviceMap.put("authenticationService", serviceFactory.getAuthenticationService());
        serviceMap.put("optionsService", serviceFactory.getOptionsService());

        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("loginView", viewFactory.getLoginView());

        Map<String, Controller> controllerMap = new HashMap<>();
        controllerMap.put("optionsController", this.createOptionsController());

        return new LoginController(controllerMap, viewMap, serviceMap);
    }

    private OptionsController createOptionsController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("optionsView", viewFactory.getOptionsView());

        Map<String, Controller> controllerMap = new HashMap<>();
        controllerMap.put("manageClientsController", this.createManageClientsController());
        controllerMap.put("manageAccountsController", this.createManageAccountsController());
        controllerMap.put("payBillController", this.createPayBillController());
        controllerMap.put("transferController", this.createTransferController());
        controllerMap.put("manageEmployeesController", this.createManageEmployeesController());
        controllerMap.put("generateReportController", this.createGenerateReportController());

        return new OptionsController(controllerMap, viewMap, null);
    }

    private ManageClientsController createManageClientsController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("manageClientsView", viewFactory.getManageClientsView());

        Map<String, Service> serviceMap = new HashMap<>();
        serviceMap.put("clientService", serviceFactory.getClientService());

        return new ManageClientsController(null, viewMap, serviceMap);
    }

    private ManageAccountsController createManageAccountsController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("manageAccountsView", viewFactory.getManageAccountsView());

        Map<String, Service> serviceMap = new HashMap<>();
        serviceMap.put("accountService", serviceFactory.getAccountService());

        return new ManageAccountsController(null, viewMap, serviceMap);
    }

    private PayBillController createPayBillController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("payBillView", viewFactory.getPayBillView());

        Map<String, Service> serviceMap = new HashMap<>();
        //TODO add services

        return new PayBillController(null, viewMap, serviceMap);
    }

    private TransferController createTransferController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("transferView", viewFactory.getTransferView());

        Map<String, Service> serviceMap = new HashMap<>();
        //TODO add service

        return new TransferController(null, viewMap, serviceMap);
    }

    private ManageEmployeesController createManageEmployeesController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("manageEmployeesView", viewFactory.getManageEmployeesView());

        Map<String, Service> serviceMap = new HashMap<>();
        //TODO add service

        return new ManageEmployeesController(null, viewMap, serviceMap);
    }

    private GenerateReportController createGenerateReportController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("generateReportView", viewFactory.getGenerateReportView());

        Map<String, Service> serviceMap = new HashMap<>();
        //TODO add service

        return new GenerateReportController(null, viewMap, serviceMap);
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
