package com.itemgroups;

public class Sword extends Item {

    // FIELDS
    private int atk;
    private int atkSpeed;

    // CONSTRUCTORS
    public Sword(String name, int atk, int atkSpeed) {
        super(name);
        this.atk = atk;
        this.atkSpeed = atkSpeed;
    }

    public Sword(String name) {
        super(name);
    }

    public Sword() {
        name = "Sword";
        atk = 1;
        atkSpeed = 1;
    }

    // GETTERS
    public int getAtk() { return atk; }
    public int getAtkSpeed() { return atkSpeed; }

    // SETTERS
    public void setAtk(int atk) { this.atk = atk; }
    public void setAtkSpeed(int atkSpeed) { this.atkSpeed = atkSpeed; }
}
