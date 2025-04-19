package edu.grinnell.csc207.lootgenerator;

import java.util.Random;

import edu.grinnell.csc207.lootgenerator.MagicPrefix.Prefix;
import edu.grinnell.csc207.lootgenerator.MagicSuffix.Suffix;

/**
 * Class to hold an entier armor piece, with base piece an affixes
 */
public class ArmorPiece {

    private String pieceName;

    private Prefix prefix = null;

    private Suffix suffix = null;

    /**
     * Constructor for the ArmorPiece object, generating appropriate affixes for the
     * armor piece
     * 
     * @param name The name of the base piece of armor
     */
    public ArmorPiece(String name) {
        pieceName = name;
        Random coin = new Random();
        if (coin.nextBoolean()) {
            prefix = LootGenerator.prefixes.getPrefix();
        }
        if (coin.nextBoolean()) {
            suffix = LootGenerator.suffixes.getSuffix();
        }
    }

    /**
     * Prints the full name of the piece, including affixes
     */
    public void printName() {
        StringBuffer itemName = new StringBuffer();
        if (prefix != null) {
            itemName.append(prefix.name() + " ");
        }
        itemName.append(pieceName);
        if (suffix != null) {
            itemName.append(" " + suffix.name());
        }
        System.out.println(itemName.toString());
    }

    /**
     * Determines and prints the defense value of the piece
     */
    public void printDefense() {
        System.out.println("Defense: " + LootGenerator.armor.getDefense(pieceName));
    }

    /**
     * Determines the values for and prints the affix stats of the piece
     */
    public void printAffixes() {
        Random randoms = new Random();
        if (prefix != null) {
            String effectValue = String.valueOf(randoms.nextInt(prefix.min(), prefix.max() + 1));
            System.out.println(effectValue + " " + prefix.effect());
            // I think value and effect should be swapped, but this matches the example outputs
        }
        if (suffix != null) {
            String effectValue = String.valueOf(randoms.nextInt(suffix.min(), suffix.max() + 1));
            System.out.println(effectValue + " " + suffix.effect());
            // I think value and effect should be swapped, but this matches the example outputs
        }
    }
}
