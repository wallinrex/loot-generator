package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.util.Scanner;

import edu.grinnell.csc207.lootgenerator.MonStats.Monster;

/**
 * Entry point for the LootGenerator program
 */
public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    public static final String DATA_SET = "data/large";
    private static MonStats stats;
    private static TreasureClass treasure;
    public static Armor armor;
    public static MagicPrefix prefixes;
    public static MagicSuffix suffixes;

    /**
     * Driver function that instantiates the necessary data structures and
     * repeatedly asks the user whether they want to fight again
     * 
     * @param args Command-line arguments (there should be none)
     * @throws IOException If any of the data files can't be opened
     */
    public static void main(String[] args) throws IOException {
        stats = new MonStats(DATA_SET);
        treasure = new TreasureClass(DATA_SET);
        armor = new Armor(DATA_SET);
        prefixes = new MagicPrefix(DATA_SET);
        suffixes = new MagicSuffix(DATA_SET);
        System.out.println("This program kills monsters and generates loot!");
        boolean stillRunning = true;
        Scanner userInput = new Scanner(System.in);
        while (stillRunning) {
            fightMonster();
            stillRunning = prompt(userInput);
        }
    }

    /**
     * Performs and prints all the actions of fighting the monster and generating
     * the loot
     */
    private static void fightMonster() {
        Monster monster = stats.getMonster();
        printFight(monster);
        String pieceName = treasure.getArmorPiece(monster.treasureClass());
        ArmorPiece piece = new ArmorPiece(pieceName);
        piece.printName();
        piece.printDefense();
        piece.printAffixes();
        System.out.println();
    }

    /**
     * Prints the user's interaction with a monster
     * 
     * @param monster The monster in question
     */
    private static void printFight(Monster monster) {
        String monsterName = monster.monsterClass();
        System.out.println("\nFighting " + monsterName + "...");
        System.out.println("You have slain " + monsterName + "!");
        System.out.println(monsterName + " dropped:\n");
    }

    /**
     * Asks the user whether they want to fight again, and asks again if they give
     * an invalid input
     * 
     * @param userInput A Scanner reading standard in
     * @return true if the user input is "y" or "Y", false if it's "n" or "N"
     */
    private static boolean prompt(Scanner userInput) {
        System.out.print("Fight again [y/n]? ");
        String answer = userInput.next().toLowerCase();
        if (answer.equals("y")) {
            return true;
        } else if (answer.equals("n")) {
            return false;
        } else {
            return prompt(userInput);
        }
    }
}
