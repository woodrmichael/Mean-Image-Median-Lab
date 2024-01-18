/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 1 - Mean Image Median
 * Name: Michael Wood
 * Created: 1/18/2024
 */
package woodm;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * TODO
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
    }
}
