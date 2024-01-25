/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 2 - Mean Image Median
 * Name: Michael Wood
 * Created: 1/25/2024
 */
package woodm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Lab2 extends Application {
    @Override
    public void start(Stage stage) {
        List<String> parameters = getParameters().getRaw();
        String choice = parameters.get(0);
        Path output = Path.of(parameters.get(1));
        ArrayList<Path> inputPaths = new ArrayList<>();
        for(int i = 2; i < parameters.size(); i++) {
            inputPaths.add(Path.of(parameters.get(i)));
        }
        Image[] inputImages = new Image[inputPaths.size()];
        try {
            for(int i = 0; i < inputPaths.size(); i++) {
                inputImages[i] = MeanImageMedian.readImage(inputPaths.get(i));
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        final int width = (int) inputImages[0].getWidth();
        final int height = (int) inputImages[0].getHeight();
        HBox pane = new HBox();
        ImageView imageView = new ImageView();
        pane.getChildren().add(imageView);
        stage.setScene(new Scene(pane, width, height));
        stage.setTitle(width + " x " + height);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
