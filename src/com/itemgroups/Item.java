package com.itemgroups;

public class Item {

    // FIELDS
    protected String name;

    // CONSTRUCTORS
    public Item() {
        name = "test";
    }

    public Item(String name) {
        this.name = name;
    }

    // BEHAVIORS
    public String toString() {
        return name;
    }
}
