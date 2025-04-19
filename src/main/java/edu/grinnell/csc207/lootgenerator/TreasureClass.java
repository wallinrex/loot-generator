package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TreasureClass {

    private HashMap<String, String[]> classes;

    private class ParseLine implements Consumer<String> {

        /**
         * Takes a line of the treasure class text file and puts it into the hashmap
         * with the name of the class as the key and the three loot choices in an array
         * as the value
         * 
         * @param str The line of the text file which is being interpreted
         */
        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            String[] choices = Arrays.copyOfRange(fields, 1, 4);
            classes.put(fields[0], choices);
        }
    }

    /**
     * Constructor for the TreasureClass object, a hashmap that stores all the
     * treasure classes and the loot they can drop
     * 
     * @param dataSet The path to find the data files
     * @throws IOException If the file can't be opened
     */
    public TreasureClass(String dataSet) throws IOException {
        classes = new HashMap<>();
        Path fileName = Paths.get(dataSet + "/TreasureClassEx.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    /**
     * Helper for `getArmorPiece` that works recursively until it actually reaches
     * an armor piece, not another treasure class
     * 
     * @param treasureClass The treasure class or armor piece to find or return
     * @return The name of the chosen armor piece
     */
    private String getArmorPieceHelper(String treasureClass) {
        if (classes.containsKey(treasureClass)) {
            int index = new Random().nextInt(3);
            String newClass = classes.get(treasureClass)[index];
            return getArmorPieceHelper(newClass);
        } else {
            return treasureClass;
        }
    }

    /**
     * Picks an armor piece to be dropped from the monster you killed
     * 
     * @param treasureClass The treasure class of the monster
     * @return The name of the chosen armor piece
     */
    public String getArmorPiece(String treasureClass) {
        return getArmorPieceHelper(treasureClass);
    }
}
