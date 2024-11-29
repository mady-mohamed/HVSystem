package com.example.hvsystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import javafx.scene.control.Button;

public class HelloApplication extends Application {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button signInButton;
    private Button manageStockButton;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Log-In.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load(), 640, 400);

        usernameField = (TextField) loginScene.lookup("#usernameField");
        passwordField = (PasswordField) loginScene.lookup("#passwordField");
        signInButton = (Button) loginScene.lookup("#signInButton");

        signInButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            int privilege = getUserPrivileges(username, password);
            switch (privilege) {
                case 1:
                    try {
                        redirectToPage("admin.fxml", stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    try {
                        redirectToPage("manager.fxml", stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    try {
                        redirectToPage("employee.fxml", stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        });




        if (fxmlLoader.getLocation().toString().endsWith("employee.fxml")) {
            HelloController controller = fxmlLoader.getController();
        }

        stage.setTitle("Log-in");
        stage.setScene(loginScene);
        stage.show();

        DbConnection.connect();

    }

    private int getUserPrivileges(String username, String password) {
        int privilege = -1;

        String query = "SELECT privilege FROM user WHERE username = ? AND password =?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:login.db");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                privilege = resultSet.getInt("privilege");
            }
        } catch (SQLException e) {
            System.out.println("error in database connection");
            e.printStackTrace();
        }

        return privilege;
    }

    private void redirectToPage(String fxmlFileName, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

