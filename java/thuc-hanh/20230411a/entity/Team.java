package entity;

import java.util.List;

public class Team {
    public static int autoid;
    private int id;
    private String name;
    private List<Hero> heroes;


    public Team() {
        this.id = ++autoid;
    }

    public Team(String name, List<Hero> heroes) {
        this.id = ++autoid;
        this.name = name;
        this.heroes = heroes;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hero> getHeroes() {
        return this.heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public Team name(String name) {
        setName(name);
        return this;
    }

    public Team heroes(List<Hero> heroes) {
        setHeroes(heroes);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", heroes='" + getHeroes() + "'" +
            "}";
    }

    
}
