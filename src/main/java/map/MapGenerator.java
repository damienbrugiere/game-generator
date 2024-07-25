package map;

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


    public Map generateV2() throws InterruptedException {
        Map map = new Map(height, width);
        //int numberOfIslands = Config.NUMBEROFISLAND;
        generateIslandV2(map, 1);
        generateIslandV2(map, 2);
        generateIslandV2(map, 3);
        generateIslandV2(map, 4);
        return map;

    }

    private void generateIslandV2(Map map, int index) throws InterruptedException {
        Island island = new Island();
        Tile tile;
        do {
            int y = RandomUtils.random(height - 10, 10);
            int x = RandomUtils.random(width - 10, 10);
            tile = map.getTile(x, y);
        }while (map.isTooCloseToIslands(tile));

        while (tile != null){
            tile = addArea(map, island, tile, index);
//            map.displayMap();
//            Thread.sleep(1000);
//            System.out.print("\033[H\033[2J");
//            System.out.flush();
        }
        map.addIsland(island);

    }
    private Tile addArea(Map map, Island island, Tile tile, int index){
        if(island.size() >= Config.NUMBEROFCASEFORISLAND){
            return null;
        }
        int width = RandomUtils.random(10, 5);
        int height = RandomUtils.random(10, 5);
        int originX = tile.getX();
        int originY = tile.getY();
        return map.transformArea(island, index, height,width,originX, originY, RandomUtils.random(4,1));
    }

}
