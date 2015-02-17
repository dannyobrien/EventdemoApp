package com.event.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection sConnection;
    //test
    // Implement the DBConnection class as a singleton
    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        String host, db, user, password;
        // COMMENT
        host = "daneel";
        db = "n00134452";
        user = "N00134452";
        password = "N00134452";
        
        if (sConnection == null || sConnection.isClosed()) {
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            sConnection = DriverManager.getConnection(url, user, password);
        }

        return sConnection;
    }
}
