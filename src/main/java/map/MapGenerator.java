package map;

public class MapGenerator {

    private int height= 50;
    private int width= 50;
    private int numberOfCaseForIsland = 200;
    private int numberOfIsland = 5;
    private int distanceBeetweenIsland = 5;

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

    public MapGenerator withNumberOfIsland(int numberOfIsland){
        this.numberOfIsland = numberOfIsland;
        return this;
    }
    public MapGenerator withNumberOfCaseForIsland(int numberOfCaseForIsland){
        this.numberOfCaseForIsland = numberOfCaseForIsland;
        return this;
    }

    public MapGenerator withDistanceBeetweenIsland(int distanceBeetweenIsland){
        this.distanceBeetweenIsland = distanceBeetweenIsland;
        return this;
    }

    public Map generateV2() throws InterruptedException {
        Map map = new Map(height, width);
        int numberOfIslands = this.numberOfIsland;
        for(int i = 0 ; i < numberOfIslands; i++){
            generateIslandV2(map, i);
        }
        return map;

    }

    private void generateIslandV2(Map map, int index) throws InterruptedException {
        Island island = new Island();
        Tile tile;
        do {
            int y = RandomUtils.random(height - 10, 10);
            int x = RandomUtils.random(width - 10, 10);
            tile = map.getTile(x, y);
        }while (map.isTooCloseToIslands(tile, this.distanceBeetweenIsland));

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
        if(island.size() >= this.numberOfCaseForIsland){
            return null;
        }
        int width = RandomUtils.random(5, 3);
        int height = RandomUtils.random(5, 3);
        int originX = tile.getX();
        int originY = tile.getY();
        return map.transformArea(island,
                index,
                height,
                width,
                originX,
                originY,
                RandomUtils.random(4,1),
                this.distanceBeetweenIsland);
    }

}
