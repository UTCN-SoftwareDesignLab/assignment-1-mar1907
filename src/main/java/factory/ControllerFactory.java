package factory;

import controller.*;
import service.Service;
import view.View;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private static final String AUTHENTICATION_SERVICE = "authenticationService";
    private static final String OPTIONS_SERVICE = "optionsService";
    private static final String LOG_SERVICE = "logService";
    private static final String LOGIN_VIEW = "loginView";
    private static final String OPTIONS_CONTROLLER = "optionsController";
    private static final String OPTIONS_VIEW = "optionsView";
    private static final String MANAGE_CLIENTS_CONTROLLER = "manageClientsController";
    private static final String MANAGE_ACCOUNTS_CONTROLLER = "manageAccountsController";
    private static final String PAY_BILL_CONTROLLER = "payBillController";
    private static final String TRANSFER_CONTROLLER = "transferController";
    private static final String MANAGE_EMPLOYEES_CONTROLLER = "manageEmployeesController";
    private static final String GENERATE_REPORT_CONTROLLER = "generateReportController";
    private static final String MANAGE_CLIENTS_VIEW = "manageClientsView";
    private static final String CLIENT_SERVICE = "clientService";
    private static final String MANAGE_ACCOUNTS_VIEW = "manageAccountsView";
    private static final String ACCOUNT_SERVICE = "accountService";
    private static final String PAY_BILL_VIEW = "payBillView";
    private static final String BILL_SERVICE = "billService";
    private static final String TRANSFER_VIEW = "transferView";
    private static final String TRANSFER_SERVICE = "transferService";
    private static final String MANAGE_EMPLOYEES_VIEW = "manageEmployeesView";
    private static final String EMPLOYEE_SERVICE = "employeeService";
    private static final String GENERATE_REPORT_VIEW = "generateReportView";

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
        serviceMap.put(AUTHENTICATION_SERVICE, serviceFactory.getAuthenticationService());
        serviceMap.put(OPTIONS_SERVICE, serviceFactory.getOptionsService());
        serviceMap.put(LOG_SERVICE, serviceFactory.getLogService());

        Map<String, View> viewMap = new HashMap<>();
        viewMap.put(LOGIN_VIEW, viewFactory.getLoginView());

        Map<String, Controller> controllerMap = new HashMap<>();
        controllerMap.put(OPTIONS_CONTROLLER, this.createOptionsController());

        return new LoginController(controllerMap, viewMap, serviceMap);
    }

    private OptionsController createOptionsController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put(OPTIONS_VIEW, viewFactory.getOptionsView());

        Map<String, Controller> controllerMap = new HashMap<>();
        controllerMap.put(MANAGE_CLIENTS_CONTROLLER, this.createManageClientsController());
        controllerMap.put(MANAGE_ACCOUNTS_CONTROLLER, this.createManageAccountsController());
        controllerMap.put(PAY_BILL_CONTROLLER, this.createPayBillController());
        controllerMap.put(TRANSFER_CONTROLLER, this.createTransferController());
        controllerMap.put(MANAGE_EMPLOYEES_CONTROLLER, this.createManageEmployeesController());
        controllerMap.put(GENERATE_REPORT_CONTROLLER, this.createGenerateReportController());

        return new OptionsController(controllerMap, viewMap, null);
    }

    private ManageClientsController createManageClientsController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put(MANAGE_CLIENTS_VIEW, viewFactory.getManageClientsView());

        Map<String, Service> serviceMap = new HashMap<>();
        serviceMap.put(CLIENT_SERVICE, serviceFactory.getClientService());
        serviceMap.put(LOG_SERVICE, serviceFactory.getLogService());

        return new ManageClientsController(null, viewMap, serviceMap);
    }

    private ManageAccountsController createManageAccountsController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put(MANAGE_ACCOUNTS_VIEW, viewFactory.getManageAccountsView());

        Map<String, Service> serviceMap = new HashMap<>();
        serviceMap.put(ACCOUNT_SERVICE, serviceFactory.getAccountService());
        serviceMap.put(LOG_SERVICE, serviceFactory.getLogService());

        return new ManageAccountsController(null, viewMap, serviceMap);
    }

    private PayBillController createPayBillController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put(PAY_BILL_VIEW, viewFactory.getPayBillView());

        Map<String, Service> serviceMap = new HashMap<>();
        serviceMap.put(BILL_SERVICE,serviceFactory.getBillService());
        serviceMap.put(LOG_SERVICE, serviceFactory.getLogService());

        return new PayBillController(null, viewMap, serviceMap);
    }

    private TransferController createTransferController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put(TRANSFER_VIEW, viewFactory.getTransferView());

        Map<String, Service> serviceMap = new HashMap<>();
        serviceMap.put(TRANSFER_SERVICE, serviceFactory.getTransferService());
        serviceMap.put(LOG_SERVICE, serviceFactory.getLogService());

        return new TransferController(null, viewMap, serviceMap);
    }

    private ManageEmployeesController createManageEmployeesController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put(MANAGE_EMPLOYEES_VIEW, viewFactory.getManageEmployeesView());

        Map<String, Service> serviceMap = new HashMap<>();
        serviceMap.put(EMPLOYEE_SERVICE,serviceFactory.getEmployeeService());
        serviceMap.put(LOG_SERVICE, serviceFactory.getLogService());

        return new ManageEmployeesController(null, viewMap, serviceMap);
    }

    private GenerateReportController createGenerateReportController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put(GENERATE_REPORT_VIEW, viewFactory.getGenerateReportView());

        Map<String, Service> serviceMap = new HashMap<>();
        serviceMap.put(LOG_SERVICE, serviceFactory.getLogService());

        return new GenerateReportController(null, viewMap, serviceMap);
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
