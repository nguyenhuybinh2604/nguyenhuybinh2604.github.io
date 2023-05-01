import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Define the correct username and password
        String correctUsername = "user123";
        String correctPassword = "password123";

        Scanner scanner = new Scanner(System.in);

        // Loop until the user enters the correct username and password
        while (true) {

            // Prompt the user to enter their username
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            // If the username is incorrect, go back to the beginning of the loop
            if (!username.equals(correctUsername)) {
                System.out.println("Incorrect username. Please try again.");
                continue;
            }

            // Prompt the user to enter their password
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            // If the password is correct, go to the user's personal screen
            if (password.equals(correctPassword)) {
                System.out.println("Welcome to your personal screen, " + username + "!");
                break;
            }

            // If the password is incorrect, offer the user three options
            System.out.println("Incorrect password. What would you like to do?");
            System.out.println("1. Re-enter your password");
            System.out.println("2. Reset your password");
            System.out.println("3. Go back");

            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    // Re-enter the password (loop back to the beginning of the loop)
                    break;
                case 2:
                    // Reset the password
                    System.out.print("Enter your email address: ");
                    String email = scanner.nextLine();
                    if (email.equals("user123@example.com")) {
                        System.out.print("Enter your new password: ");
                        correctPassword = scanner.nextLine();
                        System.out.println("Password reset successful.");
                    } else {
                        System.out.println("Incorrect email address. Password reset failed.");
                    }
                    break;
                case 3:
                    // Go back (exit the loop)
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}