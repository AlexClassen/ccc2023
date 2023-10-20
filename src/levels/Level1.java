package levels;

import map.Coord;
import map.Karte;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Level1 extends Level{

    public Level1(String name) throws IOException {
        super(name);
        loadInput();
        solve();
    }

    public String checkCoordinates() throws IOException {
        StringBuilder result = new StringBuilder();
        /*for(int i = 0; i < numberOfCoordinates - 1; i++) {
            result.append(getInfo(coordinates[i][0], coordinates[i][1]) + "\n");
        }*/
        for(int i = 0; i < loadInput().size(); i++){
            Coord coord = loadInput().get(i);
            result.append(karte.getInfo(coord.x, coord.y) + (i+1 == loadInput().size() ? "" : "\n"));
        }
        //result.append(getInfo(coordinates[numberOfCoordinates - 1][0], coordinates[numberOfCoordinates - 1][1]));
        return result.toString();
    }

    public ArrayList<Coord> loadInput() throws IOException {
        ArrayList<Coord> coords = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("data/level1/" + name + ".in"), StandardCharsets.UTF_8);
        karte.width = Integer.parseInt(lines.get(0));
        karte.array = new char[karte.width][karte.width];
        for(int y = 0; y < karte.width; y++){
            for(int x = 0; x < karte.width; x++){
                karte.array[y][x] = lines.get(y+1).charAt(x);
            }
        }
        int numberOfCoordinates = Integer.parseInt(lines.get(karte.width + 1));
        //coordinates = new int[numberOfCoordinates][2];
        for(int i = 0; i < numberOfCoordinates; i++) {
            String[] parts = lines.get(karte.width + 2 + i).split(",");
            Coord coord = new Coord(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            coords.add(coord);
        }
        return coords;
    }

    public void solve() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output/level1/" + name + ".txt", true))){
            writer.write(checkCoordinates());
        }
    }

}
