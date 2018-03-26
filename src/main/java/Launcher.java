import factory.ControllerFactory;

public class Launcher {

    public static void main(String[] args) {

        ControllerFactory controllerFactory = ControllerFactory.instance();
        controllerFactory.getLoginController().display();

    }
}
