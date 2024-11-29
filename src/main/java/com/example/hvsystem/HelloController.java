package com.example.hvsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelloController {
    

    @FXML
    private TableColumn<Inventory, String> DCIName;

    @FXML
    private TableColumn<Inventory, Integer> DCId;

    @FXML
    private TableColumn<Inventory, Integer> DCIQuantity;

    @FXML
    private TableView<Inventory> DCTable;

    @FXML
    private TableColumn<Inventory, Integer> MOEID;

    @FXML
    private TableColumn<Inventory, String> MOEIName;

    @FXML
    private TableColumn<Inventory, Integer> MOEIQuantity;

    @FXML
    private TableView<Inventory> MOETable;

    @FXML
    private TableColumn<Inventory, String> MOAIName;

    @FXML
    private TableColumn<Inventory, Integer> MOAIQuantity;

    @FXML
    private TableColumn<Inventory, Integer> MOAId;

    @FXML
    private TableView<Inventory> MOATable;


    @FXML
    private TableColumn<Inventory, String> DokkiIName;

    @FXML
    private TableColumn<Inventory, Integer> DokkiIQuantity;

    @FXML
    private TableColumn<Inventory, Integer> DokkiId;

    @FXML
    private TableView<Inventory> DokkiTable;

    @FXML
    private TableColumn<Inventory, String> HeliopolisIName;

    @FXML
    private TableColumn<Inventory, Integer> HeliopolisIQuantity;

    @FXML
    private TableColumn<Inventory, Integer> HeliopolisId;

    @FXML
    private TableView<Inventory> HeliopolisTable;

    @FXML
    private TableColumn<Inventory, String> MOEINameLow;

    @FXML
    private TableColumn<Inventory, Integer> MOEIQuantityLow;

    @FXML
    private TableColumn<Inventory, Integer> MOEIdLow;

    @FXML
    private TableView<Inventory> MOETableLow;

    @FXML
    private TableColumn<Inventory, String> MOAINameLow;

    @FXML
    private TableColumn<Inventory, Integer> MOAIQuantityLow;

    @FXML
    private TableColumn<Inventory, Integer> MOAIdLow;

    @FXML
    private TableView<Inventory> MOATableLow;

    @FXML
    private TableColumn<Inventory, String> DokkiINameLow;

    @FXML
    private TableColumn<Inventory, Integer> DokkiIQuantityLow;

    @FXML
    private TableColumn<Inventory, Integer> DokkiIdLow;

    @FXML
    private TableView<Inventory> DokkiTableLow;

    @FXML
    private TableColumn<Inventory, String> HeliopolisINameLow;

    @FXML
    private TableColumn<Inventory, Integer> HeliopolisIQuantityLow;

    @FXML
    private TableColumn<Inventory, Integer> HeliopolisIdLow;

    @FXML
    private TableView<Inventory> HeliopolisTableLow;

    @FXML
    private TableColumn<Inventory, Integer> departmentIdAll;

    @FXML
    private TableColumn<Inventory, Integer> idAll;

    @FXML
    private TableColumn<Inventory, String> itemNameAll;

    @FXML
    private TableColumn<Inventory, Integer> quantityAll;

    @FXML
    private TableView<Inventory> tableAll;

    @FXML
    private Button removeButton;

    @FXML
    private TextField departmentId;

    @FXML
    private TextField itemName;

    @FXML
    private TextField quantityField;

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, Integer> colDepartmentId;

    @FXML
    private TableColumn<User, Integer> colPrivilege;

    @FXML
    private TableColumn<User, Integer> colUserId;

    @FXML
    private TableColumn<User, String> colUserName;

    @FXML
    private TableColumn<User, String> colPassword;

    @FXML
    private TextField usernameFieldAdmin;

    @FXML
    private TextField passwordFieldAdmin;

    @FXML
    private TextField privilegeField;

    @FXML
    private TextField departmentIdAdmin;


    private Inventory selectedItemToUpdate;

    private User selectedUserToUpdate;



    @FXML
    void removeButtonClicked(MouseEvent event) {
        if (tableAll != null){
        ObservableList<Inventory> selectedProducts = tableAll.getSelectionModel().getSelectedItems();
        String deleteQuery = "DELETE FROM Inventory WHERE id = ? AND item_name = ? AND department_id = ? AND quantity = ?";
        PreparedStatement statement = null;
        try {
            statement = DbConnection.connect().prepareStatement(deleteQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Inventory product : selectedProducts) {
            // Set the parameter for each selected item
            try {
                statement.setInt(1, product.getIdProperty());
                statement.setString(2, product.getItemNameProperty());
                statement.setInt(3, product.getDepartmentIdProperty());
                statement.setInt(4, product.getQuantityProperty());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ObservableList<Inventory> SingleProduct, allProducts;
        allProducts = tableAll.getItems();
        SingleProduct = tableAll.getSelectionModel().getSelectedItems();
        SingleProduct.forEach(allProducts::remove);

    }}

    @FXML
    void addButtonClicked(MouseEvent event) {
        String depId = departmentId.getText();
        int id;
        try {
            id = Integer.parseInt(depId);
        } catch (NumberFormatException e) {
            // Handle invalid department ID format
            return;
        }
        String itName = itemName.getText();
        String quant = quantityField.getText();
        int quantity;
        try {
            quantity = Integer.parseInt(quant);
        } catch (NumberFormatException e) {
            // Handle invalid quantity format
            return;
        }

        // Database connection and insertion logic
        try (Connection connection = DbConnection.connect()) { // Use a try-with-resources block
            String addQuery = "INSERT INTO Inventory (item_name, department_id, quantity) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(addQuery);

            // Set parameters after creating the PreparedStatement
            statement.setString(1, itName);
            statement.setInt(2, id);
            statement.setInt(3, quantity);

            statement.executeUpdate();
            refreshTable(connection);
        } catch (SQLException e) {
            // Handle database exception (e.g., display an error message)
            e.printStackTrace();  // For debugging, consider a more user-friendly error message
        }
    }

    @FXML
    void updateButtonClicked(MouseEvent event) {
        String depId = departmentId.getText();
        int id;
        try {
            id = Integer.parseInt(depId);
        } catch (NumberFormatException e) {
            // Handle invalid department ID format
            return;
        }
        String itName = itemName.getText();
        String quant = quantityField.getText();
        int quantity;
        try {
            quantity = Integer.parseInt(quant);
        } catch (NumberFormatException e) {
            // Handle invalid quantity format
            return;
        }

        // Get the ID of the selected item (replace with your logic)
        int selectedId = getSelectedItemToUpdateId();  // Implement this method to get the selected item ID

        try (Connection connection = DbConnection.connect()) { // Use a try-with-resources block
            String addQuery = "UPDATE Inventory SET item_name = ?, department_id = ?, quantity = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(addQuery);

            // Set parameters after creating the PreparedStatement
            statement.setString(1, itName);
            statement.setInt(2, id);
            statement.setInt(3, quantity);
            statement.setInt(4, selectedId);

            statement.executeUpdate();
            refreshTable(connection);
        } catch (SQLException e) {
            // Handle database exception (e.g., display an error message)
            e.printStackTrace();  // For debugging, consider a more user-friendly error message
        }
    }

    @FXML
    void removeUserButtonClicked(MouseEvent event) {
        if (tableAll != null){
            ObservableList<User> selectedUser = tableUsers.getSelectionModel().getSelectedItems();
            String deleteQuery = "DELETE FROM user WHERE user.user_id = ? AND username = ? AND password = ? AND privilege = ? AND department_id = ?";
            PreparedStatement statement = null;
            try {
                statement = DbConnection.connect().prepareStatement(deleteQuery);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (User user : selectedUser) {
                // Set the parameter for each selected item
                try {
                    statement.setInt(1, user.getUserId());
                    statement.setString(2, user.getUsername());
                    statement.setString(3, user.getPassword());
                    statement.setInt(4, user.getPrivilege());
                    statement.setInt(5, user.getDepartmentId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            ObservableList<User> SingleUser, allUsers;
            allUsers = tableUsers.getItems();
            SingleUser = tableUsers.getSelectionModel().getSelectedItems();
            SingleUser.forEach(allUsers::remove);

        }}


    @FXML
    void addUserButtonClicked(MouseEvent event) {
        String userName = usernameFieldAdmin.getText();
        String userPassword = passwordFieldAdmin.getText();
        String depId = departmentIdAdmin.getText();
        String userPrivilege = privilegeField.getText();
        int privilege;
        int departId = 3;
        try {
            privilege = Integer.parseInt(userPrivilege);
            departId = Integer.parseInt(depId);
        } catch (NumberFormatException e) {
            // Handle invalid department ID format
            return;
        }

        // Database connection and insertion logic
        try (Connection connection = DbConnection.connect()) { // Use a try-with-resources block
            String addQuery = "INSERT INTO user (username, password, privilege, department_id) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(addQuery);

            // Set parameters after creating the PreparedStatement
            statement.setString(1, userName);
            statement.setString(2, userPassword);
            statement.setInt(3, privilege);
            statement.setInt(4, departId);
            statement.executeUpdate();
            refreshTable(connection);
        } catch (SQLException e) {
            // Handle database exception (e.g., display an error message)
            e.printStackTrace();  // For debugging, consider a more user-friendly error message
        }

    }

    @FXML
    void updateButtonAdminClicked(MouseEvent event) {
        String userName = usernameFieldAdmin.getText();
        String userPassword = passwordFieldAdmin.getText();
        String depId = departmentIdAdmin.getText();
        String userPrivilege = privilegeField.getText();
        int privilege;
        int departId = 3;
        try {
            privilege = Integer.parseInt(userPrivilege);
            departId = Integer.parseInt(depId);
        } catch (NumberFormatException e) {
            // Handle invalid department ID format
            return;
        }

        // Get the ID of the selected item (replace with your logic)
        int selectedId = getSelectedItemToUpdateId();  // Implement this method to get the selected item ID

        try (Connection connection = DbConnection.connect()) { // Use a try-with-resources block
            String addQuery = "UPDATE user SET username = ?, password = ?, privilege = ?, department_id = ? WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(addQuery);

            // Set parameters after creating the PreparedStatement
            statement.setString(1, userName);
            statement.setString(2, userPassword);
            statement.setInt(3, privilege);
            statement.setInt(4, departId);
            statement.setInt(5, selectedId);

            statement.executeUpdate();
            refreshTable(connection);
        } catch (SQLException e) {
            // Handle database exception (e.g., display an error message)
            e.printStackTrace();  // For debugging, consider a more user-friendly error message
        }
    }

    private int getSelectedItemToUpdateId() {
        if (selectedItemToUpdate != null) {
            return selectedItemToUpdate.getIdProperty();
        } else {
            // Handle case where no item is selected (e.g., throw an exception or return a default value)
            return -1;  // Example default value
        }
    }

    private void refreshTable(Connection connection) throws SQLException {
        if (tableAll != null) {
            ObservableList<Inventory> allProducts = null; // Use retrieved data
            try {
                allProducts = FXCollections.observableArrayList(Database.getAllRecords());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            tableAll.setItems(allProducts);
            if (tableUsers != null) {
                ObservableList<User> allUsers = null; // Use retrieved data
                try {
                    allUsers = FXCollections.observableArrayList(Database.getAllUsers());
                    tableUsers.setItems(allUsers);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //retrieve data and fill cell with data
    public void setTable(TableView<Inventory> table, TableColumn<Inventory, String> inventoryName, TableColumn<Inventory, Integer> inventoryId, TableColumn<Inventory, Integer> quantity, int departmentId) {
        try {
            table.setItems(Database.getDepartmentRecords(departmentId));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inventoryName.setCellValueFactory(cellData -> cellData.getValue().itemNamePropertyProperty());
        inventoryId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        quantity.setCellValueFactory(cellData -> cellData.getValue().quantityPropertyProperty().asObject());
    }
    public void setTableLow(TableView<Inventory> table, TableColumn<Inventory, String> inventoryName, TableColumn<Inventory, Integer> inventoryId, TableColumn<Inventory, Integer> quantity, int departmentId) {
        try {
            table.setItems(Database.getDepartmentRecordsLow(departmentId));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inventoryName.setCellValueFactory(cellData -> cellData.getValue().itemNamePropertyProperty());
        inventoryId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        quantity.setCellValueFactory(cellData -> cellData.getValue().quantityPropertyProperty().asObject());
    }

    @FXML
    public void initialize() {
        try {
            setTable(MOETable, MOEIName, MOEID, MOEIQuantity, 1);
            setTable(MOATable, MOAIName, MOAId, MOAIQuantity, 2);
            setTable(DokkiTable, DokkiIName, DokkiId, DokkiIQuantity, 3);
            setTable(HeliopolisTable, HeliopolisIName, HeliopolisId, HeliopolisIQuantity, 4);
            setTable(DCTable, DCIName, DCId, DCIQuantity, 5);

            setTableLow(MOETableLow, MOEINameLow, MOEIdLow, MOEIQuantityLow, 1);
            setTableLow(MOATableLow, MOAINameLow, MOAIdLow, MOAIQuantityLow, 2);
            setTableLow(DokkiTableLow, DokkiINameLow, DokkiIdLow, DokkiIQuantityLow, 3);
            setTableLow(HeliopolisTableLow, HeliopolisINameLow, HeliopolisIdLow, HeliopolisIQuantityLow, 4);

            if (tableAll != null) {
                int selectedId = -1;  // Initialize here (outside the lambda)

                SelectionModel<Inventory> selectionModel = tableAll.getSelectionModel();

                selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedItemToUpdate = newValue;
                        int departId = tableAll.getSelectionModel().getSelectedItem().getDepartmentIdProperty();
                        int iQuantity = tableAll.getSelectionModel().getSelectedItem().getQuantityProperty();
                        itemName.setText(tableAll.getSelectionModel().getSelectedItem().getItemNameProperty());
                        departmentId.setText(String.valueOf(departId));
                        quantityField.setText(String.valueOf(iQuantity));
                    }
                });
            }
            if(this.tableAll != null){
                tableAll.setItems(Database.getAllRecords());
                itemNameAll.setCellValueFactory(cellData -> cellData.getValue().itemNamePropertyProperty());
                departmentIdAll.setCellValueFactory(cellData -> cellData.getValue().departmentIdPropertyProperty().asObject());
                quantityAll.setCellValueFactory(cellData -> cellData.getValue().quantityPropertyProperty().asObject());
                idAll.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
                ObservableList<Inventory> selectedProducts = tableAll.getSelectionModel().getSelectedItems();
            }
            if(tableUsers != null){
                tableUsers.setItems(Database.getAllUsers());
                colUserId.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());
                colDepartmentId.setCellValueFactory(cellData -> cellData.getValue().departmentIdProperty().asObject());
                colPrivilege.setCellValueFactory(cellData -> cellData.getValue().privilegeProperty().asObject());
                colUserName.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
                colPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
                int selectedId = -1;  // Initialize here (outside the lambda)

                SelectionModel<User> selectionModelUser = tableUsers.getSelectionModel();

                selectionModelUser.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedUserToUpdate = newValue;
                        usernameFieldAdmin.setText(tableUsers.getSelectionModel().getSelectedItem().getUsername());
                        passwordFieldAdmin.setText(tableUsers.getSelectionModel().getSelectedItem().getPassword());
                        int priv = tableUsers.getSelectionModel().getSelectedItem().getPrivilege();
                        privilegeField.setText(String.valueOf(priv));
                        int departId = tableUsers.getSelectionModel().getSelectedItem( ).getDepartmentId();
                        departmentIdAdmin.setText(String.valueOf(departId));
                    }
                });
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
