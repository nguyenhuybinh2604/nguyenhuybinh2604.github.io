package entity;

import java.util.List;

public class Team {
    public static int autoId;
    private int id;
    private String name;
    private List<Hero> heroes;


    public Team() {
        this.id = ++autoId;
    }

    public Team(String name, List<Hero> heroes) {
        this.id = ++autoId;
        this.name = name;
        this.heroes = heroes;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Hero> getHeroes() {
        return this.heroes;
    }

    @Override
    public String toString() {
        return 
            " Team: '" + getName() + "' \n" +
            " Line-up: '" + getHeroes() + "' \n";
    }
    

}