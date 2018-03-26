package database;

import model.Employee;
import model.Role;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import service.employee.AuthenticationService;
import service.employee.AuthenticationServiceMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static database.Constants.Rights.RIGHTS;
import static database.Constants.Roles.ROLES;
import static database.Constants.Schemas.SCHEMAS;
import static database.Constants.Tables.ORDERED_TABLES_FOR_DROPPING;
import static database.Constants.getEmployeeRoles;
import static database.Constants.getRolesRights;
import static database.Constants.Types.TYPES;

public class Bootstrap {

    private static RightsRolesRepository rightsRolesRepository;
    private static EmployeeRepository employeeRepository;
    private static AuthenticationService authenticationService;
    private static AccountRepository accountRepository;

    public static void main(String[] args) throws SQLException{
        dropAll();

        bootstrapTables();

        bootstrapUserData();
        //TODO bootstrap user data?
    }

    private static void dropAll() throws SQLException{
        for(String schema: SCHEMAS){
            System.out.println("Dropping all tables in schema: " + schema);

            Connection connection = new JDBConnectionWrapper(schema).getConnection();
            Statement statement = connection.createStatement();

            List<String> dropStatements = new ArrayList<>();

            for(String table: ORDERED_TABLES_FOR_DROPPING){
                dropStatements.add( "TRUNCATE `" + table + "`;");
                dropStatements.add( "DROP TABLE `" + table + "`;");
            }

            dropStatements.stream().forEach(dropStatement -> {
                try{
                    statement.execute(dropStatement);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            });
        }

        System.out.println("Done table bootstrap!");
    }

    private static void bootstrapTables() throws SQLException {
        SQLTableCreationFactory sqlTableCreationFactory = new SQLTableCreationFactory();

        for (String schema : SCHEMAS) {
            System.out.println("Bootstrapping " + schema + " schema");


            JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(schema);
            Connection connection = connectionWrapper.getConnection();

            Statement statement = connection.createStatement();

            for (String table : Constants.Tables.ORDERED_TABLES_FOR_CREATION) {
                String createTableSQL = sqlTableCreationFactory.getCreateSQLForTable(table);
                statement.execute(createTableSQL);
            }
        }

        System.out.println("Done table bootstrap");
    }

    private static void bootstrapUserData() throws SQLException {
        for (String schema : SCHEMAS) {
            System.out.println("Bootstrapping user data for " + schema);

            JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(schema);
            rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());
            employeeRepository = new EmployeeRepositoryMySQL(connectionWrapper.getConnection(), rightsRolesRepository);
            authenticationService = new AuthenticationServiceMySQL(employeeRepository, rightsRolesRepository);
            accountRepository = new AccountRepositoryMySQL(connectionWrapper.getConnection());

            bootstrapRoles();
            bootstrapRights();
            bootstrapRoleRight();
            bootstrapUserRoles();
            bootstrapAccountTypes();
        }
    }

    private static void bootstrapRoles() throws SQLException {
        for (String role : ROLES) {
            rightsRolesRepository.addRole(role);
        }
    }

    private static void bootstrapRights() throws SQLException {
        for (String right : RIGHTS) {
            rightsRolesRepository.addRight(right);
        }
    }

    private static void bootstrapRoleRight() throws SQLException {
        Map<String, List<String>> rolesRights = getRolesRights();

        for (String role : rolesRights.keySet()) {
            Long roleId = rightsRolesRepository.findRoleByTitle(role).getId();

            for (String right : rolesRights.get(role)) {
                Long rightId = rightsRolesRepository.findRightByTitle(right).getId();

                rightsRolesRepository.addRoleRight(roleId, rightId);
            }
        }
    }

    private static void bootstrapUserRoles() throws SQLException {
        Map<List<String>, List<String>> employeeRoles = getEmployeeRoles();

        for (List<String> e : employeeRoles.keySet()){
            authenticationService.register(e.get(0), e.get(1));

            List<Role> roleList = new ArrayList<>();
            for(String role : employeeRoles.get(e)){
                roleList.add(rightsRolesRepository.findRoleByTitle(role));
            }

            try{
                Employee emp = authenticationService.login(e.get(0), e.get(1)).getResult();
                emp.setRoles(roleList);
                employeeRepository.update(emp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    private static void bootstrapAccountTypes(){
        for(String type: TYPES){
            accountRepository.addType(type);
        }
    }

}
