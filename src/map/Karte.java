package map;

import utils.Utilizer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Karte {

    public int width;
    public char[][] array;

    public char getInfo(int x, int y){
        return this.array[x][y];
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public char[][] getArray() {
        return array;
    }

}
