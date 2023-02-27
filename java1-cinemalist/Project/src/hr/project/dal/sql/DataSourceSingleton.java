package hr.project.dal.sql;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;

/**
 *
 * @author Goran
 */
public class DataSourceSingleton {

    private static final String SERVER_NAME = "10.0.0.10";
    private static final String DATABASE_NAME = "ProjectJava";
    private static final String USERNAME = "sas";
    private static final String PASSWORD = "SQL";

    private static DataSource instance;

    private DataSourceSingleton() {
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static DataSource createInstance() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

}
