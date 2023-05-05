package handle;

import entity.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputControl {

    public int getInput(Scanner sc, Integer min, Integer max) {
        int input = 0;
        String inputString = "";
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
        double input = 0.0;
        String inputString = "";
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

    public String getNonEmptyString(Scanner sc) {
        String returnString = sc.nextLine().trim();
        while (returnString.isEmpty()) {
            System.out.print("Enter a non-empty string: ");
            returnString = sc.nextLine().trim();
        }
        return returnString;
    }

    // return credit rating from a str
    public CreditRating toCreditRating(String ratingStr) {
        return switch (ratingStr) {
            case "A" -> CreditRating.A;
            case "B" -> CreditRating.B;
            case "C" -> CreditRating.C;
            default -> CreditRating.UNKNOWN;
        };
    }

    // return credit rating from a str
    public String toCreditRatingStr(CreditRating creditRating) {
        return switch (creditRating) {
            case A -> "A";
            case B -> "B";
            case C -> "C";
            default -> "";
        };
    }

    // return product type from a str
    public ProductType toProductType(String productTypeStr) {
        return switch (productTypeStr) {
            case "LOAN" -> ProductType.LOAN;
            case "SAVING" -> ProductType.SAVING;
            case "ACCOUNT" -> ProductType.ACCOUNT;
            default -> ProductType.UNKNOWN;
        };
    }

    // return str product type
    public String toProductTypeStr(ProductType productType) {
        return switch (productType) {
            case LOAN -> "LOAN";
            case SAVING -> "SAVING";
            case ACCOUNT -> "ACCOUNT";
            default -> "";
        };
    }

    public ProductStatus toProductStatus(String productStatusStr) {
        return switch (productStatusStr) {
            case "ACTIVE" -> ProductStatus.ACTIVE;
            case "INACTIVE" -> ProductStatus.INACTIVE;
            case "LOCKED" -> ProductStatus.LOCKED;
            default -> ProductStatus.UNKNOWN;
        };
    }

    public String toProductStatusStr(ProductStatus productStatus) {
        return switch (productStatus) {
            case ACTIVE -> "ACTIVE";
            case INACTIVE -> "INACTIVE";
            case LOCKED -> "LOCKED";
            default -> "";
        };
    }

    // return product type from a str
    public UserStatus toUserStatus(String userStatusStr) {
        return switch (userStatusStr) {
            case "ACTIVE" -> UserStatus.ACTIVE;
            case "LOCKED" -> UserStatus.LOCKED;
            case "INACTIVE" -> UserStatus.INACTIVE;
            default -> UserStatus.UNKNOWN;
        };
    }

    // return str user status
    public String toUserStatusStr(UserStatus userStatus) {
        return switch (userStatus) {
            case ACTIVE -> "ACTIVE";
            case LOCKED -> "LOCKED";
            case INACTIVE -> "INACTIVE";
            default -> "";
        };
    }
}
