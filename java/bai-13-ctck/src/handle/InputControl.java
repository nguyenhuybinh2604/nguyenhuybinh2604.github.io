package handle;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputControl {
    public int getInput(Scanner sc, Integer min, Integer max) {
        int input;
        do {
            System.out.println("Hay nhap mot so " + valueRange(String.valueOf(min), String.valueOf(max)) + ":");
            // Yeu cau gia tri la so
            while (!sc.hasNextInt()) {
                System.out.println("Hay nhap mot so " + valueRange(String.valueOf(min), String.valueOf(max)) + ":");
                sc.nextLine();
            }
            input = Integer.parseInt(sc.nextLine());
        } while (!stopCondition(String.valueOf(min), String.valueOf(max), input, null));
        return input;
    }

    public double getInput(Scanner sc, Double min, Double max) {
        double input;
        do {
            System.out.println("Hay nhap mot so " + valueRange(String.valueOf(min), String.valueOf(max)) + ":");
            // Yeu cau gia tri la so
            while (!sc.hasNextDouble()) {
                System.out.println("Hay nhap mot so " + valueRange(String.valueOf(min), String.valueOf(max)) + ":");
                sc.nextLine();
            }
            input = Double.parseDouble(sc.nextLine());
        } while (!stopCondition(String.valueOf(min), String.valueOf(max), null, input));
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
            return "tu " + min + " den " + max;
        }
    }

    public boolean stopCondition(String minStr, String maxStr, Integer inputInt, Double inputDouble) {
        if (inputDouble == null) {
            //Convert 02 so min max truyen vao thanh Int
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
            //Tra ve true neu input nam trong range min max
            if (min == null && max == null) {
                return true;
            } else if (min == null) {
                if (inputInt <= max) {
                    return true;
                } else {
                    return false;
                }
            } else if (max == null) {
                if (inputInt >= min) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (inputInt >= min && inputInt <= max) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            //Convert 02 so min max truyen vao thanh Double
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
            //Tra ve true neu input nam trong range min max
            if (min == null && max == null) {
                return true;
            } else if (min == null) {
                if (inputDouble <= max) {
                    return true;
                } else {
                    return false;
                }
            } else if (max == null) {
                if (inputDouble >= min) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (inputDouble >= min && inputDouble <= max) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public String getEmail(Scanner sc) {
        String email = sc.nextLine();
        while (email == null || !emailVerify(email)) {
            System.out.println("Yeu cau email dang emailaddress@domain.com:");
            email = sc.nextLine();
        }
        return email;
    }

    public boolean emailVerify(String email) {
        if (email != null) {
            Pattern emailFormat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher hasFormat = emailFormat.matcher(email);
            return hasFormat.matches();
        } else
            return false;
    }

    public String getPassword(Scanner sc) {
        String password = sc.nextLine();
        while (password == null || !passwordVerify(password)) {
            System.out.println("Yeu cau mat khau dai 7-15 ky tu, chua it nhat 01 ky tu in hoa va 01 ky tu dac biet:");
            password = sc.nextLine();
        }
        return password;
    }

    //    password dài từ 7 đến 15 ký tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt (. , - _ ;)
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
}
