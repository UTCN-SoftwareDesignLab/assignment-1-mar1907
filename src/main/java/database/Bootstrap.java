package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Rights.RIGHTS;
import static database.Constants.Roles.ROLES;
import static database.Constants.Schemas.SCHEMAS;
import static database.Constants.Tables.ORDERED_TABLES_FOR_DROPPING;
import static database.Constants.getRolesRights;

public class Bootstrap {

    public static void main(String[] args) throws SQLException{
        dropAll();

        bootstrapTables();

        //TODO bootstrap user data?
        //TODO new class for admin employee and employee type table
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
                System.out.println(table);
                String createTableSQL = sqlTableCreationFactory.getCreateSQLForTable(table);
                statement.execute(createTableSQL);
            }
        }

        System.out.println("Done table bootstrap");
    }

}
