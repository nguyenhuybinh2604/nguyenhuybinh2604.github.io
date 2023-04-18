package entity;

import service.ICanSurf;

public class Yasuo implements ICanSurf {
    private String hair;
    private String weapon;

    public Yasuo() {
    }

    public Yasuo(String hair, String weapon) {
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

    public Yasuo hair(String hair) {
        setHair(hair);
        return this;
    }

    public Yasuo weapon(String weapon) {
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
    public void surf() {
        System.out.println("Surf.");
    }

}
