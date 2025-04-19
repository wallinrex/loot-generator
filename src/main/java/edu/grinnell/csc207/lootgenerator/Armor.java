package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Class to package a hashmap holding the "armor.txt" file
 */
public class Armor {

    private HashMap<String, int[]> pieces;

    private class ParseLine implements Consumer<String> {

        /**
         * Processes a line of the armor text file by putting it into the hashmap with
         * the piece's name as the key and an array of the min and max defense values in
         * an array as the value
         * 
         * @param str The line of the text file being processed
         */
        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            int[] range = new int[2];
            range[0] = Integer.parseInt(fields[1]);
            range[1] = Integer.parseInt(fields[2]);
            pieces.put(fields[0], range);
        }
    }

    /**
     * Constructor for the Armor object, a hashmap of all the armor pieces and their
     * range of possible defense values
     * 
     * @param dataSet A string holding the path to find the file
     * @throws IOException If the file can't be opened
     */
    public Armor(String dataSet) throws IOException {
        pieces = new HashMap<>();
        Path fileName = Paths.get(dataSet + "/armor.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    /**
     * Generates a defense value for a specified piece of armor
     * 
     * @param piece The name of the armor piece
     * @return A random int between the min and max values, both inclusive
     */
    public String getDefense(String piece) {
        int[] values = pieces.get(piece);
        int defense = new Random().nextInt(values[0], values[1] + 1);
        return String.valueOf(defense);
    }
}
