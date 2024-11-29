package com.example.hvsystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Inventory {

    private SimpleIntegerProperty idProperty;
    private SimpleStringProperty itemNameProperty;
    private SimpleIntegerProperty departmentIdProperty;
    private SimpleIntegerProperty quantityProperty;

    public Inventory() {

        this.idProperty = new SimpleIntegerProperty();
        this.itemNameProperty = new SimpleStringProperty();
        this.departmentIdProperty = new SimpleIntegerProperty();
        this.quantityProperty = new SimpleIntegerProperty();

    }

    public int getQuantityProperty() {
        return quantityProperty.get();
    }

    public SimpleIntegerProperty quantityPropertyProperty() {
        return quantityProperty;
    }

    public void setQuantityProperty(int quantityProperty) {
        this.quantityProperty.set(quantityProperty);
    }

    public int getIdProperty() {
        return idProperty.get();
    }

    public SimpleIntegerProperty idPropertyProperty() {
        return idProperty;
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public String getItemNameProperty() {
        return itemNameProperty.get();
    }

    public SimpleStringProperty itemNamePropertyProperty() {
        return itemNameProperty;
    }

    public void setItemNameProperty(String itemNameProperty) {
        this.itemNameProperty.set(itemNameProperty);
    }

    public int getDepartmentIdProperty() {
        return departmentIdProperty.get();
    }

    public SimpleIntegerProperty departmentIdPropertyProperty() {
        return departmentIdProperty;
    }

    public void setDepartmentIdProperty(int departmentIdProperty) {
        this.departmentIdProperty.set(departmentIdProperty);
    }
}
