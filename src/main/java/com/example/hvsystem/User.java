package com.example.hvsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

    private SimpleIntegerProperty userId;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleIntegerProperty privilege;
    private SimpleIntegerProperty departmentId;

    public User() {
        this.userId =  new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.privilege =  new SimpleIntegerProperty();
        this.departmentId = new SimpleIntegerProperty();
    }

    public int getUserId() {
        return userId.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public int getPrivilege() {
        return privilege.get();
    }

    public SimpleIntegerProperty privilegeProperty() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege.set(privilege);
    }

    public int getDepartmentId() {
        return departmentId.get();
    }

    public SimpleIntegerProperty departmentIdProperty() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId.set(departmentId);
    }
}
