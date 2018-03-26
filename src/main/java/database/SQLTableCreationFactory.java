package database;

import static database.Constants.Tables.*;

public class SQLTableCreationFactory {

    public String getCreateSQLForTable(String table){
        switch (table){
            case TYPE:
                return "CREATE TABLE IF NOT EXISTS type (" +
                        "  id int(11) NOT NULL AUTO_INCREMENT," +
                        "  type varchar(500) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE KEY id_UNIQUE (id)" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";

            case CLIENT:
                return  "  CREATE TABLE IF NOT EXISTS client (" +
                        "  id int NOT NULL AUTO_INCREMENT," +
                        "  name varchar(500) NOT NULL," +
                        "  idcard varchar(500) NOT NULL," +
                        "  cnp varchar(500) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE KEY id_UNIQUE (id));";

            case ACCOUNT:
                return  "  CREATE TABLE IF NOT EXISTS account (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  amount INT NOT NULL," +
                        "  interest FLOAT," +
                        "  PRIMARY KEY (id)," +
                        "  client_id INT NOT NULL," +
                        "  type_id INT NOT NULL," +
                        "  UNIQUE KEY id_UNIQUE (id)," +
                        "  CONSTRAINT client_id" +
                        "    FOREIGN KEY (client_id)" +
                        "    REFERENCES client (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT type_id" +
                        "    FOREIGN KEY (type_id)" +
                        "    REFERENCES type (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case BILL:
                return  "  CREATE TABLE IF NOT EXISTS bill (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  amount int NOT NULL," +
                        "  description varchar(500) NOT NULL," +
                        "  account_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE KEY id_UNIQUE (id)," +
                        "  CONSTRAINT billaccount_id" +
                        "    FOREIGN KEY (account_id)" +
                        "    REFERENCES account (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case ROLE:
                return  "  CREATE TABLE IF NOT EXISTS role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC));";

            case RIGHT:
                return  "  CREATE TABLE IF NOT EXISTS `right` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `right` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC));";

            case EMPLOYEE:
                return  "  CREATE TABLE IF NOT EXISTS employee (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  username VARCHAR(200) NOT NULL," +
                        "  password VARCHAR(64) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX username_UNIQUE (username ASC));";

            case ROLE_RIGHT:
                return  "  CREATE TABLE IF NOT EXISTS role_right (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role_id INT NOT NULL," +
                        "  right_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  INDEX right_id_idx (right_id ASC)," +
                        "  CONSTRAINT role_id" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT right_id" +
                        "    FOREIGN KEY (right_id)" +
                        "    REFERENCES `right` (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case EMPLOYEE_ROLE:
                return  "  CREATE TABLE IF NOT EXISTS employee_role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  employee_id INT NOT NULL," +
                        "  role_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX user_id_idx (employee_id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  CONSTRAINT employee_fkid" +
                        "    FOREIGN KEY (employee_id)" +
                        "    REFERENCES employee (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT role_fkid" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case LOG:
                return  "  CREATE TABLE IF NOT EXISTS log (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  employee_id INT NOT NULL," +
                        "  text varchar(500) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  CONSTRAINT logemployee_fkid" +
                        "    FOREIGN KEY (employee_id)" +
                        "    REFERENCES employee (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            default:
                return "";
        }
    }
}
