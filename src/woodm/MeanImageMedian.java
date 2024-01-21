/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 1 - Mean Image Median Lab
 * Name: Michael Wood
 * Created: 1/18/2024
 */
package woodm;

import mocked.Image;
import mocked.WritableImage;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;


/**
 * TODO
 */
public class MeanImageMedian {
    
    /**
     * Maximum color value
     */
    public static final int MAX_COLOR = 255;

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
        return null;
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
        return null;
    }

    /**
     * Reads an image in PPM format. The method only supports the plain PPM (P3) format with 24-bit color
     * and does not support comments in the image file.
     * @param imagePath the path to the image to be read
     * @return An image object containing the image read from the file.
     *
     * @throws IllegalArgumentException Thrown if imagePath is null.
     * @throws IOException Thrown if the image format is invalid or there was trouble reading the file.
     */
    public static Image readPPMImage(Path imagePath) throws IOException {
        if(imagePath == null) {
            throw new IllegalArgumentException();
        }
        if(!imagePath.toString().endsWith(".ppm")) {
            throw new IOException();
        }
        try (Scanner reader = new Scanner(imagePath)) {
            reader.next();
            int width = reader.nextInt();
            int height = reader.nextInt();
            WritableImage image = new WritableImage(width, height);
            reader.nextInt();
            for(int j = 0; j < height; j++) {
                for(int i = 0; i < width; i++) {
                    int r = reader.nextInt();
                    int g = reader.nextInt();
                    int b = reader.nextInt();
                    int argb = argbToInt(MAX_COLOR, r, g, b);
                    image.getPixelWriter().setArgb(i, j, argb);
                }
            }
            return image;
        }
    }

    /**
     * Writes an image in PPM format. The method only supports the plain PPM (P3) format with 24-bit color
     * and does not support comments in the image file.
     * @param imagePath the path to where the file should be written
     * @param image the image containing the pixels to be written to the file
     *
     * @throws IllegalArgumentException Thrown if imagePath is null.
     * @throws IOException Thrown if the image format is invalid or there was trouble reading the file.
     */
    public static void writePPMImage(Path imagePath, Image image) throws IOException {
        if(imagePath == null) {
            throw new IllegalArgumentException();
        }
        if(!imagePath.toString().endsWith(".ppm")) {
            throw new IOException();
        }
        try (PrintWriter writer = new PrintWriter(imagePath.toFile())) {
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            writer.println("P3");
            writer.println(width + " " + height);
            writer.println(MAX_COLOR);
            for(int j = 0; j < height; j++) {
                for(int i = 0; i < width; i++) {
                    int argb = image.getPixelReader().getArgb(i, j);
                    int r = argbToRed(argb);
                    int g = argbToGreen(argb);
                    int b = argbToBlue(argb);
                    writer.format("%3d %3d %3d  ", r, g, b);
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
     * @return a 32-bit representation of the color in the format described by the INT_ARGB PixelFormat type.
     */
    private static int argbToInt(int a, int r, int g, int b) {
        final int alphaShift = 24;
        final int redShift = 16;
        final int greenShift = 8;
        final int mask = 0xff;
        return a << alphaShift | ((r & mask) << redShift) | (g & mask) << greenShift | b & mask;
    }
}
