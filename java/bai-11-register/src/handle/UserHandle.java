package handle;

import entity.User;

import java.util.List;
import java.util.Scanner;

public class UserHandle {

    public void addUser(List<User> users, String username, String email, String password) {
        users.add(new User(username, email, password));
        System.out.println("Tao user moi thanh cong.");
    }

    public User findUser(List<User> users, String inputStr, String strType) {
        User returnValue = null;
        for (User user : users) {
            if (strType.equals("username") && user.getUsername().equals(inputStr)) {
                returnValue = user;
                break;
            } else if (strType.equals("email") && user.getEmail().equals(inputStr)) {
                returnValue = user;
                break;
            } else {
                returnValue = null;
            }
        }
        return returnValue;
    }

    public void changeUsername(Scanner sc, List<User> users, int index) {
        System.out.println("Username hien tai:");
        System.out.println(users.get(index).getUsername());
        System.out.println("Nhap username moi:");
        String newUsername = sc.nextLine();
        if (findUser(users, newUsername, "username") != null) {
            System.out.println("Username da duoc su dung.");
        } else {
            //Thay username moi
            users.get(index).setUsername(newUsername);
            System.out.println("Username changed.");
        }
    }

    public void changeEmail(Scanner sc, List<User> users, InputControl inputControl, int index) {
        System.out.println("Email hien tai:");
        System.out.println(users.get(index).getEmail());
        System.out.println("Nhap email moi (dang emailaddress@domain.com):");
        String newEmail = inputControl.getEmail(sc); //Doi thanh inputControl
        if (findUser(users, newEmail, "email") != null) {
            System.out.println("Email da duoc su dung.");
        } else {
            //Thay email moi
            users.get(index).setEmail(newEmail);
            System.out.println("Email changed.");
        }
    }

    public void changePassword(Scanner sc, List<User> users, InputControl inputControl, int index) {
        System.out.println("Mat khau hien tai:");
        System.out.println(users.get(index).getPassword());
        System.out.println("Nhap mat khau moi (dai 7-15 ky tu, chua it nhat 01 ky tu in hoa va 01 ky tu dac biet):");
        String newPassword = inputControl.getPassword(sc); //Doi thanh inputControl
        //Thay password moi
        users.get(index).setPassword(newPassword);
        System.out.println("Password changed.");
    }
}
