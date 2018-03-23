package factory;

import controller.LoginController;

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
        createLoginController();
    }

    private LoginController createLoginController(){

    }

    public LoginController getLoginController() {
        return loginController;
    }
}
