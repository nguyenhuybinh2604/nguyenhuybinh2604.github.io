package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.User;
import handle.InputControl;
import handle.UserHandle;

public class Menu {
    InputControl inputControl = new InputControl();
    UserHandle userHandle = new UserHandle();
    Scanner sc = new Scanner(System.in);
    String loginStatus = "";
    List<User> users = new ArrayList<>();

    public int inputMain(Scanner sc) {
        System.out.println("Nhap lua chon cua ban:");
        System.out.println("1: Dang nhap.");
        System.out.println("2: Dang ky.");
        System.out.println("3: Thoat chuong trinh.");

        return inputControl.getInput(sc, 1, 3);
    }

    public void callMainMenu() {
        int input = inputMain(sc);
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    login(sc, users);
                    break;
                }
                case 2 -> {
                    register(sc, users);
                    break;
                }
                case 3 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call lai main menu
            input = inputMain(sc);
        } while (input >= 1 && input <= 3);
    }

    // Để đăng nhập, cần sử dụng username và password.
    // Nếu nhập sai username thì thông báo “Kiểm tra lại username” và cho đăng nhập lại, nhập sai password thì hiện yêu cầu:
    //  1 - Đăng nhập lại
    //  2 - Quên mật khẩu
    public void login(Scanner sc, List<User> users) {
        System.out.println("Nhap username:");
        String username = sc.nextLine();
        if (userHandle.findUser(users, username, null) != null) {
            int index = users.indexOf(userHandle.findUser(users, username, null));
            System.out.println("Nhap password:");
            String password = sc.nextLine();
            if (users.get(index).getPassword().equals(password)) {
                //call menu login thanh cong
                loginStatus = "success";
                loginSuccess(sc, users, index);
            } else {
                //Sai mat khau. call menu relogin
                System.out.println("Mat khau khong dung.");
                loginStatus = "failure_Login_WrongPassword";
                loginFailed(sc, users);
            }
        } else {
            loginStatus = "failure_Login_WrongUsername";
            System.out.println("Khong tim thay user " + username + ".");
        }
    }

    //    Để tạo tài khoản mới cần nhập username, email, password. Trong đó:
    //    username phải chưa tồn tại
    //    email phải chuẩn (Sử dụng regex), chưa tồn tại trong List
    //    password dài từ 7 đến 15 ký tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt (. , - _ ;)
    public void register(Scanner sc, List<User> users) {
        System.out.println("Nhap username:");
        String username = sc.nextLine();
        if (userHandle.findUser(users, username, null) != null) {
            System.out.println("Username da duoc dang ky.");
        } else {
            System.out.println("Nhap email:");
            String email = inputControl.getEmail(sc); //Doi thanh inputControl
            if (userHandle.findUser(users, null, email) != null) {
                System.out.println("Email da duoc dang ky.");
            } else {
                System.out.println("Nhap mat khau:");
                String password = inputControl.getPassword(sc); //Doi thanh inputControl
                //Tao user moi
                users.add(new User(username, email, password));
                System.out.println("Tao user moi thanh cong.");
            }
        }
    }

    public int inputLoginFailed(Scanner sc) {
        System.out.println("Nhap lua chon cua ban:");
        System.out.println("1: Dang nhap lai.");
        System.out.println("2: Quen mat khau.");
        System.out.println("3: Quay lai.");

        return inputControl.getInput(sc, 1, 3);
    }

    public void loginFailed(Scanner sc, List<User> users) {
        int input;
        input = inputLoginStatus(loginStatus);
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    login(sc, users);
                    break;
                }
                case 2 -> {
                    forgotPassword(sc, users);
                    break;
                }
                case 3 -> {
                    // Return to previous method
                    return;
                }
            }
            // Set option cho menu loginFailed ke tiep
            input = inputLoginStatus(loginStatus);
        } while (input >= 1 && input <= 3);
    }

    public int inputLoginStatus(String loginStatus) {
        switch (loginStatus) {
            case "failure_Login_WrongPassword", "failure_ForgotPassword_WrongEmail" -> {
                return inputLoginFailed(sc);
            }
            case "failure_Login_WrongUsername" -> {
                return 1;
            }
            default -> {
                return 3;
            }
        }
    }

    //Tại mục quên mật khẩu thì có nhập email.
    // Nếu email đúng thì cho phép đổi mật khẩu và tiến hành đăng nhập. Nếu email sai thì báo chưa tồn tại tài khoản
    public void forgotPassword(Scanner sc, List<User> users) {
        System.out.println("Nhap email:");
        String email = sc.nextLine();
        if (userHandle.findUser(users, null, email) != null) {
            int index = users.indexOf(userHandle.findUser(users, null, email));
            //call menu doi mat khau
            userHandle.changePassword(sc, users, inputControl, index);
            //dang nhap
            loginStatus = "success";
            loginSuccess(sc, users, index);
        } else {
            loginStatus = "failure_ForgotPassword_WrongEmail";
            System.out.println("Email khong dung.");
        }
    }

    public int inputLoginSuccess(Scanner sc, List<User> users, int index) {
        System.out.println("Chao mung " + users.get(index).getUsername() + ". Ban co the thuc hien cac cong viec sau:");
        System.out.println("1: Thay doi username.");
        System.out.println("2: Thay doi email.");
        System.out.println("3: Thay doi mat khau.");
        System.out.println("4: Dang xuat.");
        System.out.println("5: Thoat chuong trinh.");

        return inputControl.getInput(sc, 1, 5);
    }

    public void loginSuccess(Scanner sc, List<User> users, int index) {
        int input = inputLoginSuccess(sc, users, index);
        do {
            // Xu ly tung lua chon
            switch (input) {
                case 1 -> {
                    userHandle.changeUsername(sc, users, index);
                    break;
                }
                case 2 -> {
                    userHandle.changeEmail(sc, users, inputControl, index);
                    break;
                }
                case 3 -> {
                    userHandle.changePassword(sc, users, inputControl, index);
                    break;
                }
                case 4 -> {
                    // Log out
                    return;
                }
                case 5 -> {
                    // Exit
                    exitProgram();
                }
            }
            // Call lai login success menu
            input = inputLoginSuccess(sc, users, index);
        } while (input >= 1 && input <= 5);
    }

    public void exitProgram() {
        System.out.println("Thoat chuong trinh. Tam biet.");
        System.exit(0);
    }

}
