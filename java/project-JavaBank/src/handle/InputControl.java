package handle;

import entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputControl {

    public int getInput(Scanner sc, Integer min, Integer max) {
        int input;
        String inputString;
        while (true) {
            System.out.print("Enter an integer number " + valueRange(String.valueOf(min), String.valueOf(max)) + ": ");
            inputString = sc.nextLine().trim();

            if (inputString.isEmpty()) {
                System.out.println("Empty line, please enter an integer number "
                        + valueRange(String.valueOf(min), String.valueOf(max)) + ": ");
                continue;
            }

            try {
                input = Integer.parseInt(inputString);
                if (stopCondition(String.valueOf(min), String.valueOf(max), input, null))
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a valid integer number "
                        + valueRange(String.valueOf(min), String.valueOf(max)) + ": ");
            }
        }
        return input;
    }

    public double getInput(Scanner sc, Double min, Double max) {
        double input;
        String inputString;
        while (true) {
            System.out.print("Enter a double number " + valueRange(String.valueOf(min), String.valueOf(max)) + ": ");
            inputString = sc.nextLine().trim();

            if (inputString.isEmpty()) {
                System.out.println("Empty line, please enter a double number "
                        + valueRange(String.valueOf(min), String.valueOf(max)) + ": ");
                continue;
            }

            try {
                input = Double.parseDouble(inputString);
                if (stopCondition(String.valueOf(min), String.valueOf(max), null, input))
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a valid double number "
                        + valueRange(String.valueOf(min), String.valueOf(max)) + ": ");
            }
        }
        return input;
    }

    public String valueRange(String min, String max) {
        if (min.equalsIgnoreCase("null") && max.equalsIgnoreCase("null")) {
            return "";
        } else if (min.equalsIgnoreCase("null")) {
            return "<=" + max;
        } else if (max.equalsIgnoreCase("null")) {
            return ">=" + min;
        } else {
            return "from " + min + " to " + max;
        }
    }

    public boolean stopCondition(String minStr, String maxStr, Integer inputInt, Double inputDouble) {
        // inputDouble truyen vao null -> dang input so integer
        if (inputDouble == null) {
            // Convert 02 so min max truyen vao thanh Int
            Integer min = 0;
            Integer max = 0;
            try {
                if (minStr != null)
                    min = Integer.parseInt(minStr);
            } catch (NumberFormatException e) {
                min = null;
            }
            try {
                if (maxStr != null)
                    max = Integer.parseInt(maxStr);
            } catch (NumberFormatException e) {
                max = null;
            }
            // Tra ve true neu input nam trong range min max
            // min max deu null -> khong gioi han gia tri -> mac dinh la true
            if (min == null && max == null) {
                return true;
            } else if (min == null) {
                return inputInt <= max;
            } else if (max == null) {
                return inputInt >= min;
            } else {
                return inputInt >= min && inputInt <= max;
            }
            // inputInt null -> dang input so double
        } else if (inputInt == null) {
            // Convert 02 so min max truyen vao thanh Double
            Double min = 0.0;
            Double max = 0.0;
            try {
                if (minStr != null)
                    min = Double.parseDouble(minStr);
            } catch (NumberFormatException e) {
                min = null;
            }
            try {
                if (maxStr != null)
                    max = Double.parseDouble(maxStr);
            } catch (NumberFormatException e) {
                max = null;
            }
            // Tra ve true neu input nam trong range min max
            if (min == null && max == null) {
                return true;
            } else if (min == null) {
                return inputDouble <= max;
            } else if (max == null) {
                return inputDouble >= min;
            } else {
                return inputDouble >= min && inputDouble <= max;
            }
        } else
            return false;
    }

    public String getEmail(Scanner sc) {
        String email = sc.nextLine();
        while (email == null || !emailVerify(email)) {
            System.out.println("Format of email: emailaddress@domain.com:");
            email = sc.nextLine();
        }
        return email;
    }

    public boolean emailVerify(String email) {
        if (email != null) {
            Pattern emailFormat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);
            Matcher hasFormat = emailFormat.matcher(email);
            return hasFormat.matches();
        } else
            return false;
    }

    public String getPassword(Scanner sc) {
        String password = sc.nextLine();
        while (password == null || !passwordVerify(password)) {
            System.out.println(
                    "Password must be 7-15-character long, contain at least 01 capitalized letter and 01 special character:");
            password = sc.nextLine();
        }
        return password;
    }

    // password dài từ 7 đến 15 ký tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt
    // (. , - _ ;)
    public boolean passwordVerify(String password) {
        if (password.length() >= 7 && password.length() <= 15) {
            Pattern capitalized = Pattern.compile("[A-Z]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher hasCapitalized = capitalized.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasCapitalized.find() && hasSpecial.find();

        } else
            return false;
    }

    public LocalDate getLocalDate(Scanner sc) {
        // Prompt the user to enter a date in the expected format
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;
        while (date == null) {
            String input = sc.nextLine();

            try {
                // Parse the user input as a LocalDate object
                date = LocalDate.parse(input, fmtDate);
            } catch (DateTimeParseException e) {
                // Handle the exception if the user input is invalid
                System.out.println("Invalid date. Enter a date in the dd/MM/yyyy format:");
            }
        }
        return date;
    }

    public String getNonEmptyString(Scanner sc) {
        String returnString = sc.nextLine().trim();
        while (returnString.isEmpty()) {
            System.out.print("Enter a non-empty string: ");
            returnString = sc.nextLine().trim();
        }
        return returnString;
    }
}
