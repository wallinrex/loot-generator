package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Class to package an array holding the "MagicPrefix.txt" file
 */
public class MagicPrefix {

    private ArrayList<Prefix> prefixes;

    public record Prefix(String name, String effect, int min, int max) {
    }

    private class ParseLine implements Consumer<String> {

        /**
         * Processes a line of the magic prefix text file, turning it into a Prefix
         * object, then putting the Prefix in the array
         * 
         * @param str The line of the text file being processed
         */
        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            Prefix newPrefix = new Prefix(fields[0], fields[1], Integer.parseInt(fields[2]),
                    Integer.parseInt(fields[3]));
            prefixes.add(newPrefix);
        }
    }

    /**
     * Constructor for the MagicPrefix object, and array of all the Prefixes,
     * holding name, effect, and min and max values
     * 
     * @param dataSet String for the path to find the file
     * @throws IOException If the file can't be opened
     */
    public MagicPrefix(String dataSet) throws IOException {
        prefixes = new ArrayList<>();
        Path fileName = Paths.get(dataSet + "/MagicPrefix.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    /**
     * Returns a random Prefix object from the array
     * 
     * @return The random Prefix object
     */
    public Prefix getPrefix() {
        int index = new Random().nextInt(prefixes.size());
        return prefixes.get(index);
    }
}
