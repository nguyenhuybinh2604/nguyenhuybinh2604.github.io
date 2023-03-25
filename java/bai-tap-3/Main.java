import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String flag = "";
        String name = "";
        String address = "";
        int age = 0;
        do {
            System.out.print("Please enter student's name: ");
            name = sc.nextLine();
            System.out.print("Please enter student's address: ");
            address = sc.nextLine();
            System.out.print("Please enter student's age: ");
            age = Integer.parseInt(sc.nextLine());
            System.out.println("Student " + name + " is " + age + " years old and lives at " + address + ".");
            System.out.println("Do you want to continue (Y/N)?");
            flag = sc.nextLine();
        } while (flag.equalsIgnoreCase("Y"));
    }
}
