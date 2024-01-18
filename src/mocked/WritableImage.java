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
 * Allows creation of and writing to an Image.
 *
 * @author taylor@msoe.edu
 * @version 20230827
 */
public class WritableImage extends Image {

    /**
     * Creates a writable image with specified dimensions
     * @param width The width of the image
     * @param height The height of the image
     */
    public WritableImage(int width, int height) {
        pixels = new int[width][height];
    }

    /**
     * Returns a PixelWriter that allows pixel values in the image to be modified.
     * @return A pixel writer for the image
     */
    public PixelWriter getPixelWriter() {
        return (x, y, argb) -> pixels[x][y] = argb;
    }
}
