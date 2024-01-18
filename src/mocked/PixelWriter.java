/*
 * Course: CSC1120
 * Fall 2023
 * Lab 1
 * Name: Dr. Taylor
 * Created: 20230619
 */
package mocked;

/**
 * An interface that provides a method to change the value of a specified pixel.
 *
 * @author taylor
 * @version 20230619
 */
public interface PixelWriter {
    /**
     * Stores pixel data for a color into the specified coordinates of the surface.
     * The 32-bit integer argb
     * parameter should contain the 4 color components in separate 8-bit fields
     * in ARGB order from the most
     * significant byte to the least significant byte.
     * Parameters:
     *
     * @param x    the X coordinate of the pixel color to write
     * @param y    the Y coordinate of the pixel color to write
     * @param argb the color information to write, specified in
     *             the format described by the INT_ARGB PixelFormat type.
     */
    void setArgb(int x, int y, int argb);

}
