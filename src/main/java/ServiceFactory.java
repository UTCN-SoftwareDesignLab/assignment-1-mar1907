import database.DBConnectionFactory;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;
import service.employee.AuthenticationService;
import service.employee.AuthenticationServiceMySQL;
import service.options.OptionServiceImpl;
import service.options.OptionsService;

import java.sql.Connection;


public class ServiceFactory {

    private final AuthenticationService authenticationService;
    private final OptionsService optionsService;

    private final EmployeeRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    private static ServiceFactory instance;

    public static ServiceFactory instance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    private ServiceFactory() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new EmployeeRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.optionsService = new OptionServiceImpl(this.rightsRolesRepository);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public OptionsService getOptionsService() {
        return optionsService;
    }

    public EmployeeRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }
}
