/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 3 - Mean Image Median
 * Name: Michael Wood
 * Created: 1/31/2024
 */
package woodm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Creates an interactive GUI to calculate the mean or median on a set of images
 */
public class Lab3 extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("layout.fxml"));
            stage.setScene(new Scene(pane));
            stage.setTitle("Mean Image Median");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("There was an error reading the FXML file, please try again");
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
