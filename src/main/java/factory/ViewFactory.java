package factory;

import view.LoginView;
import view.OptionsView;

public class ViewFactory {

    private static ViewFactory instance;

    private LoginView loginView;
    private OptionsView optionsView;

    public static ViewFactory instance(){
        if(instance==null){
            instance = new ViewFactory();
        }

        return instance;
    }

    private ViewFactory(){
        loginView = new LoginView();
        optionsView = new OptionsView();
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public OptionsView getOptionsView() {
        return optionsView;
    }
}
