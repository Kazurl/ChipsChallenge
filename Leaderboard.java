package com.example.whywonthtisfuckingwork;

import javafx.application.Application;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends Application {

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> scores = new ArrayList<>();
    private static String LEADERBOARD_DATA = "leaderboard.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Leaderboard");

        // Read the leaderboard currently in the database
        readCurrentLB();

        // Display the leaderboard
        displayLeaderboard(primaryStage);
    }

    public void displayLeaderboard(Stage stage) {
        // Creating TableView and columns
        TableView<PlayerEntry> tableView = new TableView<>();
        TableColumn<PlayerEntry, Integer> scoreColumn = new TableColumn<>("Score");
        TableColumn<PlayerEntry, String> nameColumn = new TableColumn<>("Name");

        // Mapping data to columns without using properties
        scoreColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScore()));
        nameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));

        // Adding columns to TableView
        tableView.getColumns().addAll(scoreColumn, nameColumn);

        // Populating the TableView with data from the lists
        ObservableList<PlayerEntry> playerEntries = FXCollections.observableArrayList();
        for (int i = 0; i < scores.size(); i++) {
            playerEntries.add(new PlayerEntry(scores.get(i), names.get(i)));
        }
        tableView.setItems(playerEntries);

        // Creating a VBox to hold the TableView
        VBox vBox = new VBox(tableView);

        // Creating a Scene and setting it to the Stage
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    public void displayLeaderboard() {
        // Creating TableView and columns
        TableView<PlayerEntry> tableView = new TableView<>();
        TableColumn<PlayerEntry, Integer> scoreColumn = new TableColumn<>("Score");
        TableColumn<PlayerEntry, String> nameColumn = new TableColumn<>("Name");

        // Mapping data to columns without using properties
        scoreColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScore()));
        nameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));


        // Adding columns to TableView
        tableView.getColumns().addAll(scoreColumn, nameColumn);

        // Populating the TableView with data from the lists
        ObservableList<PlayerEntry> playerEntries = FXCollections.observableArrayList();
        for (int i = 0; i < scores.size(); i++) {
            playerEntries.add(new PlayerEntry(scores.get(i), names.get(i)));
        }
        tableView.setItems(playerEntries);

        // Creating a VBox to hold the TableView
        VBox vBox = new VBox(tableView);

        // Creating a Scene and setting it to the Stage
        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Leaderboard");
        stage.show();
    }

    private void readCurrentLB() {
        try {
            FileReader fr = new FileReader(LEADERBOARD_DATA);
            BufferedReader br = new BufferedReader(fr);

            String fileData = br.readLine();

            while (fileData != null) {
                String[] splitLine = fileData.split(":");
                int i = 0;

                while (i<splitLine.length-1){
                    names.add(splitLine[i]);
                    i++;
                    scores.add(Integer.valueOf(splitLine[i]));
                    i++;
                }

                fileData = br.readLine();
            }

            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void addScore(String playerName, int playerScore) {
        int index = 0;

        // Find the correct index to insert the new score
        while (index < scores.size() && playerScore > scores.get(index)) {
            index++;
        }

        // Insert the new score and name at the found index
        scores.add(index, playerScore);
        names.add(index, playerName);

        // Update the file
        updateFile();

        readCurrentLB();
        displayLeaderboard();
    }

    // Method to update the file with the current scores and names
    private void updateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEADERBOARD_DATA))) {
            for (int i = 0; i < scores.size(); i++) {
                writer.write(names.get(i) + ":" + scores.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    // Inner class representing a PlayerEntry with score and name properties
    public static class PlayerEntry {
        private final Integer score;
        private final String name;

        public PlayerEntry(Integer score, String name) {
            this.score = score;
            this.name = name;
        }

        public Integer getScore() {
            return score;
        }

        public String getName() {
            return name;
        }
    }

}
