package handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Idol;
import entity.Follower;

public class handleIdol {
    public Idol newIdol(Scanner sc) {
        System.out.println("Nhap ten idol:");
        String name = sc.nextLine();
        System.out.println("Nhap email:");
        String email = sc.nextLine();
        System.out.println("Nhap group:");
        String group = sc.nextLine();

        List<Follower> followers = new ArrayList<>();

        handleFollower handleFollower = new handleFollower();
        
        // Nhap cac followers
        handleFollower.createFollowers(sc, followers);
        
        Idol idol = new Idol(name, email, group, followers);
        return idol;
    }

    public void createIdol(Scanner sc, List<Idol> idols) {
        System.out.println("Nhap so idols:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            idols.add(newIdol(sc));
        }
    }
}
