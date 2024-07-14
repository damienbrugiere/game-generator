package map;

import java.util.HashSet;

public class Island extends HashSet<Tile> {
    private String name;

    public String getName() {
        return name;
    }

    boolean isTileSeparated(Tile tile) {
        return stream()
                .filter(t -> !t.isBlock())
                .noneMatch(t -> {
                    long distance = Math.abs(t.getX() - tile.getX()) + Math.abs(t.getY() - tile.getY());
                    return distance < Config.DISTANCEBETWEENISLAND;
                });
    }

}
