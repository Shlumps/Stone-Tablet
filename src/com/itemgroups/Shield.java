package com.itemgroups;

public class Shield extends Item {

    // FIELDS
    private int def;
    private int agility;

    // CONSTRUCTORS
    public Shield(String name, int def, int agility) {
        super(name);
        this.def = def;
        this.agility = agility;
    }

    public Shield(String name) {
        super(name);
    }

    public Shield() {
        name = "Shield";
        def = 1;
        agility = 1;
    }

    // GETTERS
    public int getDef() { return def; }
    public int getAgility() { return agility; }

    // SETTERS
    public void setDef(int def) { this.def = def; }
    public void setAgility(int agility) { this.agility = agility; }
}
