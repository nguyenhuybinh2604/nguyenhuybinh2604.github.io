import entity.Administrator;
import entity.Manager;
import entity.Marketer;
import io.InputData;
import view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();

        System.out.println("Hello world.");
        InputData inputData = new InputData();
        inputData.readExcel(administrators,managers, marketers);


    }
}