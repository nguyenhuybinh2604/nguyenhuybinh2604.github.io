package handle;

import java.util.List;
import java.util.Scanner;

import entity.Follower;

public class handleFollower {
    public Follower newFollower(Scanner sc) {
        System.out.println("Nhap ten follower:");
        String name = sc.nextLine();
        System.out.println("Nhap email:");
        String email = sc.nextLine();
        System.out.println("Nhap so likes:");
        int numberOfLikes = Integer.parseInt(sc.nextLine());
        Follower follower = new Follower(name,email,numberOfLikes);
        return follower;
    }

    public void createFollowers(Scanner sc, List<Follower> followers) {
        System.out.println("Nhap so followers:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            followers.add(newFollower(sc));
        }
    }
}
