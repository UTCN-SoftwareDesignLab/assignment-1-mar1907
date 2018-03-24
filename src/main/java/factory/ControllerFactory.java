package factory;

import controller.*;
import service.Service;
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

        return new OptionsController(controllerMap, viewMap, null);
    }

    private ManageClientsController createManageClientsController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("manageClientsView", viewFactory.getManageClientsView());

        Map<String, Service> serviceMap = new HashMap<>();
        //TODO add services

        return new ManageClientsController(null, viewMap, serviceMap);
    }

    private ManageAccountsController createManageAccountsController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("manageAccountsView", viewFactory.getManageAccountsView());

        Map<String, Service> serviceMap = new HashMap<>();
        //TODO add services

        return new ManageAccountsController(null, viewMap, serviceMap);
    }

    private PayBillController createPayBillController(){
        Map<String, View> viewMap = new HashMap<>();
        viewMap.put("payBillView", viewFactory.getPayBillView());

        Map<String, Service> serviceMap = new HashMap<>();
        //TODO add services

        return new PayBillController(null, viewMap, serviceMap);
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
