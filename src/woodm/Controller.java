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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The Controller class is used to control the layout.fxml GUI
 */
public class Controller {
    @FXML
    private HBox imageContainer;
    @FXML
    private ImageView finalImageView;
    @FXML
    private Label messageBox;
    @FXML
    private Button meanButton;
    @FXML
    private Button medianButton;
    @FXML
    private Button minButton;
    @FXML
    private Button maxButton;
    @FXML
    private Button randomButton;
    @FXML
    private Button saveButton;
    private Image finalImage;
    private final List<Image> inputImages;
    private final FileChooser fileChooser;
    private final Alert alert;

    /**
     * Creates a new Controller with a FileChooser that has ExtensionFilters
     * and an ArrayList of Images
     */
    public Controller() {
        this.fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files", "*.png", "*.jpg", "*.ppm", "*.msoe"));
        this.inputImages = new ArrayList<>();
        this.alert = new Alert(Alert.AlertType.WARNING);
    }

    /**
     * Adds the images the user wants to the scroll pane at the top of the window
     * with a remove button below to remove the image if needed
     */
    @FXML
    private void inputImages() {
        this.messageBox.setText("");
        final int inputImageWidth = 240;
        final int inputImageHeight = 170;
        try {
            List<File> imageFiles = this.fileChooser.showOpenMultipleDialog(null);
            if(imageFiles != null) {
                for (File imageFile : imageFiles) {
                    Path imagePath = imageFile.toPath();
                    Image image = MeanImageMedian.readImage(imagePath);
                    this.inputImages.addFirst(image);
                    VBox imagePanel = new VBox();
                    this.imageContainer.getChildren().addFirst(imagePanel);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(inputImageWidth);
                    imageView.setFitHeight(inputImageHeight);
                    HBox bottomContainer = new HBox();
                    Button removeButton = new Button("Remove");
                    removeButton.setOnAction(this::removeImage);
                    Label dimensionLabel = new Label(imagePath.getFileName() + "\nDimensions: " +
                            (int) image.getWidth() + " x " + (int) image.getHeight());
                    bottomContainer.getChildren().addAll(removeButton, dimensionLabel);
                    imagePanel.getChildren().addAll(imageView, bottomContainer);
                    if(this.inputImages.size() >= 2) {
                        setDisableTransformationButtons(false);
                    }
                }
            }
        } catch(IOException | IllegalArgumentException e) {
            this.alert.setContentText(e.getMessage());
            this.alert.showAndWait();
        }
    }

    /**
     * Enables the transformation buttons to be able to be pressed.
     * @param b true if the buttons should be disabled, false if the buttons should be enabled
     */
    private void setDisableTransformationButtons(boolean b) {
        this.meanButton.setDisable(b);
        this.medianButton.setDisable(b);
        this.minButton.setDisable(b);
        this.maxButton.setDisable(b);
        this.randomButton.setDisable(b);
    }

    /**
     * Saves the image to a file at the users requested location
     */
    @FXML
    private void save() {
        this.messageBox.setText("");
        try {
            File imageFile = this.fileChooser.showSaveDialog(null);
            if(imageFile != null) {
                Path imagePath = imageFile.toPath();
                MeanImageMedian.writeImage(imagePath, this.finalImage);
                this.messageBox.setText(
                        "Successfully saved file to: " + imagePath.toAbsolutePath());
            }
        } catch(IOException | IllegalArgumentException e) {
            this.alert.setContentText(e.getMessage());
            this.alert.showAndWait();
        }
    }

    /**
     * Calculates the final image based on which button is pressed to get the transformation type
     * Displays this image at the bottom of the screen.
     * @param actionEvent an ActionEvent object used to get the source of the event
     */
    @FXML
    private void generateFinalImage(ActionEvent actionEvent) {
        this.messageBox.setText("");
        Button button = (Button) actionEvent.getSource();
        Image[] inputImages = new Image[this.inputImages.size()];
        try {
            this.finalImage = MeanImageMedian.generateImage(
                    this.inputImages.toArray(inputImages), button.getText());
        } catch(IllegalArgumentException e) {
            this.alert.setContentText(e.getMessage());
            this.alert.showAndWait();
        }
        this.finalImageView.setImage(this.finalImage);
        this.saveButton.setDisable(false);
    }

    /**
     * Removes the desired image from the input images
     * @param actionEvent an ActionEvent object used to get the source of the event
     */
    private void removeImage(ActionEvent actionEvent) {
        this.messageBox.setText("");
        Button removeButton = (Button) actionEvent.getSource();
        VBox imagePanel = (VBox) removeButton.getParent().getParent();
        this.inputImages.remove(this.imageContainer.getChildren().indexOf(imagePanel));
        this.imageContainer.getChildren().remove(imagePanel);
        if(this.inputImages.size() < 2) {
            setDisableTransformationButtons(true);
        }
    }

    /**
     * Clears all the text fields, input images, and the final image displayed at the bottom
     */
    @FXML
    private void clear() {
        this.messageBox.setText("");
        this.imageContainer.getChildren().clear();
        this.inputImages.clear();
        this.finalImageView.setImage(null);
        this.finalImage = null;
        setDisableTransformationButtons(true);
        this.saveButton.setDisable(true);
    }
}
