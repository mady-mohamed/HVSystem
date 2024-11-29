package com.example.hvsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    public static ObservableList<User> getAllUsers() throws ClassNotFoundException, SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();

        String sql = "select * from user";
        return getUsers(userList, sql);
    }

    private static ObservableList<User> getUsers(ObservableList<User> userList, String sql) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:login.db")) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int privilege = resultSet.getInt("privilege");
                int departmentId = resultSet.getInt("department_id");

                User user = new User();
                user.setUserId(userId);
                user.setUsername(username);
                user.setPassword(password);
                user.setPrivilege(privilege);
                user.setDepartmentId(departmentId);

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }

        return userList;
    }

    public static ObservableList<Inventory> getAllRecords() throws ClassNotFoundException, SQLException {
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

        String sql = "select * from Inventory";
        return getInventories(inventoryList, sql);
    }

    private static ObservableList<Inventory> getInventories(ObservableList<Inventory> inventoryList, String sql) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:login.db")) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String itemName = resultSet.getString("item_name");
                int department_id = resultSet.getInt("department_id");
                int quantity = resultSet.getInt("quantity");

                Inventory inventory = new Inventory();
                inventory.setIdProperty(id);
                inventory.setItemNameProperty(itemName);
                inventory.setDepartmentIdProperty(department_id);
                inventory.setQuantityProperty(quantity);

                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }

        return inventoryList;
    }
    public static ObservableList<Inventory> getDepartmentRecords(int departmentId) throws ClassNotFoundException, SQLException {
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(); // Create an empty observable list

        String sql = "SELECT id, item_name, department_id, quantity FROM Inventory WHERE department_id = " + departmentId;
        return getInventories(inventoryList, sql);
    }
    public static ObservableList<Inventory> getDepartmentRecordsLow(int departmentId) throws ClassNotFoundException, SQLException {
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Inventory WHERE quantity < 5 AND department_id = " + departmentId;
        return getInventories(inventoryList, sql);
    }
}
