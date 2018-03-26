package factory;

import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.bill.BillRepository;
import repository.bill.BillRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceMySQL;
import service.bill.BillService;
import service.bill.BillServiceMySQL;
import service.client.ClientService;
import service.client.ClientServiceMySQL;
import service.employee.AuthenticationService;
import service.employee.AuthenticationServiceMySQL;
import service.employee.EmployeeService;
import service.employee.EmployeeServiceMySQL;
import service.options.OptionServiceImpl;
import service.options.OptionsService;
import service.transfer.TransferService;
import service.transfer.TransferServiceMySQL;

import java.sql.Connection;


public class ServiceFactory {

    private final AuthenticationService authenticationService;
    private final OptionsService optionsService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final TransferService transferService;
    private final BillService billService;

    private final EmployeeRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final BillRepository billRepository;
    private final EmployeeService employeeService;

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

        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.clientService = new ClientServiceMySQL(clientRepository);

        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.accountService = new AccountServiceMySQL(accountRepository);

        this.transferService = new TransferServiceMySQL(accountRepository, accountService);

        this.billRepository = new BillRepositoryMySQL(connection);
        this.billService = new BillServiceMySQL(accountRepository,billRepository);

        this.employeeService = new EmployeeServiceMySQL(userRepository,rightsRolesRepository,authenticationService);
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

    public ClientService getClientService() {
        return clientService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    public BillService getBillService() {
        return billService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}
