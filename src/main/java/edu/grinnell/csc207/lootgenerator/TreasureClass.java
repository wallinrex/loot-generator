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

        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            String[] choices = Arrays.copyOfRange(fields, 1, 4);
            classes.put(fields[0], choices);
        }
    }

    public TreasureClass (String dataSet) throws IOException {
        classes = new HashMap<>();
        Path fileName = Paths.get(dataSet + "/TreasureClassEx.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    public boolean contains(String treasureClass) {
        return classes.containsKey(treasureClass);
    }

    public String getChoice(String treasureClass) {
        int index = new Random().nextInt(3);
        return classes.get(treasureClass)[index];
    }
}
