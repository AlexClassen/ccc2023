package main;

import levels.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        // Level 1
        /*
        for(File f : new File("data/level1").listFiles()){
            String name = f.getName().replace(".in", "");
            System.out.println(name);
            Karte karte = new Karte(name);
            karte.level1();
        }

        // Level 2
        for(File f : new File("data/level2").listFiles()){
            String name = f.getName().replace(".in", "");
            Level l = new Level2(name);
        }
         */

        // Level 3
        for(String name : getFilesForLevel("level3")){
            System.out.println(name);
            Level3 l = new Level3(name);
        }
    }

    public static List<String> getFilesForLevel(String level){
        File folder = new File("data/" + level);
        List<File> files = List.of(folder.listFiles());
        return files.stream().map(File::getName)
                      .map(name -> name.replace(".in", ""))
                      .collect(Collectors.toList());
    }

}
