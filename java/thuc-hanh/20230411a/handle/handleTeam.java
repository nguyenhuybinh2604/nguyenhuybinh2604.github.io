package handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Hero;
import entity.Team;

public class handleTeam {
    public Team newTeam(Scanner sc) {
        System.out.println("Nhap ten doi:");
        String name = sc.nextLine();

        List<Hero> heroes = new ArrayList<>();

        handleHero handleHero = new handleHero();
        
        // Nhap cac heroes
        handleHero.createHeroes(sc, heroes);
        
        Team team = new Team(name,heroes);
        return team;
    }

    public void createTeam(Scanner sc, List<Team> teams) {
        System.out.println("Nhap so teams:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            teams.add(newTeam(sc));
        }
    }
}
