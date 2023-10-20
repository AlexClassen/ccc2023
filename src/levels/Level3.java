package levels;

import map.Coord;
import map.Karte;
import map.Pair;
import utils.Utilizer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Level3 extends Level{

    public Level3(String name) throws IOException {
        super(name);
        solve();
    }

    // input ArrayList <ArrayList<Coord>> coords
    public  ArrayList<ArrayList<Coord>> loadInput() throws IOException {
        ArrayList<ArrayList<Coord>> result = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("data/level3/" + name + ".in"), StandardCharsets.UTF_8);
        karte.setWidth(Integer.parseInt(lines.get(0)));
        karte.array = new char[karte.getWidth()][karte.getWidth()];
        for(int y = 0; y < karte.getWidth(); y++){
            for(int x = 0; x < karte.getWidth(); x++){
                karte.array[y][x] = lines.get(y+1).charAt(x);
            }
        }
        int numberOfCoordinates = Integer.parseInt(lines.get(karte.getWidth() + 1));
        for(int i = 0; i < numberOfCoordinates; i++) {

            ArrayList<Coord> line = new ArrayList<>();

            String[] parts = lines.get(karte.getWidth() + 2 + i).split(" ");
            for(int j = 0; j<parts.length; j++) {
                String[] part = parts[j].split(",");
                Coord coord = new Coord(Integer.parseInt(part[0]), Integer.parseInt(part[1]));
                line.add(coord);
            }
            result.add(line);
        }
        return result;
    }

    public static String doesIntersect(ArrayList<ArrayList<Coord>> listOfCoords) throws IOException {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < listOfCoords.size(); i++) {
            result.append(doesIntersectHelper(listOfCoords.get(i)) + (i+1 == listOfCoords.size() ? "" : "\n"));
        }
        return result.toString();
    }

    private static String doesIntersectHelper(ArrayList<Coord> coords) throws IOException {
        // Check if it doubles
        for(int i = 0; i < coords.size(); i++) {
            for(int j = 0; j < i; j++) {
                if(j != i && coords.get(j).equals(coords.get(i))) {
                    return "INVALID";
                }
            }
        }

        List<Pair<Coord, Coord>> allDiagonals = diagonalsForLine(coords);
        for(int i = 0; i < allDiagonals.size(); i++){
            System.out.println("Diagonal NR: " + i);
            Pair<Coord, Coord> diagonal = allDiagonals.get(i);

            for(int j = 0; j < i; j++){
                Pair<Coord, Coord> otherDiagonal = allDiagonals.get(j);
                boolean check = checkIntersect(diagonal, otherDiagonal);
                if(check)
                    return "INVALID";
            }

        }
        return "VALID";
    }

    @Deprecated
    private static String doesIntersectHelperOld(ArrayList<Coord> coords) {
        // Check if it doubles
        for(int i = 0; i < coords.size(); i++) {
            for(int j = 0; j < coords.size(); j++) {
                if(j != i && coords.get(j) == coords.get(i)) {
                    return "INVALID";
                }
            }
        }

        for(int i = 0; i < coords.size(); i++) {
            if(i < coords.size() - 1) {
                int xStep = coords.get(i).x - coords.get(i + 1).x;
                int yStep = coords.get(i).y - coords.get(i + 1).y;
                for(int n = 0; n < coords.size() - 1; n++) {
                    if(coords.get(n).equals(new Coord(coords.get(i).x - xStep, coords.get(i).y)) && coords.get(n + 1).equals(new Coord(coords.get(i).x, coords.get(i).y - yStep))
                    || coords.get(n).equals(new Coord(coords.get(i).x, coords.get(i).y - yStep)) && coords.get(n + 1).equals(new Coord(coords.get(i).x - xStep, coords.get(i).y))) {
                        //System.out.println(coords.get(n));
                        return "INVALID";
                    }
                }
            }
        }
        return "VALID";
    }

    public static List<Pair<Coord, Coord>> diagonalsForLine(ArrayList<Coord> coords) throws IOException {
        ArrayList<Pair<Coord, Coord>> diagonals = new ArrayList<>();
        for(int i = 0; i < coords.size()-1; i++){
            Coord c1 = coords.get(i);
            Coord c2= coords.get(i+1);

            int xStep = c2.x-c1.x;
            int yStep = c2.y-c1.y;

            if(xStep != 0 && yStep!= 0){
                Pair<Coord, Coord> p = new Pair<>(c1,c2);
                diagonals.add(p);
            }

        }
        return diagonals;
    }

    public static boolean checkIntersect(Pair<Coord, Coord> d1, Pair<Coord, Coord> d2){
        boolean result = (d1.left.x + d1.right.x == d2.left.x + d2.right.x && d1.left.y + d1.right.y == d2.left.y + d2.right.y);
        System.out.println(d1 + " " + d2 + ": " + result);
        return result;
    }

    public static boolean checkIntersect_Other(Pair<Coord, Coord> d1, Pair<Coord, Coord> d2){
        boolean result = false;
        int yStep = d1.left.y - d1.right.y;
        int xStep = d1.left.x - d1.right.x;
        if(d2.left.equals(new Coord(d1.left.x, d1.left.y - yStep)) && d2.right.equals(new Coord(d1.left.x - xStep, d1.left.y)) ||
                d2.right.equals(new Coord(d1.left.x, d1.left.y - yStep)) && d2.left.equals(new Coord(d1.left.x - xStep, d1.left.y))) {
            result = true;
        }
        System.out.println(result);
        return result;
    }

    public void solve() throws IOException {
        String result = doesIntersect(loadInput());
        Utilizer.writeFile(result, "output/level3/" + name + ".txt");
    }

}
