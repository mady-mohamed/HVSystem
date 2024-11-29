module com.example.hvsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.hvsystem to javafx.fxml;
    exports com.example.hvsystem;
}