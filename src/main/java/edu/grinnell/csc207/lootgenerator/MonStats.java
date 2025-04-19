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
 * Class to package an array holding the "monstats.txt" file
 */
public class MonStats {

    private ArrayList<Monster> monsters;

    public record Monster(String monsterClass, String type, int level, String treasureClass) {
    }

    private class ParseLine implements Consumer<String> {

        /**
         * Processes a line of the monstats text file and makes it into a Monster
         * object, which is then put in the array
         * 
         * @param str The line of the text file being processed
         */
        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            Monster newMonster = new Monster(fields[0], fields[1],
                    Integer.parseInt(fields[2]), fields[3]);
            monsters.add(newMonster);
        }
    }

    /**
     * Constructor for the MonStats object, an array of all the Monster objects,
     * holding name, type, level, and treasure class
     * 
     * @param dataSet The path to find the data files
     * @throws IOException If the file can't be opened
     */
    public MonStats(String dataSet) throws IOException {
        monsters = new ArrayList<>();
        Path fileName = Paths.get(dataSet + "/monstats.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    /**
     * Returns a random Monster object for the player to fight
     * 
     * @return A random Monster object from the array
     */
    public Monster getMonster() {
        int index = new Random().nextInt(monsters.size());
        return monsters.get(index);
    }
}
