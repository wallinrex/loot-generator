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
 * Class to package an array holding the "MagicSuffix.txt" file
 */
public class MagicSuffix {

    private ArrayList<Suffix> suffixes;

    /**
     * Object to hold one line from the MagicSuffix file
     */
    public record Suffix(String name, String effect, int min, int max) { }

    private class ParseLine implements Consumer<String> {

        /**
         * Processes a line of the magic suffix text file, turning it into a Suffix
         * object, then putting the Suffix in the array
         * 
         * @param str The line of the text file being processed
         */
        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            Suffix newSuffix = new Suffix(fields[0], fields[1], Integer.parseInt(fields[2]),
                    Integer.parseInt(fields[3]));
            suffixes.add(newSuffix);
        }
    }

    /**
     * Constructor for the MagicSuffix object, and array of all the Suffixes,
     * holding name, effect, and min and max values
     * 
     * @param dataSet String for the path to find the file
     * @throws IOException If the file can't be opened
     */
    public MagicSuffix(String dataSet) throws IOException {
        suffixes = new ArrayList<>();
        Path fileName = Paths.get(dataSet + "/MagicSuffix.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    /**
     * Returns a random Suffix object from the array
     * 
     * @return The random Suffix object
     */
    public Suffix getSuffix() {
        int index = new Random().nextInt(suffixes.size());
        return suffixes.get(index);
    }
}
