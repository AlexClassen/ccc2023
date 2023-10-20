package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Utilizer {

    public static char[][] copy(char[][] oldArray, int width) {
        char[][] newArray = new char[width][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                newArray[i][j] = oldArray[i][j];
            }
        }
        return newArray;
    }

    public static void printCharArray(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void writeFile(String text, String path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))){
            writer.write(text);
        }
    }

}
