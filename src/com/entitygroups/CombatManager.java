package com.entitygroups;

import com.itemgroups.*;
import com.main.GamePanel;
import java.awt.*;
import java.util.*;

public class CombatManager extends GameEntity {

    private Scanner input = new Scanner(System.in);
    private ArrayList<Creature> players = new ArrayList<>();
    private Creature player;
    private ArrayList<Creature> enemies = new ArrayList<>();
    boolean isPlayerTurn;

    public CombatManager(GamePanel game) {
        super(game);
        initializeEnemies();
    }

    // CREATION BEHAVIORS...............................................................................................
    public void initializeEnemies() { // create all enemies
        enemies.add(new Creature(getGame(),400, 400, "Black Skeleton", 20, new Sword("Stone Sword", 3, 5), new Shield("Wooden Shield", 1, 4)));
        enemies.add(new Creature(getGame(),400, 400, "Gray Skeleton", 20, new Sword("Stone Sword", 3, 5), new Shield("Iron Shield", 2, 4)));
    }

    public void createPlayer() { // DO THIS
        players.add(new Creature(getGame(),0,0,"Player",100,new Sword("Adamant Wooden Sword", 5, 5), new Shield("Frail Wooden Shield", 1, 2)));
        System.out.println("Player Created");
    }

    // DISPLAY METHODS..................................................................................................
    public void footer() {
        System.out.println("^-^-^-^-^-^-^-^-^-^-^\n\n");
    }

    public void displayEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println((i+1)+". "+enemies.get(i));
        }
        footer();
    }

    public void displayMainMenu() {
        System.out.println("\t~STONE TABLET~\n");
        System.out.println("1. Start Game\n2. Create Player\n3. Exit Game");
        footer();
    }

    public void displayActions() {

    }

    public void displayPlayers() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i+1)+". "+players.get(i));
        }
        footer();
    }

    // CHOICE METHODS...................................................................................................

    public void selectPlayerChoice() {
        displayPlayers();
        player = players.get(input.nextInt()-1);
    }

    public void chooseEnemy() {
        displayEnemies();
        System.out.println("Choose a target!");
        player.changeEnemy(enemies.get(input.nextInt()));
    }

    public void setupEncounter() {
        for(Creature i : enemies) {
            i.changeEnemy(player);
        }
        getGame().getEntities().addAll(enemies);
        System.out.println("Encounter Started!");
    }

    public void combatPhase() {
        while(enemies.size() > 0) {
            if(isPlayerTurn) {
                System.out.println("Your Turn!");
                displayActions();
                switch (input.nextLine()) {
                    case "1": // ATTACK
                        chooseEnemy();
                        player.attackEnemy();
                        isPlayerTurn = false;
                        break;
                    case "2": // RECOVER
                        player.recover();
                        isPlayerTurn = false;
                        break;
                    default:
                        System.out.println("Invalid Option!");

                }
                isPlayerTurn = false;
            }
            else {
                System.out.println("Enemies' turn!");
                isPlayerTurn = true;
            }
        }
        System.out.println("You Defeated The Enemies!");
    }

    public void mainMenuChoice() {
        while(true) {
            displayMainMenu();
            switch (input.nextLine()) {
                case "1": // Start game
                    if (players.size() > 0) {
                        selectPlayerChoice();
                        return;
                    }
                    break;
                case "2": // Create player
                    createPlayer();
                    break;
                case "3": // Exit game
                    System.out.println("Game Exited");
                    getGame().exitGame();
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    @Override
    public void graphicalUpdate(Graphics2D g) {

    }

    @Override
    public void logicalUpdate() { // MAIN COMBAT MANAGEMENT
        boolean gameEnded = false;

        MainLoop:
        while(!gameEnded) {
            mainMenuChoice();
            setupEncounter();
            combatPhase();
                // END ENCOUNTER
                    // DEATH -> MENU
                    // WIN -> MENU
        }
    }

    @Override
    public void onClickUpdate() {

    }
}
