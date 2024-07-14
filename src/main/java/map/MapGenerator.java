package map;

import java.util.List;

public class MapGenerator {

    private int height, width;

    public static MapGenerator builder() {
        return new MapGenerator();
    }

    public MapGenerator withWidth(int width) {
        this.width = width;
        return this;
    }

    public MapGenerator withHeight(int height) {
        this.height = height;
        return this;
    }

    public Map generate() {
        Map map = new Map(height, width);
        int numberOfIslands = Config.NUMBEROFISLAND;
        int index = 0;
        while (numberOfIslands != 0) {
            generateIsland(map, index);
            index++;
            numberOfIslands--;
        }
        return map;
    }

    private void generateIsland(Map map, int index) {
        Island island = new Island();
        Tile tile;
        do {
            int randomY = RandomUtils.random(height);
            int randomX = RandomUtils.random(width);
            tile = map.getTile(randomX, randomY);
        } while (map.isTooCloseToIslands(tile));

        // Start transforming tiles to create an island
        transformTile(map, island, tile, index);
        map.addIsland(island);
    }

    private void transformTile(Map map, Island island, Tile origin, int index) {
        if (island.size() >= Config.NUMBEROFCASEFORISLAND) {
            return;
        }

        Tile tileChosen;
        origin = map.transformTileToGround(origin.getX(), origin.getY(), index);
        island.add(origin);

        List<Tile> tiles = map.getAllTileNearWithoutNullAndSea(origin);
        while (!tiles.isEmpty()) {
            int indexChoose = RandomUtils.random(tiles.size());
            tileChosen = tiles.get(indexChoose);
            tiles.remove(indexChoose);

            if (!map.isTooCloseToIslands(tileChosen)) {
                transformTile(map, island, tileChosen, index);
            }
        }
    }
}
