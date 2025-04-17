package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MagicPrefix {
    
    private ArrayList<Prefix> prefixes;

    private record Prefix(String name, String effect, int min, int max) { }

    private class ParseLine implements Consumer<String> {

        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            Prefix newPrefix = new Prefix(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]));
            prefixes.add(newPrefix);
        }
    }

    public MagicPrefix () throws IOException {
        Path fileName = Paths.get("MagicPrefix.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    public Prefix getPrefix() {
        int index = new Random().nextInt(prefixes.size());
        return prefixes.get(index);
    }
}
