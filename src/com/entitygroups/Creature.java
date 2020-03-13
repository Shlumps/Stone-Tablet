package com.entitygroups;

import com.itemgroups.*;
import com.main.*;

import java.awt.*;
import java.util.*;

public class Creature extends GameEntity {

    // FIELDS
    private String name;
    private Creature enemy;
    private boolean downed;

    private int health;
    private int maxHealth;
    private int atk;
    private int def;
    private int agility;
    private int atkSpeed;

    private Sword selectedSword;
    private Shield selectedShield;
    private ArrayList<Item> inventory = new ArrayList<>();

    // CONSTRUCTORS
    public Creature(GamePanel game, int xPos, int yPos, String name, int health, Sword selectedSword, Shield selectedShield) {
        super(game, xPos, yPos);
        this.downed = false;
        this.name = name;
        this.health = health;
        this.selectedSword = selectedSword;
        this.selectedShield = selectedShield;
    }

    // IMPORT BEHAVIORS
    public String toString() {
        return name;
    }
    // STAT ACTIONS
    public void changeHealth(int amount) {
        health += amount;
        checkDeath();
    }

    public void checkDeath() {
        if(health <= 0) {
            System.out.println("~event~ " + name + " is now dead!");
            downed = true; // broadcast dead (for now just die)
            setDead(true); // clean dat boi up
        }
    }

    // TURN ACTIONS
    public void changeEnemy(Creature newEnemy) {
        enemy = newEnemy;
        System.out.println("~event~ " + enemy + " is now " + name + "'s target!");
    }

    public void attackEnemy() {
        if(-atk + enemy.getDef() < 0) {
            enemy.changeHealth(-atk + enemy.getDef());
            System.out.println("~event~ " + enemy + " attacked " + name + " for " + atk);
        }
        else {
            System.out.println("~event~ " + enemy + " attacked " + name + " but did no damage!");
        }
    }

    public void recover() {
        int heal = (int)(Math.random()*maxHealth);
        health += heal;
        System.out.println("~event~ " + name + " healed for " + heal + " health!");
    }

    // INVENTORY ACTIONS
    public void displayInventory() { // ADD GRAPHICS TO THIS PLEASE
        for(int i = 0; i < inventory.size(); i++) {
            System.out.println(i+". "+inventory.get(i));
        }
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
        System.out.println("~event~ " + item + " has been added to " + name);
    }

    public void dropItem(Item item) {
        inventory.remove(item);
        System.out.println("~event~ " + item + " has been dropped by " + name);
    }

    public void selectSword(Sword newSword) {
        inventory.add(selectedSword);
        inventory.remove(newSword);
        selectedSword = newSword;

        atk = newSword.getAtk();
        atkSpeed = newSword.getAtkSpeed();
        System.out.println("~event~ " + newSword +" has been equipped to: " + name);
    }

    public void selectShield(Shield newShield) {
        inventory.add(selectedShield);
        inventory.remove(newShield);
        selectedShield = newShield;

        def = newShield.getDef();
        agility = newShield.getAgility();
        System.out.println("~event~ " + newShield +" has been equipped to: " + name);
    }

    // GAME ENTITY UPDATE METHODS
    @Override
    public void graphicalUpdate(Graphics2D g) {

    }

    @Override
    public void logicalUpdate() {
        if(health > maxHealth) {
            health = maxHealth;
        }
    }

    @Override
    public void onClickUpdate() {

    }

    // GETTERS
    public int getDef() {
        return def;
    }
}