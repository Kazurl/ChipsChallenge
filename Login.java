package com.example.whywonthtisfuckingwork;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Login extends Application {

    private static final String LOGIN_DATA_FILE = "logInData.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Application");

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 1);

        TextField usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 4);

        Button signUpButton = new Button("Sign Up");
        grid.add(signUpButton, 2, 4);

        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);

        primaryStage.show();

        signUpButton.setOnAction(e -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            if (isValidUsername(username) && isValidPassword(password) && isUsernameUnique(username)) {
                // Save the new username and password to the database
                saveUser(username, password);
                showMessage("Sign Up successful!");
            } else {
                String errorMessage = "Username not available. Please try again.";
                if (!isValidUsername(username)) {
                    errorMessage = "Username must contain only letters and numbers.";
                } else if (!isValidPassword(password)) {
                    errorMessage = "Password must contain at least one number, one lowercase letter, and one uppercase letter.";
                }
                showMessage(errorMessage);
            }
        });

        loginButton.setOnAction(e -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            if (authenticateUser(username, password)) {
                showMessage("Login successful!");
            } else {
                showMessage("Invalid username or password. Please try again.");
            }
        });
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Remove the icon
        alert.getDialogPane().setGraphic(null);

        alert.showAndWait();
    }


    private boolean authenticateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true; // Authentication successful
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Authentication failed
    }

    private boolean isValidUsername(String username) {
        // Check if the username contains at least one letter and one number
        return username.matches(".*[a-zA-Z].*") && username.matches(".*\\d.*");
    }


    private boolean isValidPassword(String password) {
        // Password must contain at least one number, one lowercase letter, and one uppercase letter
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{1,}$");
    }

    private boolean isUsernameUnique(String username) {
        Set<String> existingUsernames = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    existingUsernames.add(parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return !existingUsernames.contains(username);
    }

    private void saveUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_DATA_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

