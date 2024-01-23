/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 1 - Mean Image Median
 * Name: Michael Wood
 * Created: 1/18/2024
 */
package woodm;

import mocked.Image;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the main method to generate a mean or median image or 2 or more images.
 */
public class Lab1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.print("mean or median?: ");
            choice = in.nextLine().toLowerCase();
        } while(!choice.equals("mean") && !choice.equals("median"));
        System.out.print("Which file would you like to output to?: ");
        String output = in.nextLine();
        Path outputPath = Path.of(output);
        ArrayList<String> inputs = new ArrayList<>();
        System.out.println("Enter at least 2 input files: (-1 to quit)");
        String input;
        do {
            System.out.print("Input File " + (inputs.size() + 1) + ": ");
            input = in.nextLine();
            if(!input.equals("-1")) {
                inputs.add(input);
            }
        } while(!input.equals("-1"));
        Image[] inputImages = new Image[inputs.size()];
        try {
            for(int i = 0; i < inputs.size(); i++) {
                inputImages[i] = MeanImageMedian.readPPMImage(Path.of(inputs.get(i)));
            }
            Image finalImage = null;
            try {
                if(choice.equals("mean")) {
                    finalImage = MeanImageMedian.calculateMeanImage(inputImages);
                } else {
                    finalImage = MeanImageMedian.calculateMedianImage(inputImages);
                }
            } catch (IllegalArgumentException e) {
                if(inputImages.length < 2) {
                    System.out.println("Please enter at least 2 input files.");
                } else {
                    System.out.println(
                            "Please ensure that your input images have the same width and height");
                }
            }
            MeanImageMedian.writePPMImage(outputPath, finalImage);
            System.out.println("Your output image can be found at " + outputPath.toAbsolutePath());
        } catch (IllegalArgumentException e) {
            System.out.println("Please ensure that your input paths are not null.");
        } catch (IOException e) {
            System.out.println("File could not be read or file does not take the type 'ppm'.");
        }

    }
}
