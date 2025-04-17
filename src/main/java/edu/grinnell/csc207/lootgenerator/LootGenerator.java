package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    public static final String DATA_SET = "data/small";
    
    public static void main(String[] args) throws IOException {
        MonStats stats = new MonStats(DATA_SET);
        TreasureClass treasure = new TreasureClass(DATA_SET);
        Armor armor = new Armor(DATA_SET);
        MagicPrefix prefixes = new MagicPrefix(DATA_SET);
        MagicSuffix suffixes = new MagicSuffix(DATA_SET);
        System.out.println("This program kills monsters and generates loot!");
        // TOOD: Implement me!
    }
}
