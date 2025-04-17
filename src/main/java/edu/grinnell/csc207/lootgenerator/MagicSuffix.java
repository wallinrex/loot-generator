package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MagicSuffix {
    
    private ArrayList<Suffix> suffixes;

    private record Suffix(String name, String effect, int min, int max) { }

    private class ParseLine implements Consumer<String> {

        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            Suffix newSuffix = new Suffix(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
            suffixes.add(newSuffix);
        }
    }

    public MagicSuffix () throws IOException {
        Path fileName = Paths.get("MagicSuffix.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    public Suffix getSuffix() {
        int index = new Random().nextInt(suffixes.size());
        return suffixes.get(index);
    }
}
