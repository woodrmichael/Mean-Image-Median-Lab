/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 5 - Mean Image Median Lab
 * Name: Michael Wood
 * Created: 2/8/2024
 */
package woodm;

@FunctionalInterface
public interface Transform {
    int apply(int[] arr);
}
