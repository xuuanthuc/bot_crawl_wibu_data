package util;

import model.config.ConfigDatabase;

import java.sql.*;

public class AppSql {
    private Connection connection;
    private AppStorage appStorage;

    public AppSql() {
        appStorage = AppStorage.getInstance();
    }

    public void connect(Callback callback) {
        try {
            Class.forName(AppData.Database.MySQL.driver);
            ConfigDatabase configDatabase = appStorage.config.database;
            if (configDatabase == null) return;
            String connectString = String.format(
                    AppData.Database.MySQL.url,
                    configDatabase.domain + ":" + configDatabase.port,
                    configDatabase.schema,
                    configDatabase.user,
                    configDatabase.pass
            );
            connection = DriverManager.getConnection(connectString);
            callback.execute(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface Callback {
        void execute(Connection connect);
    }
}