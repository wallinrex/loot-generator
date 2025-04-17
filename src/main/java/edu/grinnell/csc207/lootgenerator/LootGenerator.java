package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import edu.grinnell.csc207.lootgenerator.MagicPrefix.Prefix;
import edu.grinnell.csc207.lootgenerator.MagicSuffix.Suffix;
import edu.grinnell.csc207.lootgenerator.MonStats.Monster;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    public static final String DATA_SET = "data/small";
    private static MonStats stats;
    private static TreasureClass treasure;
    private static Armor armor;
    private static MagicPrefix prefixes;
    private static MagicSuffix suffixes;
    
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

    private static void fightMonster() {
        Monster monster = stats.getMonster();
        String monsterName = monster.monsterClass();
        System.out.println("\nFighting " + monsterName + "...");
        System.out.println("You have slain " + monsterName + "!");
        System.out.println(monsterName + " dropped:\n");
        Random randoms = new Random();
        StringBuffer itemName = new StringBuffer();
        Prefix prefix;
        Suffix suffix;
        if (randoms.nextInt(2) == 1) {
            prefix = prefixes.getPrefix();
            itemName.append(prefix.name() + " ");
        } else {
            prefix = null;
        }
        String armorName = treasure.getArmorPiece(monster.treasureClass());
        itemName.append(armorName);
        if (randoms.nextInt(2) == 1) {
            suffix = suffixes.getSuffix();
            itemName.append(" " + suffix.name());
        } else {
            suffix = null;
        }
        System.out.println(itemName.toString());
        System.out.println("Defense: " + armor.getDefense(armorName));
        if (prefix != null) {
            String effectValue = String.valueOf(randoms.nextInt(prefix.min(), prefix.max() + 1));
            System.out.println(effectValue + " " + prefix.effect());
        }
        if (suffix != null) {
            String effectValue = String.valueOf(randoms.nextInt(suffix.min(), suffix.max() + 1));
            System.out.println(effectValue + " " + suffix.effect());
        }
        System.out.println();
    }

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
