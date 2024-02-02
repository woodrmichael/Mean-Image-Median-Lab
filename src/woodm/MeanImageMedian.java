/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 1 - Mean Image Median Lab
 * Name: Michael Wood
 * Created: 1/18/2024
 */
package woodm;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The MeanImageMedian class offers multiple methods to generate mean and median images
 * of 2 or more images.
 */
public class MeanImageMedian {
    
    /**
     * Maximum color value
     */
    public static final int MAX_COLOR = 255;
    private static final int MINIMUM_FILE_LENGTH = 5;

    /**
     * Calculates the median of all the images passed to the method.
     * <br />
     * Each pixel in the output image consists is calculated as the median
     * red, green, and blue components of the input images at the same location.
     * @param inputImages Images to be used as input
     * @return An image containing the median color value for each pixel in the input images
     *
     * @throws IllegalArgumentException Thrown if inputImages or any element of inputImages is null,
     * the length of the array is less than two, or  if any of the input images differ in size.
     */
    public static Image calculateMedianImage(Image[] inputImages) {
        checkInputImages(inputImages);
        int width = (int) inputImages[0].getWidth();
        int height = (int) inputImages[0].getHeight();
        WritableImage image = new WritableImage(width, height);
        for(int row = 0; row < width; row++) {
            for(int col = 0; col < height; col++) {
                int[][] arr = getARGBArrays(inputImages, row, col);
                int a = calculateMedian(arr[0]);
                int r = calculateMedian(arr[1]);
                int g = calculateMedian(arr[2]);
                int b = calculateMedian(arr[3]);
                image.getPixelWriter().setArgb(row, col, argbToInt(a, r, g, b));
            }
        }
        return image;
    }

    /**
     * Calculates the mean of all the images passed to the method.
     * <br />
     * Each pixel in the output image consists is calculated as the average of the
     * red, green, and blue components of the input images at the same location.
     * @param inputImages Images to be used as input
     * @return An image containing the mean color value for each pixel in the input images
     *
     * @throws IllegalArgumentException Thrown if inputImages or any element of inputImages is null,
     * the length of the array is less than two, or  if any of the input images differ in size.
     */
    public static Image calculateMeanImage(Image[] inputImages) {
        checkInputImages(inputImages);
        int width = (int) inputImages[0].getWidth();
        int height = (int) inputImages[0].getHeight();
        WritableImage image = new WritableImage(width, height);
        for(int row = 0; row < width; row++) {
            for(int col = 0; col < height; col++) {
                int a = 0;
                int r = 0;
                int g = 0;
                int b = 0;
                for (Image inputImage : inputImages) {
                    int argb = inputImage.getPixelReader().getArgb(row, col);
                    a += argbToAlpha(argb);
                    r += argbToRed(argb);
                    g += argbToGreen(argb);
                    b += argbToBlue(argb);
                }
                a = (int) Math.round(((double) a) / inputImages.length);
                r = (int) Math.round(((double) r) / inputImages.length);
                g = (int) Math.round(((double) g) / inputImages.length);
                b = (int) Math.round(((double) b) / inputImages.length);
                image.getPixelWriter().setArgb(row, col, argbToInt(a, r, g, b));
            }
        }
        return image;
    }
    /**
     * Calculates the darker or lighter image of all the images passed to the method.
     * <br />
     * Each pixel in the output image consists is calculated as either the lighter or darker
     * red, green, and blue components of the input images at the same location.
     * @param inputImages Images to be used as input
     * @param transformation the type of transformation, either 'Darker' or 'Lighter'
     * @return An image containing the lighter or darker value for each pixel in the input images
     *
     * @throws IllegalArgumentException Thrown if inputImages or any element of inputImages is null,
     * the length of the array is less than two, or  if any of the input images differ in size.
     */
    public static Image transformColorChange(Image[] inputImages, String transformation) {
        checkInputImages(inputImages);
        int width = (int) inputImages[0].getWidth();
        int height = (int) inputImages[0].getHeight();
        WritableImage image = new WritableImage(width, height);
        for(int row = 0; row < width; row++) {
            for(int col = 0; col < height; col++) {
                int[][] arr = getARGBArrays(inputImages, row, col);
                int a = calculateColorChange(arr[0], transformation);
                int r = calculateColorChange(arr[1], transformation);
                int g = calculateColorChange(arr[2], transformation);
                int b = calculateColorChange(arr[3], transformation);
                image.getPixelWriter().setArgb(row, col, argbToInt(a, r, g, b));
            }
        }
        return image;
    }

    /**
     * Reads an image in PPM, PNG, or JPG format
     * @param imagePath the path to the image to be read
     * @return An image object containing the image read from the file.
     *
     * @throws IllegalArgumentException Thrown if imagePath is null.
     * @throws IOException Thrown if the image format is invalid or
     * there was trouble reading the file.
     */
    public static Image readImage(Path imagePath) throws IOException {
        checkImagePath(imagePath);
        String path = imagePath.toString();
        if(path.length() < MINIMUM_FILE_LENGTH) {
            throw new IOException("Please ensure the image paths have lengths greater than 4");
        }
        String fileExtension = path.substring(path.length() - 4);
        Image image;
        if(fileExtension.equals(".ppm")) {
            image = readPPMImage(imagePath);
        } else if(fileExtension.equals(".png") || fileExtension.equals(".jpg")) {
            image = new Image(new FileInputStream(imagePath.toFile()));
        } else {
            throw new IllegalArgumentException(
                    "Please ensure the image path has extension '.ppm', '.png', or 'jpg'");
        }
        return image;
    }

