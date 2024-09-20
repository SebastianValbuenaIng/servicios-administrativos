package com.serviciosAdministrativos.servicios.util.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RegistroConnection {
    @Value("${db6.datasource.jdbc.url}")
    private String jdbc;
    @Value("${db6.datasource.username}")
    private String user;
    @Value("${db6.datasource.password}")
    private String password;

    public List<Map<String, String>> executeSelectSql(String query) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(jdbc, user, password);

            var statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            List<Map<String, String>> response = new ArrayList<>();

            while (rs.next()) {
                Map<String, String> dataRow = new HashMap<>();
                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumns = rsmd.getColumnCount();
                for (int i = 1; i <= numColumns; i++) {
                    dataRow.put(rsmd.getColumnName(i), rs.getString(i));
                }

                response.add(dataRow);
            }

            rs.close();
            statement.close();
            connection.close();

            return response;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void executeSql(String query) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(jdbc, user, password);

            var statement = connection.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}