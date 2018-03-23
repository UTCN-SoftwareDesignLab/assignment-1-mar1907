import controller.LoginController;
import factory.ControllerFactory;
import factory.ServiceFactory;
import view.LoginView;

public class Launcher {

    public static void main(String[] args) {

        //TODO implement factories and redo main
        //ServiceFactory componentFactory = ServiceFactory.instance();
        //new LoginController(new LoginView(), componentFactory.getAuthenticationService(), componentFactory.getOptionsService());

        ControllerFactory controllerFactory = ControllerFactory.instance();
        controllerFactory.getLoginController().display();
    }

}
