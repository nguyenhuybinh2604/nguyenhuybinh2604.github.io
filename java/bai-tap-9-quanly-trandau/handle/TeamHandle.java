package handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Hero;
import entity.Team;

public class TeamHandle {
    public Team createTeam(Scanner sc, String name) {
        List<Hero> heroes = new ArrayList<>();
        HeroHandle heroHandle = new HeroHandle();
        for (int i = 0; i < 5; i++) {
            Hero hero = heroHandle.createHero(sc, i + 1, name);
            heroes.add(hero);
        }
        return new Team(name, heroes);
    }
}
