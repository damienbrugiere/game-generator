package map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Map {
    private long height;
    private long width;
    private Tile[][] map;

    private List<Island> islands = new ArrayList<>();

    public Map(long height, long width, Tile[][] map) {
        this.height = height;
        this.width = width;
        this.map = map;
        this.init();
    }
    public Map(long height, long width){
        this(height, width, new Tile[(int) height][(int) width]);
    }


    public void displayMap(){
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.map[y][x].display();
            }
            System.out.println();
        }
    }
    void init(){
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x]= new Tile((long) x,(long) y, true, null);
            }
        }
    }

    //
    public Tile getNorth(int x, int y){
        return getTile(x, y-1);
    }
    public  Tile getNorthEast(int x, int y){
        return getTile(x+1,y-1);
    }

    public  Tile getEast(int x, int y){
        return getTile(x+1, y);
    }
    public  Tile getSouthEast(int x, int y){
        return getTile(x+1,y+1);
    }

    public  Tile getSouth(int x, int y){
        return getTile(x,y+1);
    }
    public  Tile getSouthWest(int x, int y){
        return getTile(x-1,y+1);
    }
    public  Tile getWest(int x, int y){
        return getTile(x-1,y);
    }

    public  Tile getNorthWest(int x, int y){
       return getTile(x-1,y-1);
    }

    public Tile getTile(int x, int y){
        if(x < 1 || x >= width-1 || y< 1 || y>= height-1 ){
            return null;
        }
        return map[y][x];
    }

    Tile transformTileToGround(int x, int y, int index){
        this.map[y][x] = new Tile(x,y, false, index);
        return this.getTile(x,y);
    }

    List<Tile> getAllTilesSeaNear(Tile tile){
        List<Tile> tiles = new ArrayList<>();
        addIfSea(getNorth(tile.getX(),tile.getY()), tiles);
        addIfSea(getNorthWest(tile.getX(),tile.getY()), tiles);
        addIfSea(getWest(tile.getX(),tile.getY()), tiles);
        addIfSea(getSouthWest(tile.getX(),tile.getY()), tiles);
        addIfSea(getSouth(tile.getX(),tile.getY()), tiles);
        addIfSea(getSouthEast(tile.getX(),tile.getY()), tiles);
        addIfSea(getEast(tile.getX(),tile.getY()), tiles);
        addIfSea(getNorthEast(tile.getX(),tile.getY()), tiles);
        return tiles;
    }

    boolean isTooCloseToIslands(Tile tile){
        return this.islands.stream().anyMatch(island -> !island.isTileSeparated(tile));
    }

    private void addIfSea(Tile tile, List<Tile> tiles){
        if(tile == null || !tile.isBlock()){
            return;
        }
        tiles.add(tile);
    }

    void addIsland(Island island){
        this.islands.add(island);
    }

    public Tile transformArea(Island island, int index, int height, int width, int originX, int originY, int value){
        switch (value){
            case 1:
                for (int x = originX; x < originX + width; x ++){
                    for (int y = originY; y < originY + height; y ++){
                        addGround(island, index, x, y);
                    }
                }
                break;
            case 2:
                for (int x = originX; x > originX - width; x --){
                    for (int y = originY; y < originY + height; y ++){
                        addGround(island, index, x, y);
                    }
                }
                break;
            case 3:
                for (int x = originX; x < originX + width; x ++){
                    for (int y = originY; y > originY - height; y --){
                        addGround(island, index, x, y);
                    }
                }
                break;
            case 4:
                for (int x = originX; x > originX - width; x--){
                    for (int y = originY; y > originY - height; y--){
                        addGround(island, index, x, y);
                    }
                }
                break;
        }
        for (int x = originX; x < originX + width; x ++){
            for (int y = originY; y < originY + height; y ++){
                addGround(island, index, x, y);
            }
        }
        List<Tile> tiles = getBordures(island);
        return tiles.get(RandomUtils.random(tiles.size()));
    }

    private void addGround(Island island, int index, int x, int y) {
        Tile tile = this.getTile(x, y);
        if(tile == null || tile.hasAlreadyIsland()){
            return;
        }
        Tile t = transformTileToGround(x, y, index);
        island.add(t);
    }

    public List<Tile> getBordures(Island island){
        return island.stream().filter(t -> !this.getAllTilesSeaNear(t).isEmpty()).collect(Collectors.toList());
    }
}
