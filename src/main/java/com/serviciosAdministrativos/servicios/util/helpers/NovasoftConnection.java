package com.serviciosAdministrativos.servicios.util.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NovasoftConnection {
    @Value("${db3.datasource.jdbc.url}")
    private String jdbc;
    @Value("${db3.datasource.username}")
    private String user;
    @Value("${db3.datasource.password}")
    private String password;

    public List<Map<String, Object>> executeSelectSql(String query) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(jdbc, user, password);

            var statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            List<Map<String, Object>> response = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> dataRow = new HashMap<>();
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
}
