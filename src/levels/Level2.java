package levels;

import map.Coord;
import map.Pair;
import utils.Utilizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Level2 extends Level{

    public Level2(String name) throws IOException {
        super(name);
        loadInput();
        solve();
    }

    public ArrayList<Pair<Coord, Coord>> loadInput() throws IOException {
        ArrayList<Pair<Coord, Coord>> result = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("data/level2/" + name + ".in"), StandardCharsets.UTF_8);
        karte.width = Integer.parseInt(lines.get(0));
        karte.array = new char[karte.width][karte.width];
        for(int y = 0; y < karte.width; y++){
            for(int x = 0; x < karte.width; x++){
                karte.array[y][x] = lines.get(y+1).charAt(x);
            }
        }
        int numberOfCoordinates = Integer.parseInt(lines.get(karte.width + 1));
        for(int i = 0; i < numberOfCoordinates; i++) {

            String[] parts = lines.get(karte.width + 2 + i).split(" ");
            String[] part1 = parts[0].split(",");
            String[] part2 = parts[1].split(",");
            Coord coord1 = new Coord(Integer.parseInt(part1[0]), Integer.parseInt(part1[1]));
            Coord coord2 = new Coord(Integer.parseInt(part2[0]), Integer.parseInt(part2[1]));
            result.add(new Pair<>(coord1, coord2));
        }
        return result;
    }

    public String isSameIsland() throws IOException {
        StringBuilder result = new StringBuilder();
        /*
        for(Pair<Coord, Coord> c : loadInput2()) {
            result.append(isSameIslandHelper1(c.left.x, c.left.y, c.right.x, c.right.y) + "\n");
        }
        */
        int size = loadInput().size();

        for (int i = 0; i < size; i++) {
            Pair<Coord, Coord> c = loadInput().get(i);
            boolean isLast = i == size - 1;
            Coord c1 = new Coord(c.left.x, c.left.y);
            Coord c2 = new Coord(c.right.x, c.right.y);
            result.append(isSameIslandHelper1(c1.y, c1.x, c2.y, c2.x));

            if (!isLast) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    private String isSameIslandHelper1(int y1, int x1, int y2, int x2) {
        char[][] copy = Utilizer.copy(karte.array, karte.width);
        System.out.println(x1 + "," + y1 + ":" + x2 + "," + y2);
        return isSameIslandHelper(y1, x1, y2, x2, copy);
    }

    private String isSameIslandHelper(int x1, int y1, int x2, int y2, char[][] copy) {
        //printCharArray(copy);
        if(x1 == x2 && y1 == y2) {
            return "SAME";
        }
        if(copy[x1][y1] == 'X') {
            return "DIFFERENT";
        }
        copy[x1][y1] = 'X';
        if(x1 > 0 && karte.getInfo(x1 - 1, y1) == 'L') {
            if(isSameIslandHelper(x1 - 1, y1, x2, y2, copy).equals("SAME")) {
                return "SAME";
            }
        }
        if(x1 < karte.width - 1 && karte.getInfo(x1 + 1, y1) == 'L') {
            if(isSameIslandHelper(x1 + 1, y1, x2, y2, copy).equals("SAME")) {
                return "SAME";
            }
        }
        if(y1 > 0 && karte.getInfo(x1, y1 - 1) == 'L') {
            if(isSameIslandHelper(x1, y1 - 1, x2, y2, copy).equals("SAME")) {
                return "SAME";
            }
        }
        if(y1 < karte.width - 1 && karte.getInfo(x1, y1 + 1) == 'L') {
            if(isSameIslandHelper(x1, y1 + 1, x2, y2, copy).equals("SAME")) {
                return "SAME";
            }
        }
        return "DIFFERENT";
    }

    public void solve() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/level2/" + name + ".txt", true))){
            writer.write(isSameIsland());
        }
    }

}
