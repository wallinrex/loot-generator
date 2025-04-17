package edu.grinnell.csc207.lootgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MonStats {

    private ArrayList<Monster> monsters;

    public record Monster (String monsterClass, String type, int level, String treasureClass) { }

    private class ParseLine implements Consumer<String> {

        @Override
        public void accept(String str) {
            String[] fields = str.split("\t");
            Monster newMonster = new Monster(fields[0], fields[1], Integer.parseInt(fields[2]), fields[3]);
            monsters.add(newMonster);
        }
    }

    public MonStats (String dataSet) throws IOException {
        monsters = new ArrayList<>();
        Path fileName = Paths.get(dataSet + "/monstats.txt");
        Stream<String> lines = Files.lines(fileName);
        lines.forEach(new ParseLine());
        lines.close();
    }

    public Monster getMonster() {
        int index = new Random().nextInt(monsters.size());
        return monsters.get(index);
    }
}
