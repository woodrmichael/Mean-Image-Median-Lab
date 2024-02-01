package woodm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Button addImageButton;
    @FXML
    private ImageView imageView;
    private ArrayList<Image> inputImages = new ArrayList<>();

    @FXML
    public void inputImage(ActionEvent actionEvent) throws IOException {
        //getExtensionFilters .addAll new ExtensionFilter // filter to .ppm, .png, .jpg
        FileChooser fileChooser = new FileChooser();
        Path imagePath = fileChooser.showOpenDialog(null).toPath();
        Image image = MeanImageMedian.readImage(imagePath);
        this.inputImages.add(image);
        this.imageView.setX(image.getWidth());
        this.imageView.setY(image.getHeight());
        this.imageView.setImage(image);
    }
}
