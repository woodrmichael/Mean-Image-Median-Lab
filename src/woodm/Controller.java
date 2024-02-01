/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 3 - Mean Image Median
 * Name: Michael Wood
 * Created: 2/1/2024
 */
package woodm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Button addImageButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private HBox imageContainer;
    @FXML
    private Button saveButton;
    private Image finalImage;
    private ArrayList<Image> inputImages = new ArrayList<>();
    private FileChooser fileChooser;
    // Create a constructor to create FileChooser and also limit the extensions also
    public Controller() {
        this.fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.ppm"));
    }
    @FXML
    public void inputImage(ActionEvent actionEvent) throws IOException {
        //getExtensionFilters .addAll new ExtensionFilter // filter to .ppm, .png, .jpg
        Path imagePath = this.fileChooser.showOpenDialog(null).toPath();
        Image image = MeanImageMedian.readImage(imagePath);
        this.inputImages.add(image);
        this.imageContainer.getChildren().add(new ImageView(image));
    }

    @FXML
    public void save(ActionEvent actionEvent) throws IOException {
        Path imagePath = this.fileChooser.showSaveDialog(null).toPath();
        MeanImageMedian.writeImage(imagePath, this.finalImage);
    }
}
