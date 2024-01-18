/*
 * Course: CSC1120
 * Fall 2023
 * Lab 1
 * Name: Dr. Taylor
 * Created: 20230619
 */
package mocked;

/**
 * An interface that provides a method to access the value of a specified pixel.
 *
 * @author taylor
 * @version 20230619
 */
public interface PixelReader {
    /**
     * Reads a 32-bit integer representation of the color of a pixel
     * from the specified coordinates in the
     * surface. The 32-bit integer will contain the 4 color components
     * in separate 8-bit fields in ARG
     * order from the most significant byte to the least significant byte.
     * Parameters:
     *
     * @param x the X coordinate of the pixel color to read
     * @param y the Y coordinate of the pixel color to read
     * @return a 32-bit representation of the color in the
     * format described by the INT_ARGB PixelFormat type.
     */
    int getArgb(int x, int y);
}
