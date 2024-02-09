# CSC1120 Labs 1, 2, and 3

Each week you will be required to complete multiple commits that
build towards a complete lab solution. Note the due dates associated
with each commit.

## [Link to Lab 1 assignment](https://csse.msoe.us/csc1120/lab1)

* [x] Commit 1 "Rename package"
    - Rename the package to your MSOE woodm
* [x] Commit 2 "User input"
  Create a class called `Lab1` with a `main()` method that gathers the
  required user input and displays it to the console. You may either accept the user
  input as command line arguments or prompt the user for the appropriate input via the console.
* [x] Commit 3 "Read PPM Image"
    - Implement the **`MeanImageMedian.readPPMImage(Path imagePath)`** method
    - Modify `main()` so that it reads all of the input images specified by the user and
      displays the size of each image.
* [x] Commit 4 "Write PPM Image"
    - Implement the **`MeanImageMedian.writePPMImage(Path imagePath)`** method
    - Modify `main()` so that it writes the first image input image specified by the user to
      the output image specified by the user. The output image should be identical to the input
      image.
* [x] Commit 5 "Lab completed"
    - Implement the remaining requirements for the lab assignment.

Remove the dashes on this line when lab 1 is ready to be graded (and commit and push): DONE1

## [Link to Lab 2 assignment](https://csse.msoe.us/csc1120/lab2)

* [x] Commit 1 "JavaFX Application"
    - Install JavaFX
    - Create a `Lab2` class that extends `Application`
* [x] Commit 2 "JavaFX Window"
    - Implement the `start(Stage stage)` method so that the dimensions of the first input
      image are displayed in the title of graphical window
* [x] Commit 3 "Jar file"
    - Create an executable `.jar` file, place it in the project folder (same folder as this
      file), and rename it `lab2.jar`.
* [x] Commit 4 "Command line arguments"
    - If your program already supports command line arguments, skip this commit. Otherwise,
      modify the program to support command line arguments for all user input.
* [x] Commit 5 "Lab completed"
    - Update your code based on feedback from your instructor (if available)
    - Finish all assignment requirements.
    - Update the `.jar` file by rebuilding it and copying it to the project folder.

Remove the dashes on this line when lab 2 is ready to be graded (and commit and push): DONE2

## [Link to Lab 3 assignment](https://csse.msoe.us/csc1120/lab3)

* [x] Commit 1 "FXML shell"
    - Create the FXML file needed to support the GUI functionality
    - Create the `Lab3` class that loads the FXML file and displays the GUI
* [x] Commit 2 "Display Image"
    - Update the `MeanImageMedian` class so that it loads the FXML file
    - Minimally implement the `Controller` class so that the program displays the first input
      image loaded.
* [x] Commit 3 "Input images"
    - Design a technique to specify how many input images you wish to load and how to specify them
    - You click the add image button everytime you want to add an image, then click save as once you are finished and want to save your image. You can add as many images as your computer can handle as long as it is >= 2.
* [x] Commit 4 "Lab completed"
    - Update your code based on feedback from your instructor (if available)
    - Finish all assignment requirements.

Remove the dashes on this line when lab 3 is ready to be graded (and commit and push): DONE3

## [Link to Lab 5 assignment](https://csse.msoe.us/csc1120/lab5)

* [x] Commit 1 "Feedback and Deprecation"
    - Make updates based on feedback from your instructor on labs 1 - 3
    - Deprecate the `calculateMeanImage()` and `calculateMedianImage()` methods
* [x] Commit 2 "Functional Interface"
    - Declare the `Transform` functional interface.
* [x] Commit 3 "Apply Transformation"
    - Implement the `generateImage()` and `applyTransform()` methods.
    - Update button handlers for Mean and Median to call `generateImage()`
* [x] Commit 4 "Read/Write .msoe"
    - Implement `readMSOEImage()` and `writeMSOEImage()`
* [x] Commit 5 "Lab completed"
    - Add buttons and functionality for Max, Min, and Random.
    - Finish all assignment requirements.

Remove the dashes on this line when lab 5 is ready to be graded (and commit and push): DONE5
