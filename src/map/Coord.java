package map;

public class Coord{

    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + x + "," + y + "}";
    }

    public boolean equals(Coord otherCord) {
        return x == otherCord.x && y == otherCord.y;
    }
}
