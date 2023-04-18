package entity;

import service.ICanShoot;
import service.ICanSurf;

public class Lucian implements ICanShoot, ICanSurf {
    private String hair;
    private String weapon;

    public Lucian() {
    }

    public Lucian(String hair, String weapon) {
        this.hair = hair;
        this.weapon = weapon;
    }

    public String getHair() {
        return this.hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getWeapon() {
        return this.weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public Lucian hair(String hair) {
        setHair(hair);
        return this;
    }

    public Lucian weapon(String weapon) {
        setWeapon(weapon);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " hair='" + getHair() + "'" +
                ", weapon='" + getWeapon() + "'" +
                "}";
    }

    @Override
    public void shoot() {
        System.out.println("Shoot.");
    }

    @Override
    public void surf() {
        System.out.println("Surf.");
    }
}
