package levels;

import map.Karte;

public class Level {

    Karte karte;
    String name;

    public Level(String name) {
        this.name = name;
        this.karte = new Karte();
    }

}