    /**
     * Writes an image in PPM, PNG, or JPG format
     * @param imagePath the path to where the file should be written
     * @param image the image containing the pixels to be written to the file
     *
     * @throws IllegalArgumentException Thrown if imagePath is null.
     * @throws IOException Thrown if the image format is invalid or
     * there was trouble reading the file.
     */
    public static void writeImage(Path imagePath, Image image) throws IOException {
        checkImagePath(imagePath);
        String path = imagePath.toString();
        if(path.length() < MINIMUM_FILE_LENGTH) {
            throw new IOException("Please ensure the image paths have lengths greater than 4");
        }
        if(image == null) {
            throw new IllegalArgumentException("Please ensure the image is not null");
        }
        String fileExtension = path.substring(path.length() - 4);
        if(fileExtension.equals(".ppm")) {
            writePPMImage(imagePath, image);
        } else if(fileExtension.equals(".png") || fileExtension.equals(".jpg")) {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null),
                    fileExtension.substring(1), imagePath.toFile());
        } else {
            throw new IllegalArgumentException(
                    "Please ensure the image path has extension '.ppm', '.png', or 'jpg'");
        }
    }

    /**
     * Reads an image in PPM format. The method only supports the plain PPM (P3)
     * format with 24-bit color and does not support comments in the image file.
     * @param imagePath the path to the image to be read
     * @return An image object containing the image read from the file.
     *
     * @throws IllegalArgumentException Thrown if imagePath is null.
     * @throws IOException Thrown if the image format is invalid or
     * there was trouble reading the file.
     */
    private static Image readPPMImage(Path imagePath) throws IOException {
        checkImagePath(imagePath);
        if(!imagePath.toString().endsWith(".ppm")) {
            throw new IOException("Please ensure the image path has extension '.ppm'");
        }
        try (Scanner reader = new Scanner(imagePath)) {
            if(!reader.next().equals("P3")) {
                throw new IOException(
                        "Invalid image format. The first non-comment line is not 'P3'");
            }
            int width = reader.nextInt();
            int height = reader.nextInt();
            WritableImage image = new WritableImage(width, height);
            if(reader.nextInt() != MAX_COLOR) {
                throw new IOException(
                        "Invalid image format. The max value was not equal to " + MAX_COLOR);
            }
            for(int col = 0; col < height; col++) {
                for(int row = 0; row < width; row++) {
                    int r = reader.nextInt();
                    int g = reader.nextInt();
                    int b = reader.nextInt();
                    int argb = argbToInt(MAX_COLOR, r, g, b);
                    image.getPixelWriter().setArgb(row, col, argb);
                }
            }
            return image;
        }
    }

    /**
     * Writes an image in PPM format. The method only supports the plain PPM (P3)
     * format with 24-bit color and does not support comments in the image file.
     * @param imagePath the path to where the file should be written
     * @param image the image containing the pixels to be written to the file
     *
     * @throws IllegalArgumentException Thrown if imagePath is null.
     * @throws IOException Thrown if the image format is invalid or
     * there was trouble reading the file.
     */
    private static void writePPMImage(Path imagePath, Image image) throws IOException {
        checkImagePath(imagePath);
        if(!imagePath.toString().endsWith(".ppm")) {
            throw new IOException("Please ensure the image path has extension '.ppm'");
        }
        try (PrintWriter writer = new PrintWriter(imagePath.toFile())) {
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            writer.println("P3");
            writer.println(width + " " + height);
            writer.println(MAX_COLOR);
            for(int col = 0; col < height; col++) {
                for(int row = 0; row < width; row++) {
                    int argb = image.getPixelReader().getArgb(row, col);
                    int r = argbToRed(argb);
                    int g = argbToGreen(argb);
                    int b = argbToBlue(argb);
                    writer.format("%3d %3d %3d   ", r, g, b);
                }
                writer.println();
            }
        }
    }

    /**
     * Extract 8-bit Alpha value of color from 32-bit representation of the color in the format
     * described by the INT_ARGB PixelFormat type.
     * @param argb the 32-bit representation of the color
     * @return the 8-bit Alpha value of the color.
     */
    private static int argbToAlpha(int argb) {
        final int bitShift = 24;
        return argb >> bitShift;
    }

    /**
     * Extract 8-bit Red value of color from 32-bit representation of the color in the format
     * described by the INT_ARGB PixelFormat type.
     * @param argb the 32-bit representation of the color
     * @return the 8-bit Red value of the color.
     */
    private static int argbToRed(int argb) {
        final int bitShift = 16;
        final int mask = 0xff;
        return (argb >> bitShift) & mask;
    }

    /**
     * Extract 8-bit Green value of color from 32-bit representation of the color in the format
     * described by the INT_ARGB PixelFormat type.
     * @param argb the 32-bit representation of the color
     * @return the 8-bit Green value of the color.
     */
    private static int argbToGreen(int argb) {
        final int bitShift = 8;
        final int mask = 0xff;
        return (argb >> bitShift) & mask;
    }

    /**
     * Extract 8-bit Blue value of color from 32-bit representation of the color in the format
     * described by the INT_ARGB PixelFormat type.
     * @param argb the 32-bit representation of the color
     * @return the 8-bit Blue value of the color.
     */
    private static int argbToBlue(int argb) {
        final int bitShift = 0;
        final int mask = 0xff;
        return (argb >> bitShift) & mask;
    }

    /**
     * Converts argb components into a single int that represents the argb value of a color.
     * @param a the 8-bit Alpha channel value of the color
     * @param r the 8-bit Red channel value of the color
     * @param g the 8-bit Green channel value of the color
     * @param b the 8-bit Blue channel value of the color
     * @return a 32-bit representation of the color in the format
     * described by the INT_ARGB PixelFormat type.
     */
    private static int argbToInt(int a, int r, int g, int b) {
        final int alphaShift = 24;
        final int redShift = 16;
        final int greenShift = 8;
        final int mask = 0xff;
        return a << alphaShift | ((r & mask) << redShift) | (g & mask) << greenShift | b & mask;
    }

    /**
     * Determines if the list of inputImages is valid
     * The list is valid if no elements in the list are null, the size of the list
     * is greater than 2, and all elements have the same widths and heights as other elements.
     * @param inputImages the list to validate
     * @throws IllegalArgumentException Thrown if there is less than 2 input images,
     * any of the input images are null, or any of the input images have different dimensions
     */
    private static void checkInputImages(Image[] inputImages) {
        StringBuilder message = new StringBuilder();
        boolean flag = false;
        if(inputImages.length < 2) {
            flag = true;
            message.append("Please ensure there are at least 2 input images. ");

        }
        for (Image inputImage : inputImages) {
            if (inputImage == null) {
                if(!flag) {
                    message.append("Please ensure that none of the input images are null. ");
                }
                flag = true;
            }
        }
        if(!flag) {
            int width = (int) inputImages[0].getWidth();
            int height = (int) inputImages[0].getHeight();
            for(int i = 1; i < inputImages.length; i++) {
                if(width != (int) inputImages[i].getWidth() ||
                        height != (int) inputImages[i].getHeight()) {
                    if(!flag) {
                        message.append(
                                "Please ensure that all input images have the same dimensions");
                    }
                    flag = true;
                }
            }
        }
        if(flag) {
            throw new IllegalArgumentException(message.toString());
        }
    }

    /**
     * Sorts the given array and returns the median
     * @param arr the given array
     * @return the median value of the array
     */
    private static int calculateMedian(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length;
        int median;
        if(len % 2 == 1) {
            median = arr[len / 2];
        } else {
            median = (int) Math.round((arr[len / 2] + arr[len / 2 - 1]) / 2.0);
        }
        return median;
    }

    /**
     * Checks whether the image path is null
     * @param imagePath the image path to be checked
     * @throws IllegalArgumentException thrown if the image path is null
     */
    private static void checkImagePath(Path imagePath) {
        if(imagePath == null) {
            throw new IllegalArgumentException("Please ensure the image path is not null");
        }
    }

    /**
     * Calculates the darker or lighter pixel depending on the type of transformation
     * @param arr the array of pixels
     * @param transformation either 'Darker' or 'Lighter'
     * @return a pixel value for the darker or lighter pixel
     */
    private static int calculateColorChange(int[] arr, String transformation) {
        Arrays.sort(arr);
        int value;
        if(transformation.equals("Darker")) {
            value = arr[0];
        } else {
            value = arr[arr.length - 1];
        }
        return value;
    }

    /**
     * Gets arrays of Alpha, Red, Green, and Blue values to be used for
     * median calculations and other transformations
     * @param inputImages Images to be used as input
     * @param row the current row for the pixels
     * @param col the current column for the pixels
     * @return a 2D array containing arrays containing each Alpha, Red, Green, and Blue pixel
     * values for each image at a specified row and column.
     */
    private static int[][] getARGBArrays(Image[] inputImages, int row, int col) {
        int[] alpha = new int[inputImages.length];
        int[] red = new int[inputImages.length];
        int[] green = new int[inputImages.length];
        int[] blue = new int[inputImages.length];
        for(int i = 0; i < inputImages.length; i++) {
            int argb = inputImages[i].getPixelReader().getArgb(row, col);
            alpha[i] = argbToAlpha(argb);
            red[i] = argbToRed(argb);
            green[i] = argbToGreen(argb);
            blue[i] = argbToBlue(argb);
        }
        int[][] arr = new int[4][];
        arr[0] = alpha;
        arr[1] = red;
        arr[2] = green;
        arr[3] = blue;
        return arr;
    }
}
