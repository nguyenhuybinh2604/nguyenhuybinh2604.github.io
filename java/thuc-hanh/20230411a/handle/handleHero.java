package handle;

import java.util.List;
import java.util.Scanner;

import entity.Hero;

public class handleHero {
    public Hero newHero(Scanner sc) {
        System.out.println("Nhap ten hero:");
        String name = sc.nextLine();
        System.out.println("Nhap vi tri:");
        String position = sc.nextLine();
        Hero hero = new Hero(name,position);
        return hero;
    }

    public void createHeroes(Scanner sc, List<Hero> heroes) {
        System.out.println("Nhap so heroes:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            heroes.add(newHero(sc));
        }
    }
}
