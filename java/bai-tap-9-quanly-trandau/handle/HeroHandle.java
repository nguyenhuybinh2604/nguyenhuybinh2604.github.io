package handle;

import java.util.Scanner;

import entity.Hero;

public class HeroHandle {
    public Hero createHero(Scanner sc, int i, String teamName) {
        System.out.println(teamName + ": Nhap ten cua tuong thu " + i + ":");
        String name = sc.nextLine();
        System.out.println(teamName + ": Nhap vi tri cua tuong thu " + i + ":");
        String position = sc.nextLine();
        Hero hero = new Hero(name, position);
        return hero;
    }
}
