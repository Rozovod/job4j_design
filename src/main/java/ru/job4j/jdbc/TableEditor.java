package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    public void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void initStatement(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        initStatement(String.format("create table %s ()", tableName));
    }

    public void dropTable(String tableName) throws Exception {
        initStatement(String.format("drop table %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        initStatement(String.format("alter table %s add %s %s;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        initStatement(String.format("alter table %s drop column %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        initStatement(String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format(
                        "%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)
                ));
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(
                "app.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("test111");
            System.out.println(tableEditor.getTableScheme("test111"));

            tableEditor.addColumn("test111", "testColumn", "text");
            System.out.println(tableEditor.getTableScheme("test111"));

            tableEditor.renameColumn("test111", "testColumn", "newTestColumn");
            System.out.println(tableEditor.getTableScheme("test111"));

            tableEditor.dropColumn("test111", "newTestColumn");
            System.out.println(tableEditor.getTableScheme("test111"));

            tableEditor.dropTable("test111");
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
