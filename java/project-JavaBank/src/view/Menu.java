package view;

import java.util.Scanner;

public class Menu {

    public int inputMain(Scanner sc) {
        System.out.println("WELCOME TO JAVABANK");
        System.out.println("1: Log in");
        System.out.println("2. Register");
        System.out.println("3: Exit");

        //return inputControl.getInput(sc, 1, 3);
        return 0;
    }

    public void callMainMenu(Scanner sc) {
        int input = inputMain(sc);
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {

                }
                case 2 -> {

                    break;
                }
                case 3 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call lai main menu
            input = 3;
        } while (input >= 1 && input <= 3);
    }

    public void exitProgram() {
        System.out.println("Thoat chuong trinh. Tam biet.");
        System.exit(0);
    }
}
