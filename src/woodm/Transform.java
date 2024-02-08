/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 5 - Mean Image Median Lab
 * Name: Michael Wood
 * Created: 2/8/2024
 */
package woodm;

/**
 * A Functional Interface used to apply a transformation to an array of integers
 */
@FunctionalInterface
public interface Transform {
    /**
     * Applies a transformation to an array of integers
     * @param arr the array of integers to do the transformation on
     * @return the integer value of the transformation that had it applied to
     */
    int apply(int[] arr);
}
