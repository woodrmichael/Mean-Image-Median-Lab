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

/**
 * Creates a Scene that displays either the mean or median of a list of images.
 */
public class Lab2 extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Image finalImage = processFiles(getParameters().getRaw());
            final int width = (int) finalImage.getWidth();
            final int height = (int) finalImage.getHeight();
            HBox pane = new HBox();
            ImageView imageView = new ImageView(finalImage);
            pane.getChildren().add(imageView);
            stage.setScene(new Scene(pane, width, height));
            stage.setTitle(width + " x " + height);
            stage.show();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Checks to make sure that all parameters are valid.
     * Using the image paths the user provides, it generates a list of images that the
     * mean or median is then calculated from.
     * @param args the command line arguments provided by the user
     * @return the final image that is either the mean or median of the input images
     * @throws IOException Thrown if the image format is invalid or
     * there was trouble reading the file.
     * @throws IllegalArgumentException Thrown if there are less than 4 arguments present,
     * the arguments don't follow the order, 'mean/median' 'output file name' 'input file names'...
     */
    private Image processFiles(List<String> args) throws IOException {
        if(args.size() < 4) {
            throw new IllegalArgumentException("Please ensure there are at least 4 arguments");
        }
        String choice = args.getFirst().toLowerCase();
        if(!choice.equals("mean") && !choice.equals("median")) {
            throw new IllegalArgumentException(
                    "Please ensure the first argument is either 'mean' or 'median'");
        }
        ArrayList<Path> inputPaths = new ArrayList<>();
        for(int i = 2; i < args.size(); i++) {
            inputPaths.add(Path.of(args.get(i)));
        }
        Image[] inputImages = new Image[inputPaths.size()];
        for (int i = 0; i < inputPaths.size(); i++) {
            inputImages[i] = MeanImageMedian.readImage(inputPaths.get(i));
        }
        Image finalImage;
        if(choice.equals("mean")) {
            finalImage = MeanImageMedian.calculateMeanImage(inputImages);
        } else {
            finalImage = MeanImageMedian.calculateMedianImage(inputImages);
        }
        MeanImageMedian.writeImage(Path.of(args.get(1)), finalImage);
        return finalImage;
    }
}
