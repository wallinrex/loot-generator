package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Armor {
    
    private HashMap<String, int[]> pieces;

    private class ParseLine implements Consumer<String> {

        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            int[] range = new int[2];
            range[0] = Integer.parseInt(fields[1]);
            range[1] = Integer.parseInt(fields[2]);
            pieces.put(fields[0], range);
        }
    }

    public Armor () throws IOException {
        Path fileName = Paths.get("TreasureClassEx.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    public int getDefense(String piece) {
        int[] values = pieces.get(piece);
        int defense = new Random().nextInt(values[0], values[1] + 1);
        return defense;
    }
}
