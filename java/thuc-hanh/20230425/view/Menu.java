package view;

import java.util.Scanner;

import entity.School;
import handle.InputControl;
import handle.StudentHandle;

public class Menu {
    Scanner sc = new Scanner(System.in);
    InputControl inputControl = new InputControl();
    StudentHandle studentHandle = new StudentHandle();
    School school = new School();

    public int inputMain() {
        System.out.println("Please select:");
        System.out.println("1. Input data");
        System.out.println("2. View list of students - born In 1985 from Thai Nguyen");
        System.out.println("3. View list of students - in group 10A1");
        System.out.println("4. Exit");

        return inputControl.getInput(sc, 1, 4);
    }

    public void callMainMenu() {
        int input = inputMain();
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    studentHandle.inputData(sc, inputControl, school);
                    break;
                }
                case 2 -> {
                    studentHandle.displayStudents1(school);
                    break;
                }
                case 3 -> {
                    studentHandle.displayStudents2(school);
                    break;
                }
                case 4 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call lai main menu
            input = inputMain();
        } while (input >= 1 && input <= 4);
    }

    public void exitProgram() {
        System.out.println("Exiting. Goodbye.");
        System.exit(0);
    }
}
