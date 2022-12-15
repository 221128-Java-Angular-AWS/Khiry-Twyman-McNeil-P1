package com.amazon.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {




    private static Connection connection;
    private ConnectionFactory () {
        //PLACE HOLDER
    }

    public static Connection getConnection() {
        if (connection == null) {
            connection = connect();
            return connection;
        } else {
            return connection;
        }

    }
    private static Connection connect() {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream("jdbc.properties");

        try {
            prop.load(inputStream);

            StringBuilder builder = new StringBuilder();
            builder.append("jdbc:postgresql://");
            builder.append(prop.getProperty("host"));
            builder.append(":");
            builder.append(prop.getProperty("port"));
            builder.append("/");
            builder.append(prop.getProperty("dbname"));
            builder.append("?user=");
            builder.append(prop.getProperty("username"));
            builder.append("&password");
            builder.append(prop.getProperty("password"));

            Class.forName(prop.getProperty("driver"));

            connection = DriverManager.getConnection(builder.toString());




        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
