/*
 * Course: CSC1120
 * Fall 2023
 * Lab 1
 * Name: Dr. Taylor
 * Created: 20230619
 */
 package mocked;

/**
 * Provides a simple representation of a 2D image.
 * This class provides a read-only view of the image.
 *
 * @author taylor@msoe.edu
 * @version 20230827
 */
public class Image {
    protected int[][] pixels;

    /**
     * Returns a PixelReader to allow access to the pixel values.
     *
     * @return a PixelReader for the image
     */
    public PixelReader getPixelReader() {
        return (x, y) -> pixels[x][y];
    }

    /**
     * Returns the height of the image
     *
     * @return The height of the image in pixels
     */
    public double getHeight() {
        return pixels.length == 0 ? 0 : pixels[0].length;
    }

    /**
     * Returns the width of the image
     *
     * @return The width of the image in pixels
     */
    public double getWidth() {
        return pixels.length;
    }
}
